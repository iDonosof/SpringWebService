package siglo21.springboot.backend.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siglo21.springboot.backend.apirest.models.entity.Reportes;
import siglo21.springboot.backend.apirest.models.services.IReportesService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/reportes")
public class ReportesController {
	
	@Autowired
	private IReportesService reportesService;
	
	@GetMapping("/obtener-reportes")
	public Reportes ObtenerReportes() {
		return reportesService.getReports();
	}
}
