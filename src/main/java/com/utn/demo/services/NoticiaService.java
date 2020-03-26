package com.utn.demo.services;

import java.util.Optional;
import java.io.File;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.utn.demo.BackendLaboGenApplication;
import com.utn.demo.entities.Empresa;
import com.utn.demo.entities.Noticia;
import com.utn.demo.repositories.EmpresaRepository;
import com.utn.demo.repositories.NoticiaRepository;

@Service
public class NoticiaService extends ServicioGenerico<Noticia, NoticiaRepository>{
	
	protected final EmpresaRepository repositorio2;
	private String upload_folder = String.valueOf(BackendLaboGenApplication.getHome() +"\\files\\").replace("\\","/");
	
	public NoticiaService(EmpresaRepository repositorio2) {
		this.repositorio2 = repositorio2;
	}
	
	@Override
	public Noticia save(Noticia entityForm) throws Exception {
		try {
			
			Optional<Empresa> optionalEntity = repositorio2.findById(entityForm.getIdEmpresa().getId());
			Empresa empresa_indicada = optionalEntity.get();
			Noticia nuevaNoticia = entityForm;
			nuevaNoticia.setIdEmpresa(empresa_indicada);
			nuevaNoticia = repository.save(nuevaNoticia);
			return entityForm;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}
	
	//METODOS CRUD TERMINADOS Y FUNCIONANDO CORRECTAMENTE - METODOS PERSONALIZADOS COMIENZAN AQUI
	@Transactional
	public String getMaxID() throws Exception{
		try {
			 return repository.maximoIDnoticia();
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public String getPathImages() {
		return this.upload_folder;
	}
	
	@Transactional
	public void saveFile(MultipartFile file) throws IOException {
		System.out.println(BackendLaboGenApplication.getHome());
		 File directorio = new File(BackendLaboGenApplication.getHome()+"//files");
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                System.out.println("Directorio creado");
	            } else {
	                System.out.println("Error al crear directorio");
	            }
	        }
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	
	@Transactional
	public List<Noticia> getLast( int id ) throws Exception{
		Pageable firstPageWithFiveElements = PageRequest.of(0, 5);
		try {
			List<Noticia> entities = repository.buscarPorEmpresa(id, firstPageWithFiveElements);
			
			return entities;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public List<Noticia> buscarPorNombre(String consulta) throws Exception{
		Pageable mypageable = PageRequest.of(0, 20);
		try {
			List<Noticia> entities = repository.buscarPorNombre(consulta, mypageable);
			return entities;
		}catch (Exception e) {
			throw new Exception();
		}
	}
}
