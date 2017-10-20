package unit.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

import org.junit.Test;
import org.steve.heaertbeat.ShutDownListener;

public class DataRequestTest {

	@Test
	public void testDataRequest() {
		Properties properties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("conf/client.properties"));
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = properties.getProperty("client.ip");
		int port = Integer.valueOf(properties.getProperty("client.port", "9996"));
		System.out.println(host);
		try {
			Socket socket = new Socket(host, port);
			OutputStream opt = socket.getOutputStream();
			String command = "ReAd SyStEm StatuS...";
			InputStream ipt = socket.getInputStream();			
			StringBuffer str = new StringBuffer();
			opt.write(command.getBytes());
			opt.flush();
			socket.shutdownOutput();
			System.out.println("Finished flush");
			byte[] buf = new byte[1024*8];
			int len = 0;
			while((len =ipt.read(buf))!=-1){
				String temp = new String(buf,0,len);
				str.append(temp);
			}
			socket.shutdownInput();
			System.out.println(str.toString());
			opt.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testShutdownRequest() {
		Properties properties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("conf/client.properties"));
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = properties.getProperty("client.ip");
		int port = Integer.valueOf(properties.getProperty("client.stop.port", "9996"));
		System.out.println(host);
		try {
			Socket socket = new Socket(host, port);
			OutputStream opt = socket.getOutputStream();
			InputStream ipt = socket.getInputStream();			
			StringBuffer str = new StringBuffer();
			opt.write(ShutDownListener.SHUTDOWN_COMMAND.getBytes());
			opt.flush();
			socket.shutdownOutput();
			System.out.println("Finished flush");
			byte[] buf = new byte[1024*8];
			int len = 0;
			while((len =ipt.read(buf))!=-1){
				String temp = new String(buf,0,len);
				str.append(temp);
			}
			socket.shutdownInput();
			System.out.println(str.toString());
			opt.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
