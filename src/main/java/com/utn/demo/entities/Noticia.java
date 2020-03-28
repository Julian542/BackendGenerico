package com.utn.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Noticia extends GenericEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;*/
	
	@Size(min = 1, max = 128)
	private String titulo_de_la_noticia;
	
	@Size(min = 1, max = 1024)
	private String resumen_de_la_noticia;
	
	@Size(min = 1, max = 128)
	private String imagen_noticia;
	
	@Size(min = 1, max = 20480)
	private String contenido_html;
	
	private char publicada;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_publicacion;
	
	@JsonIgnoreProperties(value = {"noticias"})
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEmpresa")
	private Empresa idEmpresa;
	
	public Noticia() {}
	
	public Noticia(String titulo_de_la_noticia, String resumen_de_la_noticia, String imagen_noticia,
			String contenido_html, char publicada, Date fecha_publicacion, Empresa idEmpresa) {
		this.titulo_de_la_noticia = titulo_de_la_noticia;
		this.resumen_de_la_noticia = resumen_de_la_noticia;
		this.imagen_noticia = imagen_noticia;
		this.contenido_html = contenido_html;
		this.publicada = publicada;
		this.fecha_publicacion = fecha_publicacion;
		this.idEmpresa = idEmpresa;
	}

	/*public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}*/

	public String getTitulo_de_la_noticia() {
		return titulo_de_la_noticia;
	}

	public void setTitulo_de_la_noticia(String titulo_de_la_noticia) {
		this.titulo_de_la_noticia = titulo_de_la_noticia;
	}

	public String getResumen_de_la_noticia() {
		return resumen_de_la_noticia;
	}

	public void setResumen_de_la_noticia(String resumen_de_la_noticia) {
		this.resumen_de_la_noticia = resumen_de_la_noticia;
	}

	public String getImagen_noticia() {
		return imagen_noticia;
	}

	public void setImagen_noticia(String imagen_noticia) {
		this.imagen_noticia = imagen_noticia;
	}

	public String getContenido_html() {
		return contenido_html;
	}

	public void setContenido_html(String contenido_html) {
		this.contenido_html = contenido_html;
	}

	public char getPublicada() {
		return publicada;
	}

	public void setPublicada(char publicada) {
		this.publicada = publicada;
	}

	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	

}
