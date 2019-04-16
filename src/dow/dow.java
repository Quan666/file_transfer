package dow;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
/*
public class dow {
	public static boolean downloadHttpUrl(String url, String dir, String fileName) {
		try {
			URL httpurl = new URL(url);
			File dirfile = new File(dir);  
	        if (!dirfile.exists()) {  
	        	dirfile.mkdirs();
	        }
			FileUtils.copyURLToFile(httpurl, new File(dir+fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}*/
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class dow {
    private URL url;    // 目标地址
    private File file;  // 本地文件
    private static final int THREAD_AMOUNT = 8;                 // 线程数
    
    private int threadLen;                                      // 每个线程下载多少
    
    
    //文件名处理
    
    
    
    public dow(String address, String dirs, String filename) throws IOException {     // 通过构造函数传入下载地址
        url = new URL(address);
        File dir = new File(dirs);
        if(!dir.exists()){
            dir.mkdirs();
        }
        file = new File(dir, filename);
    }

    public void download() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);

        int totalLen = conn.getContentLength();                             // 获取文件长度
        threadLen = (totalLen + THREAD_AMOUNT - 1) / THREAD_AMOUNT;         // 计算每个线程要下载的长度

        System.out.println("totalLen="+totalLen+",threadLen:"+threadLen);

        RandomAccessFile raf = new RandomAccessFile(file, "rws");           // 在本地创建一个和服务端大小相同的文件
        raf.setLength(totalLen);                                            // 设置文件的大小
        raf.close();

        for (int i = 0; i < THREAD_AMOUNT; i++)                             // 开启3条线程, 每个线程下载一部分数据到本地文件中
            new DownloadThread(i).start();
    }

    private class DownloadThread extends Thread {
        private int id;
        public DownloadThread(int id) {
            this.id = id;
        }
        public void run() {
            int start = id * threadLen;                     // 起始位置
            int end = id * threadLen + threadLen - 1;       // 结束位置
            System.out.println("线程" + id + ": " + start + "-" + end);

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("Range", "bytes=" + start + "-" + end);     // 设置当前线程下载的范围

                InputStream in = conn.getInputStream();
                RandomAccessFile raf = new RandomAccessFile(file, "rws");
                raf.seek(start);            // 设置保存数据的位置

                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1)
                    raf.write(buffer, 0, len);
                raf.close();

                System.out.println("线程" + id + "下载完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   
}