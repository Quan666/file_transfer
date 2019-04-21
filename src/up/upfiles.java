package up;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.util.Collection;
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
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 

/**
 * Servlet implementation class up
 */

@WebServlet("/upfiles")
@MultipartConfig(maxFileSize=-1)//上传文件大小限制-1默认无限制//1024*1024*200=200mb
public class upfiles extends HttpServlet {
    private static final long serialVersionUID = 1L;
     config c=new config();
     static final int MAX_FILE_SIZE      = 1024 * 1024 ; // 1MB
 

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	// 上传文件存储目录
    	
    	
    	
    	
    	
    	c.config();
    	String UPLOAD_DIRECTORY = "../"+c.uppath;
    	String fileName="";
    	
        String password=""; 
        try {
			password=request.getParameter("password");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(password);
			password="";
		}
        finally {
        	try {
            	//说明输入的请求信息采用UTF-8编码方式
        		request.setCharacterEncoding("utf-8");
        		response.setContentType("text/html; charset=UTF-8");
        		PrintWriter out = response.getWriter();
        		//Servlet3.0中新引入的方法，用来处理multipart/form-data类型编码的表单
        		
        		
        		System.out.print(password);
        		Collection<Part> parts = request.getParts();
        		boolean e=true;
        		for(Part part:parts){
        			//获取HTTP头信息headerInfo=（form-data; name="file" filename="文件名"）
            		String headerInfo = part.getHeader("content-disposition");
            		//从HTTP头信息中获取文件名fileName=（文件名）
            		
            		String fn = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
            		if("password".equals(fn))continue;
            		fileName=fn;
            		fileName=removeSpecialcharacter(fileName);
            		if(part.getSize()>=MAX_FILE_SIZE*Integer.parseInt(c.max_size)) {
            			request.setAttribute("message","文件超出"+c.max_size+"MB!无法上传！");
            			e=false;
            			break;
            		}
            		
            		password=new String((password).getBytes("ISO-8859-1"),"UTF-8");
            		//获得存储上传文件的文件夹路径
            		String fileSavingFolder = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
            		if(!"".equals(password)) {
            			//密码设定
                		String configPath = request.getServletContext().getRealPath("./") + File.separator + "../config";
                		Properties pro = new Properties();
                    	pro.loadFromXML(new FileInputStream(configPath+"/password.xml"));
                        pro.setProperty(fileName, password);
                        pro.storeToXML(new FileOutputStream(new File(configPath+"/password.xml")), "passwordfile");
            		}
            		
            		
            		//获得存储上传文件的完整路径（文件夹路径+文件名）
            		//文件夹位置固定，文件夹采用与上传文件的原始名字相同
            		String fileSavingPath = fileSavingFolder + File.separator + fileName;
            		
            		//如果存储上传文件的文件夹不存在，则创建文件夹
            		File f = new File(fileSavingFolder + File.separator);
            		if(!f.exists()){
            			f.mkdirs();
            		}
            		
            		//将上传的文件内容写入服务器文件中
            		part.write(fileSavingPath);

                    /*if(null!=fileName&&!(fileName).trim().equals("")){

                        String direc = this.getServletContext().getRealPath("/upload/");
                        File dirf = new File(direc);

                        if(!dirf.exists()){
                            dirf.mkdirs();
                        }
                        part.write(direc+fileName);
                    }*/
        		}
            	
        		if(e)this.getServletContext().setAttribute("message","<div class=\"alert alert-success alert-dismissable\">\r\n" + 
        				"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
        				"			aria-hidden=\"true\">\r\n" + 
        				"		&times;\r\n" + 
        				"	</button>\r\n" + 
        				"	<center>文件“"+fileName+"”上传成功！</center>\r\n" + 
        				"</div>" );
            } catch (Exception ex) {
            	this.getServletContext().setAttribute("message","<div class=\"alert alert-danger alert-dismissable\">\r\n" + 
            			"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
            			"			aria-hidden=\"true\">\r\n" + 
            			"		&times;\r\n" + 
            			"	</button>\r\n" + 
            			"	<center>文件“"+fileName+"”上传出错！</center>\r\n" + 
            			"</div>" );
                //request.setAttribute("message","错误信息: " + ex.getMessage());//调试
            }
            // 跳转到 message.jsp
            //request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        	
            response.sendRedirect("index.jsp");
        }
        
    }
    
    public static String removeSpecialcharacter(String filename){
    	filename=filename.replace(" ", "");//去掉空格
    	filename=filename.replace("#", "");
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