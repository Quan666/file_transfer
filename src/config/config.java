package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
	public static String uppath;
	public static String link;
	public static String admin;
	public static String password;
	public static String configpath;
	public static String max_size;
	public void config() {
		Properties con = new Properties();
		configpath=config.class.getClassLoader().getResource("/").getPath()+"../../../";
		//red.load(new FileReader("config.properties"));
        try {

			con.loadFromXML(new FileInputStream(configpath+"config/config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//configpath=e.getMessage();
		}
        uppath=con.getProperty("uppath");
        link=con.getProperty("link");
        max_size=con.getProperty("max_size");
        admin=con.getProperty("admin");
        password=con.getProperty("password");
	}
}
