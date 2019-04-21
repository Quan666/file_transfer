package delete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    
 
   
 
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	// 文件存储路径
    	config.config c=new config.config();
    	c.config();
    	String password="";
    	String inpassword="";
        String DIRECTORY =c.configpath+ c.uppath+"/";
        String filename=new String((request.getParameter("filename")).getBytes("ISO-8859-1"),"UTF-8");
    	DIRECTORY=DIRECTORY+filename;
    	Properties red = new Properties();
        red.loadFromXML(new FileInputStream(c.configpath+"config/password.xml"));
    	try {
			inpassword=new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
			if(!"".equals(inpassword)) {
                
                password=red.getProperty(filename);
                
			}
		} catch (Exception e) {
			// TODO: handle exception 
		}
    	String fileName="";
    	try{
    		File file = new File(DIRECTORY);
    		if(!inpassword.equals(password)){
    			this.getServletContext().setAttribute("message",file.getName() + "<div class=\"alert alert-danger alert-dismissable\">\r\n" + 
            			"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
            			"			aria-hidden=\"true\">\r\n" + 
            			"		&times;\r\n" + 
            			"	</button>\r\n" + 
            			"	<center> 文件删除失败！密码错误！！！</center>\r\n" + 
            			"</div>");
            }
    		if(inpassword.equals(password)) {
    			if(file.delete()){
    				this.getServletContext().setAttribute("message","<div class=\"alert alert-success alert-dismissable\">\r\n" + 
                			"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
                			"			aria-hidden=\"true\">\r\n" + 
                			"		&times;\r\n" + 
                			"	</button>\r\n" + 
                			"	<center>文件“"+file.getName() + "”已被删除！</center>\r\n" + 
                			"</div>");
                	red.remove(file.getName());
                	fileName=file.getName();
                    red.storeToXML(new FileOutputStream(new File(c.configpath+"config/password.xml")), "password");
                }else {
                	this.getServletContext().setAttribute("message","<div class=\"alert alert-danger alert-dismissable\">\r\n" + 
                			"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
                			"			aria-hidden=\"true\">\r\n" + 
                			"		&times;\r\n" + 
                			"	</button>\r\n" + 
                			"	<center>文件“"+file.getName() + "”删除失败！</center>\r\n" + 
                			"</div>");
                }
    		}
        }catch(Exception e){
            e.printStackTrace();
            this.getServletContext().setAttribute("message","<div class=\"alert alert-danger alert-dismissable\">\r\n" + 
        			"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
        			"			aria-hidden=\"true\">\r\n" + 
        			"		&times;\r\n" + 
        			"	</button>\r\n" + 
        			"	<center>文件“"+fileName+"”删除失败！"+e+"</center>\r\n" + 
        			"</div>");
        }
    	
    	
    	
    	
        // 跳转到 message.jsp
    	
        //request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    	
        response.sendRedirect("index.jsp");
    }
}
