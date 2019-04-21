package up;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.*;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 

/**
 * Servlet implementation class up
 */
@WebServlet("/up")
@MultipartConfig
public class up extends HttpServlet {
    private static final long serialVersionUID = 1L;
     config c=new config();
     
    
    
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 ; // 1MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 500; // 500MB
 
    /**
     * 上传数据及保存文件
     */
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        // 检测是否为多媒体上传
    	// 上传文件存储目录
    	
    	c.config();
    	String UPLOAD_DIRECTORY = "../"+c.uppath;
    	
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        
        upload.setFileSizeMax(MAX_FILE_SIZE*Integer.parseInt(c.max_size));
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        
        
        
        
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("/unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        
                        //fileName=fileName.replace(" ", "");//去掉空格
                        fileName=removeSpecialcharacter(fileName);
                        
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        //System.out.println(filePath);
                        String password="";
                        // password文件的存储
                        try {
                        	Properties pro = new Properties();
                        	String configPath = request.getServletContext().getRealPath("./") + File.separator + "../config";
                        	//System.out.println(configPath);
                        	password=new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
                        	//pro.loadFromXML(new FileInputStream(configPath+"/password.xml"));
                            pro.setProperty(fileName, password);
                            File configDir = new File(configPath);
                            if (!configDir.exists()) {
                                configDir.mkdir();
                            }
                            // password
                            pro.storeToXML(new FileOutputStream(new File(configPath+"/password.xml")), "passwordfile");
                            //Properties red = new Properties();
                            //red.loadFromXML(new FileInputStream(configPath+"/password.xml"));
                		} catch (Exception e) {
                			
                			System.out.println(e);
                			System.out.println(fileName+password+"@");
                		}
                        
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                            "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
        	request.setAttribute("message","上传出错！" );
            request.setAttribute("message","错误信息: " + ex.getMessage());//调试
        }
        // 跳转到 message.jsp
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
    
    
    public static String removeSpecialcharacter(String filename){
    	Pattern pattern=Pattern.compile("[\u4e00-\u9fa5]");//中文汉字编码区间  
        Matcher matcher;
        char[] array = filename.toCharArray();
        for (int i = 0; i < array.length; i++) {
             if((char)(byte)array[i]!=array[i]){//取出双字节字符
            	 matcher=pattern.matcher(String.valueOf(array[i]));
                    if(!matcher.matches()){//中文汉字无需替换
                        filename=filename.replaceAll(String.valueOf(array[i]), "");//特殊字符用空字符串替换
                   }
             }
        }
           return filename;
    }
}
