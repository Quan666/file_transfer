package delete;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

 
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
        String DIRECTORY =c.configpath+ c.uppath+"/";
    	DIRECTORY=DIRECTORY+new String((request.getParameter("filename")).getBytes("ISO-8859-1"),"UTF-8");
    	try{
            File file = new File(DIRECTORY);
            if(file.delete()){
            	request.setAttribute("message",file.getName() + " 文件已被删除！");
            }else{
            	request.setAttribute("message",file.getName() + " 文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    	
    	
    	
    	
        // 跳转到 message.jsp
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
}
