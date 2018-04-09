package admin.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CommonDaoImpl {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	/**
	 * 当查询结果数为0时，会报异常，则默认报异常为没有结果
	 */
	public int count(String sql, Object... o) {
		String newSql = "SELECT COUNT(1) FROM ( " + sql + " ) t";
		try {
			if (o == null) {
				return jdbcTemplate.queryForObject(newSql, Integer.class);
			}
			return jdbcTemplate.queryForObject(newSql, o, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public int count(String sql) {
		return count(sql, null);
	}


}
