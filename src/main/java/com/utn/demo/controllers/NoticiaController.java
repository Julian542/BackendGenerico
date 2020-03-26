package com.utn.demo.controllers;

import java.io.IOException;

import javax.transaction.Transactional;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utn.demo.entities.Noticia;
import com.utn.demo.services.NoticiaService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping(path = "api/v2/noticia")
public class NoticiaController extends ControllerGenerico<Noticia, NoticiaService>{
	@GetMapping("/maxid")
	@Transactional
	public ResponseEntity getMaxID(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(JSONObject.quote(service.getMaxID()));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\":\"Ha ocurrido un error al obtener el id maximo de noticia\"}");
		}
	}
	
	@GetMapping("/recoverUpload")
	@Transactional
	public ResponseEntity getPathImages() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(JSONObject.quote(service.getPathImages()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\":\"Ha ocurrido un error al obtener la direccion de la imagen\"}");
		}
	}
	
	@PostMapping("/upload")
	@Transactional
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<Object>("Seleccionar un Archivo", HttpStatus.OK);
		}
		try {
			service.saveFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);

	}
	
	@GetMapping("/searchEmpresa/{id}")
	@Transactional
	public ResponseEntity getLast(@PathVariable("id") int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getLast(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\":\"Ha ocurrido un error en el metodo getLast\"}");
		}
	}

	@GetMapping("/search/{nombre}")
	@Transactional
	public ResponseEntity buscarPorNombre(@PathVariable("nombre") String nombre) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNombre(nombre));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\":\"Ha ocurrido un error en el metodo getAll\"}");
		}
	}
	
}
