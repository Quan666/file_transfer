package look;

import java.io.File;
import java.util.ArrayList;

import org.apache.tomcat.util.collections.SynchronizedQueue;

public class look {
    public  ArrayList<String> look() throws Exception {
    	ArrayList<String> all=new ArrayList<String>(); 
        File dir = new File("/data/wwwroot/pic.myelf.club/upload"); //要遍历的目录
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
