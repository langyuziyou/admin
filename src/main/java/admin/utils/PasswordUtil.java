package admin.utils;

import org.apache.shiro.crypto.hash.Md5Hash;



/**
 * 密码工具类
  * @title  
  * @author liulicai
  * @date 2016年8月18日 上午11:12:28
  * @version 1.0.0 
  * @since JDK 1.8
 */
public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String encrytPwd(char[] password, String salt) {
        Md5Hash md5Hash = new Md5Hash(password, salt);
        return md5Hash.toBase64();
    }
    
    public static String encrytPwd(String password, String salt) {
    	char[] charPwd =StringUtil.StringToChar(password);
    	Md5Hash md5Hash = new Md5Hash(charPwd, salt);
    	return md5Hash.toBase64();
    }
    
    public static void main(String[] args) {
    System.out.println(encrytPwd("88888","admin"));	
	}

}
