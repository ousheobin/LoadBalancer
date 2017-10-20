package org.steve.monitor;

import java.util.Properties;

/**
 * ����ϵͳ���ͼ��
 * @author Ou Sheobin - me@oushaobin.cn
 *
 */
public class OperatingSystem {
	
	public static String check(){
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name").toLowerCase();
		if(os.contains("windows")){
			/*Windows Operating System. ΢��Windowsϵͳ*/
			return "Windows";
		}else if(os.contains("linux")){
			/*Linux Operating System. Linux����ϵͳ*/
			return "Linux";
		}else{
			return "Unknow";
		}
	}
	
}
