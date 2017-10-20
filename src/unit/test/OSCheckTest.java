package unit.test;

import java.util.Properties;

public class OSCheckTest {

	public static void main(String[] args) {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name").toLowerCase();
		if(os.contains("windows")){
			/*Windows Operating System. ΢��Windowsϵͳ*/
			System.out.println(os);
		}else{
			/*Linux Operating System. Linux����ϵͳ*/
			System.out.println(os);
		}

	}
}
