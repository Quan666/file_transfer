package look;

import java.io.File;
import java.util.ArrayList;

import org.apache.tomcat.util.collections.SynchronizedQueue;

public class look {
    public  ArrayList<String> look() throws Exception {
    	config.config c=new config.config();
    	c.config();
        String DIRECTORY =c.configpath+c.uppath; 
    	ArrayList<String> all=new ArrayList<String>(); 
        File dir = new File(DIRECTORY); //要遍历的目录
        return all=visitAllDirsAndFiles(dir);
    }
    public static ArrayList<String> visitAllDirsAndFiles(File dir) {
    	ArrayList<String> all=new ArrayList<String>();
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));
            	all.add(""+children[i]);
            }
        }
		return all;
    }
}
