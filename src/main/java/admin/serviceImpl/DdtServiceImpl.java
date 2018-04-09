package admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.dao.DdtDao;
import admin.service.DdtService;
@Service("ddtService")
public class DdtServiceImpl implements DdtService{
	@Autowired
	private DdtDao ddtDao;
	
	@Override
	public List<Map<String, Object>> ddtList() {
		return ddtDao.ddtList();
	}

	@Override
	public Integer addDdt(String name, String imageUrl) {
		return ddtDao.addDdt(name,imageUrl);
	}

	@Override
	public Integer editDdt(long id, String name, String imageUrl) {
		return ddtDao.editDdt(id,name,imageUrl);
	}

	@Override
	public Map<String, Object> findById(String id) {
		return ddtDao.findById(id);
	}

	@Override
	public Integer delDdt(long id) {
		return ddtDao.delDdt(id);
	}

	@Override
	public List<Map<String, Object>> findByPid(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
