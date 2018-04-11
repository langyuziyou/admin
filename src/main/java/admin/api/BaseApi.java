package admin.api;

import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;

import admin.utils.PasswordUtil;

public class BaseApi {
	/**
	 * 
	* 方法名: exceptionStr 
	* 描述: 异常返回
	* 参数: @param sb
	* 参数: @param resaon
	* 参数: @return    设定文件 
	* 时间:2018年4月11日上午11:49:01 
	* 返回: StringBuffer    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public StringBuffer exceptionStr(StringBuffer sb, String resaon) {
		sb.append("[{\"success\":");
		sb.append(false);
		sb.append(",");
		sb.append("\"exception\":");
		sb.append("\"" + resaon + "\"");
		sb.append("}]");
		return sb;
	}

	/**
	 * 
	* 方法名: successStr 
	* 描述: 正确返回
	* 参数: @param sb
	* 参数: @param result
	* 参数: @return    设定文件 
	* 时间:2018年4月11日上午11:49:10 
	* 返回: StringBuffer    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public StringBuffer successStr(StringBuffer sb, Map<String, Object> result) {
		sb.append("[{\"success\":");
		sb.append(true);
		for (Map.Entry<String, Object> entry : result.entrySet()) {
			String key = entry.getKey().toString();
			Object value = entry.getValue();
			if(value!=null) {
				sb.append(",");
				if (value.getClass().toString().equals("class java.util.ArrayList")) {
					sb.append("\"" + key + "\":");
					sb.append(JSON.toJSON(value).toString());

				} else if (value.getClass().toString().equals("class java.lang.Integer")) {
					sb.append("\"" + key + "\":");
					sb.append(value);
				} else {
					sb.append("\"" + key + "\":");
					sb.append("\"" + value + "\"");
				}
			}

		}
		sb.append("}]");
		return sb;
	}
	
	
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
	
	/**
	 * 
	* 方法名: getRandomPassWord 
	* 描述: 获取 一个随机字符 
	* 参数: @return    设定文件 
	* 时间:2018年4月11日下午2:16:49 
	* 返回: String    返回类型 
	* 作者: yzj 
	* @throws
	 */
	public static String getRandomPassWord() {
		String str = getRandomString(8);
		return str;
	}

}
