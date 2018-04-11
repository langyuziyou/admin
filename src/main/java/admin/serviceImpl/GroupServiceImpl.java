package admin.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.dao.GroupDao;
import admin.service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService{
	private static final Logger LOGGER = Logger.getLogger(GroupServiceImpl.class);

	@Autowired
	private GroupDao groupDao;
	
	@Override
	public Map<String, Object> groupList(Map<String, Object> map) {
		return groupDao.groupList(map);
	}

	@Override
	public Integer addGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer editGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delGroup(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> permissionList(Map<String, Object> map) {
		return groupDao.permissionList(map);
	}

	@Override
	public Map<String, Object> groupPermission(String groupId) {
		return groupDao.groupPermission(groupId);
	}

	/**
	 * 保存当前 组的权限 permissionIds用',' 隔开 
	 * 1.删除当前 用户组的权限
	 * 2.新增 用户组权限 
	 */
	public Map<String, Object> saveGroupPermission(String groupId, String permissionIds) {
		Map<String, Object> m = new HashMap<>();
		try {
			// 删除当前 用户组的权限 
			groupDao.delGroupPermissionRelation(groupId);
			groupDao.saveGroupPermission(groupId,permissionIds);
		}catch(Exception e)
		{
			LOGGER.error("saveGroupPermission:" + e.toString());
			m.put("result", 0);
		}
		m.put("result", 1);
		return m;
	}

}
