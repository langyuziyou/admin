package admin.controller;

import java.util.Random;

import admin.utils.PasswordUtil;

public class BaseController {
	/**
	 * 随机密码生成 
	 * @author yzj
	 * @version 2.0 2017年8月22日 下午3:40:36
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTUVWXYZ";     
	    String num = "0123456789";  
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	    	int number = 0;
	    	if(i%2==0){
	    		number = random.nextInt(base.length());     
	    		 sb.append(base.charAt(number));     
	    	}else
	    	{
	    		number = random.nextInt(num.length());     
	    		 sb.append(num.charAt(number));     
	    	}
	    }
	    return sb.toString();     
	 } 
	
	/**
	 * 
	* 方法名: changePassWord 
	* 描述: 输入一个账号 ，重置密码 可以是 登陆密码，也可以是交易密码 
	* 参数: @param accountName
	* 参数: @return    设定文件 
	* 时间:2018年4月10日下午4:23:22 
	* 返回: String    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public static String changePassWord(String accountName) {
		String str = getRandomString(8);
		String password = PasswordUtil.encrytPwd(str, accountName);
		return password;
	}

}
