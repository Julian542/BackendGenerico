package com.utn.demo.services;
import java.util.List;

public interface IservicioGenerico <E>{
	
	public E findById(int id) throws Exception;
	
	public E save(E entityForm) throws Exception;
	
	public E update (int id, E entityForm) throws Exception;
	
	public List<E> findAll (int page, int size) throws Exception;
	
	public boolean delete (int id) throws Exception;
}
