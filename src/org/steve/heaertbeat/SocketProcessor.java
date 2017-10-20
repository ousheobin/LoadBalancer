package org.steve.heaertbeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.alibaba.fastjson.JSON;

public class SocketProcessor implements Runnable {

	Socket socket = new Socket();
	private ClientStatus clientStatus;

	private static final String READ_HEARTBEAT_COMMON = "ReAd SyStEm StatuS...";

	public SocketProcessor(Socket socket, ClientStatus clientStatus) {
		super();
		this.socket = socket;
		this.clientStatus = clientStatus;
	}

	@Override
	public void run() {
		try {
			InputStream ipt = socket.getInputStream();
			OutputStream opt = socket.getOutputStream();
			StringBuffer str = new StringBuffer();
			byte[] buf = new byte[1024*8];
			int len = 0;
			while((len =ipt.read(buf))!=-1){
				String temp = new String(buf,0,len);
				str.append(temp);
				System.out.println(temp);
			}
			if(str.toString().equals(READ_HEARTBEAT_COMMON)){
				System.out.println("Debug:"+JSON.toJSONString(clientStatus));
				opt.write(JSON.toJSONBytes(clientStatus));
				opt.flush();
				ipt.close();
				opt.close();
				socket.close();
			}else{
				opt.close();
				ipt.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
