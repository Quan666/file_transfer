package file;

import java.util.Date;

public class file {
	
	public String name;
	public String size;
	public Date time;
	public String password;
	public file(String name, String size, Date time, String password) {
		this.name=name;
		this.size=size;
		this.time=time;
		this.password=password;
	}
}
