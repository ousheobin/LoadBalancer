package org.steve.monitor;

import java.util.Properties;

/**
 * 操作系统类型检查
 * @author Ou Sheobin - me@oushaobin.cn
 *
 */
public class OperatingSystem {
	
	public static String check(){
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name").toLowerCase();
		if(os.contains("windows")){
			/*Windows Operating System. 微软Windows系统*/
			return "Windows";
		}else if(os.contains("linux")){
			/*Linux Operating System. Linux操作系统*/
			return "Linux";
		}else{
			return "Unknow";
		}
	}
	
}
