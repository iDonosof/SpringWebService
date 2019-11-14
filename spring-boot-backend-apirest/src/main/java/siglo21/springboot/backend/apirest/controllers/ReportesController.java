package siglo21.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> ObtenerReportes() {
		Map<String, Object> response = new HashMap<>();
		try {
			Reportes reportes = reportesService.getReports();
			if(reportes == null) {
				response.put("message", "Ocurrio un error al conseguir los reportes");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Reportes>(reportes, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
