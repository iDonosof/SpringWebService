package siglo21.springboot.backend.apirest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IReportesDao;
import siglo21.springboot.backend.apirest.models.entity.Reportes;

@Service
public class ReportesServiceImpl implements IReportesService{

	@Autowired
	private IReportesDao reportesDao;

	@Override
	@Transactional(readOnly = true)
	public Reportes getReports() {
		return reportesDao.getReports();
	}
}
