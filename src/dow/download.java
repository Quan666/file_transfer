package dow;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class download extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    
 
   
 
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	// 文件存储路径
    	config.config c=new config.config();
    	c.config();
        String DIRECTORY =c.configpath+ c.uppath+"/";
    	String url=new String((request.getParameter("dowlink")).getBytes("ISO-8859-1"),"UTF-8");
    	String name=new String((request.getParameter("downame")).getBytes("ISO-8859-1"),"UTF-8");
    	
    	//后缀名处理
    	name =name +"."+ url.substring(url.lastIndexOf('.')+1);
    	
    	
    	try{
            dow dow=new dow(url,DIRECTORY,name);
            dow.download();
            this.getServletContext().setAttribute("message","<div class=\"alert alert-success alert-dismissable\">\r\n" + 
            		"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
            		"			aria-hidden=\"true\">\r\n" + 
            		"		&times;\r\n" + 
            		"	</button>\r\n" + 
            		"	<center>“"+name + "”离线下载任务添加成功！请过段时间再来下载！视文件大小而定！</center>\r\n" + 
            		"</div>");
        }catch(Exception e){
            e.printStackTrace();
            this.getServletContext().setAttribute("message","<div class=\"alert alert-danger alert-dismissable\">\r\n" + 
            		"	<button type=\"button\" class=\"close\" data-dismiss=\"alert\"\r\n" + 
            		"			aria-hidden=\"true\">\r\n" + 
            		"		&times;\r\n" + 
            		"	</button>\r\n" + 
            		"	<center>文件“"+name+ "”下载失败！</center>\r\n" + 
            		"</div>");
        }
    	
    	
    	
    	
        // 跳转到 message.jsp
        //request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    	response.sendRedirect("index.jsp");
    }
}
