package admin.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import admin.dao.GroupDao;

@Repository
public class GroupDaoImpl extends CommonDaoImpl implements GroupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Map<String, Object> groupList(Map<String, Object> paraList) {

		Integer page = Integer.parseInt(paraList.get("page").toString());
		Integer pageSize = Integer.parseInt(paraList.get("pageSize").toString());
		// conditions
		String name = paraList.get("name").toString();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();

		StringBuffer baseSql = new StringBuffer();
		baseSql.append(" SELECT * FROM `ddt_group`  ");
		baseSql.append(" WHERE 1=1  ");

		StringBuffer sql = new StringBuffer();
		sql.append(baseSql.toString());
		int count = 0;
		int pageCount = 1;// 记录总页数

		if (!name.equals("")) {
			sql.append(" AND  GROUP_NAME Like ?");
			paramList.add("%" + name + "%");
		}

		// 查询总数
		count = count(sql.toString(), paramList.toArray());

		sql.append(" ORDER BY ddt_group.`create_time` DESC LIMIT ?,? ");
		paramList.add((page - 1) * pageSize);
		paramList.add(pageSize);

		list = jdbcTemplate.queryForList(sql.toString(), paramList.toArray());

		/****
		 * 计算出 总页数
		 */
		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}

		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		result.put("pageCount", pageCount);

		return result;

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

	/**
	 * 所有 权限列表  
	 */
	public Map<String, Object> permissionList(Map<String, Object> paraList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();

		StringBuffer baseSql = new StringBuffer();
		baseSql.append(" SELECT * FROM `ddt_permission`  ");
		baseSql.append(" WHERE 1=1  ");

		StringBuffer sql = new StringBuffer();
		sql.append(baseSql.toString());

		list = jdbcTemplate.queryForList(sql.toString(), paramList.toArray());
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);

		return result;

	}

	/**
	 * 用户组对应的权限列表 
	 */
	public Map<String, Object> groupPermission(String groupId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();

		StringBuffer baseSql = new StringBuffer();
		baseSql.append(
				" SELECT `ddt_permission`.`DDT_PERMISSION_ID`,`ddt_permission`.`PERMISSION_NAME` FROM `ddt_group` ");
		baseSql.append(
				" LEFT JOIN `ddt_group_permission_relation` ON `ddt_group_permission_relation`.`DDT_GROUP_ID` = `ddt_group`.`DDT_GROUP_ID` ");
		baseSql.append(
				" LEFT JOIN `ddt_permission` ON `ddt_group_permission_relation`.`DDT_PERMISSION_ID` = `ddt_permission`.`DDT_PERMISSION_ID`  ");
		
		baseSql.append(" WHERE `ddt_group`.`DDT_GROUP_ID` = ? ");

		StringBuffer sql = new StringBuffer();
		sql.append(baseSql.toString());
		
		paramList.add(groupId);
		list = jdbcTemplate.queryForList(sql.toString(), paramList.toArray());
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);

		return result;
	}

	/**
	 * 批量 保存  ddt_group_permission_relation
	 */
	public void saveGroupPermission(String groupId, String permissionIds) {
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO `ddt_group_permission_relation` (DDT_GROUP_ID,DDT_PERMISSION_ID) ");
		sb.append(" VALUES (?,?) ");
		String[] ids = permissionIds.split("/,");
		for(String str:ids) {
			batchArgs.add(new Object[] {groupId,str});
		}
		jdbcTemplate.batchUpdate(sb.toString(),batchArgs);
	}

	/**
	 * 删除组关系 
	 */
	public Integer delGroupPermissionRelation(String groupId) {
		  String sql = " delete from ddt_group_permission_relation where DDT_GROUP_ID = :groupId ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("groupId", groupId); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}

}
