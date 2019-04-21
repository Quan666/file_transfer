package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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
@WebServlet("/install")
public class install extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    
 
   
 
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String uppath=new String((request.getParameter("uppath")).getBytes("ISO-8859-1"),"UTF-8");
    	String link=new String((request.getParameter("link")).getBytes("ISO-8859-1"),"UTF-8");
    	String admin=new String((request.getParameter("admin")).getBytes("ISO-8859-1"),"UTF-8");
    	String password=new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
    	String max_size=new String((request.getParameter("max_size")).getBytes("ISO-8859-1"),"UTF-8");
    	
    	Properties pro = new Properties();
    	Properties pd = new Properties();
        // 资源配置文件的存储
        pro.setProperty("uppath", uppath);
        pro.setProperty("link", link);
        pro.setProperty("admin", admin);
        pro.setProperty("password", password);
        pro.setProperty("max_size", max_size);
        String configPath = request.getServletContext().getRealPath("./") + File.separator + "../config";
        
        File configDir = new File(configPath);
        if (!configDir.exists()) {
            configDir.mkdir();
        }
        // 资源配置文件的输出
        //pro.store(new FileOutputStream(new File("config.properties")), "配置文件");
        pro.storeToXML(new FileOutputStream(new File(configPath+"/config.xml")), "配置文件");
        pd.storeToXML(new FileOutputStream(new File(configPath+"/password.xml")), "password");
        Properties red = new Properties();
        //red.load(new FileReader("config.properties"));
        red.loadFromXML(new FileInputStream(configPath+"/config.xml"));
        if(red.getProperty("uppath").equals(uppath)&&red.getProperty("link").equals(link)&&red.getProperty("admin").equals(admin)&&red.getProperty("password").equals(password)){
            request.setAttribute("message", "安装成功！");
        }else{
            request.setAttribute("message","安装失败！");
        }
        
    	
    	
    	
    	
        // 跳转到 message.jsp
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
}

