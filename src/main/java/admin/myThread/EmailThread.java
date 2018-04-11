package admin.myThread;

import org.apache.log4j.Logger;

import admin.api.UserApi;
import admin.utils.Email;

public class EmailThread extends Thread{
	private static final Logger LOGGER = Logger.getLogger(EmailThread.class);
	public String name;
	public String pass;
	public String email;
	
	/**
	 * 
	 * 创建一个新的实例 EmailThread.    
	 * @param name
	 * @param pass
	 * @param email
	 */
	public EmailThread(String email,String name,String pass){
		this.name = name;
		this.pass = pass;
		this.email = email;
	}

	public void run() {
		try {
			Email.sendMail(email, name, pass);
		} catch (Exception e) {
			LOGGER.error(" 邮件发送失败： SendEmail failed " + e.toString());
		}// 发送邮件 
	}

}
