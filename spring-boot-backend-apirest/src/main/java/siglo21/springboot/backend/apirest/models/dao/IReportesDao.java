package siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import siglo21.springboot.backend.apirest.models.entity.Reportes;

public interface IReportesDao extends CrudRepository<Reportes, Long>{

	@Query("select fn_total_de_ingresos_mes_mas_ingreso as INGRESO_MENSUAL, fn_mes_con_mas_ingresos as MES_MAYOR_INGRESO from dual")
	public Reportes getReports();
}
