package com.ProjetoApiCursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoApiCursos.entities.Cursos;
import com.ProjetoApiCursos.repository.CursosRepository;
@Service
public class CursosService {
	
	@Autowired
	private final CursosRepository cursosRepository;
	
	@Autowired
    public CursosService(CursosRepository cursosRepository) {
        this.cursosRepository = cursosRepository;
    }

	 public List<Cursos> getAllCursos() {
	        return cursosRepository.findAll();
	    }

	    public Cursos getCursosById(Long id) {
	        Optional<Cursos> cursos = cursosRepository.findById(id);
	        return cursos.orElse(null);
	    }

	    public Cursos salvarCursos(Cursos cursos) {
	        return cursosRepository.save(cursos);
	    }

	    public Cursos updateCursos(Long id, Cursos updatedCursos) {
	        Optional<Cursos> existingCursos = cursosRepository.findById(id);
	        if (existingCursos.isPresent()) {
	            updatedCursos.setId(id);
	            return cursosRepository.save(updatedCursos);
	        }
	        return null;
	    }

	    public boolean deleteCursos(Long id) {
	        Optional<Cursos> existingCursos = cursosRepository.findById(id);
	        if (existingCursos.isPresent()) {
	        	cursosRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }

}
