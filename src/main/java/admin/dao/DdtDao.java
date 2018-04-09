package admin.dao;

import java.util.List;
import java.util.Map;

public interface  DdtDao {
	List<Map<String, Object>> ddtList();
	Integer addDdt(String name, String imageUrl);
	Integer editDdt(long id, String name, String imageUrl);
	Map<String, Object> findById(String id);
	Integer delDdt(long id);

}
