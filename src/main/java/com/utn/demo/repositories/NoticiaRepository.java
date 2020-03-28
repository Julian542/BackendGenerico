package com.utn.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.demo.entities.Noticia;

@Repository
public interface NoticiaRepository  extends JpaRepository<Noticia, Long>{
	
	@Query("from Noticia n where n.titulo_de_la_noticia like %?1% or n.resumen_de_la_noticia like %?1%")
	List<Noticia> buscarPorNombre(String consulta, Pageable pageable);
	
	@Query("from Noticia n where n.idEmpresa.id = ?1 order by n.fecha_publicacion")
	List<Noticia> buscarPorEmpresa(int id, Pageable pageable);
	
	@Query("SELECT MAX(id) from Noticia")
	String maximoIDnoticia();
}
