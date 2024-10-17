package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Matricula;



public interface MatriculaDao {
	Matricula create(Matricula a);
	Matricula update(Matricula a);
	void delete(Long id);
	Optional<Matricula> read(Long id);
	List<Matricula> readAll();
}
