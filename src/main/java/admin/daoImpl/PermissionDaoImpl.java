package admin.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import admin.dao.PermissionDao;

@Repository
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Map<String, Object>> permissionListByUserId(String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" SELECT `ddt_permission`.`DDT_PERMISSION_ID`,`ddt_permission`.`PERMISSION_NAME` ");
/*		sql.append(" ,`ddt_permission`.`HREF`,`ddt_permission`.`LEVEL`,`ddt_permission`.`PARENT_ID`, ddt_permission.`TYPE`");*/
		sql.append("  FROM `ddt_user` ");
		sql.append(
				" LEFT JOIN `ddt_user_group_relation` ON `ddt_user`.`USER_ID` = `ddt_user_group_relation`.`DDT_USER_ID` ");
		sql.append(" LEFT JOIN `ddt_group` ON `ddt_user_group_relation`.`DDT_GROUP_ID` = `ddt_group`.`DDT_GROUP_ID` ");
		sql.append(
				" LEFT JOIN `ddt_group_permission_relation` ON `ddt_group_permission_relation`.`DDT_GROUP_ID` = `ddt_group`.`DDT_GROUP_ID` ");
		sql.append(
				" LEFT JOIN `ddt_permission` ON `ddt_permission`.`DDT_PERMISSION_ID` = `ddt_group_permission_relation`.`DDT_PERMISSION_ID` ");
		sql.append(" WHERE `ddt_user`.`USER_ID` = ? ");

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(), new Object[] { userId });
		return list;
	}

}
