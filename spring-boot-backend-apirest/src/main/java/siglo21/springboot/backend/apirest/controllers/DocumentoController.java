package siglo21.springboot.backend.apirest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siglo21.springboot.backend.apirest.models.entity.Documento;
import siglo21.springboot.backend.apirest.models.services.IDocumentoService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/documentos")
public class DocumentoController {
	
	@Autowired
	private IDocumentoService documentoService;

	@GetMapping("/obtener-documentos")
	public ResponseEntity<?> ObtenerDocumentos() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Documento> documentos = documentoService.findAll();
			if(documentos == null || documentos.size() == 0) {
				response.put("message", "No se encuentra documentos en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Documento>>(documentos, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-documento/{id}")
	public ResponseEntity<?> BuscarDocumento(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Documento documento = documentoService.findById(id);
			if(documento == null) {
				response.put("message", "Documento no encontrado en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Documento>(documento, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-documento")
	public ResponseEntity<?> CrearDocumento(@RequestBody Documento documento) {
		Map<String, Object> response = new HashMap<>();
		try {
			Documento documentoOut = documentoService.save(documento);
			if(documentoOut == null) {
				response.put("message", "Ocurrio un error al ingresar el documento a la base de datos, compruebe los datos ingresados");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			documentoOut.setFecha(new Date(documentoOut.getFecha().getTime() - TimeUnit.DAYS.toMillis( 1 )));
			return new ResponseEntity<Documento>(documentoOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-documento/{id}")
	public ResponseEntity<?> ActualizarDocumento(@RequestBody Documento documento, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Documento documentoActual = documentoService.findById(id);
			if(documentoActual == null) {
				response.put("message", "No se encontro el documento en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			documentoActual.setTipo(documento.getTipo());
			documentoActual.setHora(documento.getHora());
			documentoActual.setFecha(documento.getFecha());
			documentoActual = documentoService.save(documentoActual);
			if(documentoActual == null) {
				response.put("message", "Ocurrion un error al actualizar el documento en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Documento>(documento, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-documento/{id}")
	public ResponseEntity<?> EliminarDocumento(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			documentoService.delete(id);
			response.put("message", "Documento eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
