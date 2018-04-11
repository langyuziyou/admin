package admin.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	Map<String, Object> userList(Map<String,Object> map);
	
	Integer addUser(Map<String,Object> map);

	Integer editUser(Map<String,Object> map);

	Map<String, Object> findById(String id);
	
	Map<String, Object> findByAccountName(String name,String pass);

	Integer delUser(String id);

	Integer editUserPassWord(String accountName, String realPass);

}
