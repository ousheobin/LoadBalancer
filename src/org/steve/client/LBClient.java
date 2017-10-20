package org.steve.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.steve.heaertbeat.ClientStatus;
import org.steve.heaertbeat.MonitorHeartbeat;
import org.steve.heaertbeat.NetworkListener;
import org.steve.heaertbeat.ShutDownListener;
import org.steve.monitor.OperatingSystem;
import org.steve.monitor.SystemMonitor;

public class LBClient {
	
	private static final String MONITOR_BASE_PACKAGE = "org.steve.monitor";
	private static final String propertiesPath = "conf/client.properties";

	private static Properties properties;
	private static int localPort = 9996;
	private static int stopPort = 9997;

	public static void main(String[] args) {
		ClientStatus clientStatus = new ClientStatus();
		SystemMonitor systemMonitor = null;
		String operatingSystem = OperatingSystem.check();
		try {
			systemMonitor = (SystemMonitor) Class.forName(MONITOR_BASE_PACKAGE+"."+operatingSystem+"Monitor").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		properties = new Properties();
		readConf();
		/* 启动 系统心跳监考线程*/
		Thread monitor = new Thread(new MonitorHeartbeat(systemMonitor, clientStatus));
		monitor.setName("System Monitor - Heartbeat");
		monitor.setDaemon(true);
		monitor.start();
		/* 启动 Network监听线程*/
		Thread networkListener = new Thread(new NetworkListener(localPort,clientStatus));
		networkListener.setName("Network listener - Heartbeat");
		networkListener.setDaemon(true);
		networkListener.start();
		/* 启动 关闭指令监听线程*/
		Thread stopListener = new Thread(new ShutDownListener(stopPort,clientStatus));
		stopListener.start();
		System.out.println("Lite Load Balance Server - Heartbeat Detected Client");
		System.out.println("The client has listened the port:"+localPort);
	}
	
	private static void readConf(){
		try {
			File file = new File(propertiesPath);
			InputStream in = new BufferedInputStream(new FileInputStream(file));  
			properties.load(in);
			localPort = Integer.valueOf(properties.getProperty("client.port", "9996"));
			stopPort =  Integer.valueOf(properties.getProperty("client.stop.port", "9997"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
