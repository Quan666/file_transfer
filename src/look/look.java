package look;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.tomcat.util.collections.SynchronizedQueue;

import file.file;

public class look {
    public  ArrayList<file> look() throws Exception {
    	config.config c=new config.config();
    	c.config();
        String DIRECTORY =c.configpath+c.uppath; 
    	ArrayList<file> all=new ArrayList<file>(); 
        File dir = new File(DIRECTORY); //要遍历的目录
        return all=visitAllDirsAndFiles(dir);
    }
    public static ArrayList<file> visitAllDirsAndFiles(File dir) {
    	ArrayList<file> all=new ArrayList<file>();
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
            	 File file = new File(dir, children[i]);
                visitAllDirsAndFiles(file);
                Long lastModified = file.lastModified();
                Date date = new Date(lastModified);
                //密码
                config.config c=new config.config();
            	c.config();
            	String ptag = "0";
                Properties red = new Properties();
                try {
					red.loadFromXML(new FileInputStream(c.configpath+"config/password.xml"));
					if(red.getProperty(""+children[i]) != null) {//
						ptag="1";
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	all.add(new file(""+children[i],longToString(file.length()),date,ptag));
            }
        }
		return all;
    }
    
    public static String longToString(long size) {
    	 
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        String ret = "";
         
        DecimalFormat df = new DecimalFormat("0.00");
         
        if(size >= gb){
            ret = df.format(size/(gb*1.0)) + " GB";
        }else if(size >= mb){
            ret = df.format(size/(mb*1.0)) + " MB";
        }else if(size >= kb){
            ret = df.format(size/(kb*1.0)) + " KB";
        }else if(size >= 0){
            ret = df.format(size/(1.0)) + " Byte";
        }
         
        return ret;
    }
}
