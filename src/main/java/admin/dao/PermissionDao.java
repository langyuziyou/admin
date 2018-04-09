package admin.dao;

import java.util.List;
import java.util.Map;

public interface PermissionDao {
	List<Map<String, Object>> permissionListByUserId(String userId);
}
