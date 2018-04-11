package admin.dao;

import java.util.Map;

public interface GroupDao {
	Map<String, Object> groupList(Map<String,Object> map);
	
	Integer addGroup(Map<String,Object> map);

	Integer editGroup(Map<String,Object> map);

	Map<String, Object> findById(String id);

	Integer delGroup(String id);

	Map<String, Object> permissionList(Map<String, Object> map);

	Map<String, Object> groupPermission(String groupId);

	void saveGroupPermission(String groupId, String permissionIds);

	Integer delGroupPermissionRelation(String groupId);

}
