package admin.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import admin.dao.UserDao;

@Repository
public class UserDaoImpl extends CommonDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Override
	public Map<String, Object> userList(Map<String, Object> paraList) {
		Integer page = Integer.parseInt(paraList.get("page").toString());
		Integer pageSize = Integer.parseInt(paraList.get("pageSize").toString());
		// conditions
		String name = paraList.get("name").toString();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer baseSql = new StringBuffer();
		baseSql.append(" SELECT * FROM `ddt_user`  ");
		baseSql.append(" WHERE 1=1  ");
		
		StringBuffer sql = new StringBuffer();
		sql.append(baseSql.toString());
		int count = 0;
		int pageCount = 1;// 记录总页数
		
		if (!name.equals("")) {
			sql.append(" AND  USER_ACCOUNT Like ?");
			paramList.add("%" + name + "%");
		}
		
		// 查询总数
		count = count(sql.toString(), paramList.toArray());

		sql.append(" ORDER BY ddt_user.`create_time` DESC LIMIT ?,? ");
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

	/**
	 * 获取 用户信息 ，登入时用 
	 */
	public Map<String, Object> findByAccountName(String name, String pass) {
		Map<String, Object> result;
		if(pass!=null) {
			String sql = " SELECT * FROM ddt_user where USER_ACCOUNT = ? and PASSWORD = ? ";
			result = jdbcTemplate.queryForMap(sql,new Object[] { name,pass });
		}else
		{
			String sql = " SELECT * FROM ddt_user where USER_ACCOUNT = ? ";
			result = jdbcTemplate.queryForMap(sql,new Object[] { name});
		}
		return result;
	}

	/**
	 * 修改用户密码 
	 */
	public Integer editUserPassWord(String accountName, String realPass) {
		 String sql = " update ddt_user set PASSWORD =:pass where USER_ACCOUNT = :accountName ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("pass", realPass); 
	      paramSource.addValue("accountName", accountName);
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}

}
