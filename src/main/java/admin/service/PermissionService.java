package admin.service;

import java.util.List;
import java.util.Map;

public interface PermissionService {
	List<Map<String, Object>> permissionListByUserId(String userId);
}
