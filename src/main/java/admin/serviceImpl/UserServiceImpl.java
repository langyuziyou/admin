package admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.dao.UserDao;
import admin.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, Object> userList(Map<String,Object> map) {
		return userDao.userList(map);
	}

	@Override
	public Integer addUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer editUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findByAccountName(String name,String pass) {
		return userDao.findByAccountName(name,pass);
	}

	/**
	 * 修改密码
	 */
	public Integer editUserPassWord(String accountName, String realPass) {
		return userDao.editUserPassWord(accountName,realPass);
	}

}
