package quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyJob {

	public void run() {
		System.out.println("任务执行----"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
}
