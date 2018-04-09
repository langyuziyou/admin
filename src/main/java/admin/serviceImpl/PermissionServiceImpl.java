package admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.dao.PermissionDao;
import admin.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public List<Map<String, Object>> permissionListByUserId(String userId) {
		// TODO Auto-generated method stub
		return permissionDao.permissionListByUserId(userId);
	}

}
