package admin.service;

import java.util.Map;

public interface GroupService {
	
	Map<String, Object> groupList(Map<String,Object> map);
	
	Map<String, Object> permissionList(Map<String,Object> map);
	
	Integer addGroup(Map<String,Object> map);

	Integer editGroup(Map<String,Object> map);

	Map<String, Object> findById(String id);

	Integer delGroup(String id);

	Map<String, Object> groupPermission(String groupId);

	Map<String, Object> saveGroupPermission(String groupId, String permissionIds);

}
