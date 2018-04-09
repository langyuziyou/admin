package admin.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;



import admin.dao.DdtDao;
import admin.utils.DateUtils;

@Repository
public class DdtDaoImpl extends CommonDaoImpl implements DdtDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Map<String, Object>> ddtList() {
		String sql = " SELECT * FROM `ddt` LIMIT 0,9; ";
		List<Map<String, Object>> categoryList = jdbcTemplate.queryForList(sql);
		return categoryList;
	}

	@Override
	public Integer addDdt(String name, String imageUrl) {
		  String currentTime = DateUtils.toStr();
		  String sql = " INSERT INTO ddt(NAME,IMAGEURL,CREATE_TIME,CREATE_BY,UPDATE_TIME,UPDATE_BY) VALUES(:name,:imageUrl,:createTime,:createBy,:updateTime,:updateBy) ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("name", name); 
	      paramSource.addValue("imageUrl", imageUrl); 
	      paramSource.addValue("createTime", currentTime); 
	      paramSource.addValue("createBy", "admin"); 
	      paramSource.addValue("updateTime", currentTime); 
	      paramSource.addValue("updateBy", "admin"); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}

	@Override
	public Integer editDdt(long id, String name, String imageUrl) {
		 String sql = " update ddt set name =:name,imageUrl=:imageUrl where ID = :id ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();  
	      paramSource.addValue("name", name); 
	      paramSource.addValue("imageUrl", imageUrl); 
	      paramSource.addValue("id", id); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}

	@Override
	public Map<String, Object> findById(String id) {
		String sql = " SELECT * FROM ddt where ID = ? ";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,new Object[] { id });
		return result;
	}

	@Override
	public Integer delDdt(long id) {
		  String sql = " delete from ddt where ID = :id ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("id", id); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}



}
