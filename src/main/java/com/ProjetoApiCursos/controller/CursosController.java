package com.ProjetoApiCursos.controller;

import java.util.List;

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

import com.ProjetoApiCursos.entities.Cursos;
import com.ProjetoApiCursos.service.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cursos", description = "API REST DE GERECIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursosController {
	
	private final CursosService cursosService;

	@Autowired
	public CursosController(CursosService cursosService) {
		this.cursosService = cursosService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um cursos por ID")
	public ResponseEntity<Cursos> getCursosById(@PathVariable Long id) {
		Cursos Cursos = cursosService.getCursosById(id);
		if (Cursos != null) {
			return ResponseEntity.ok(Cursos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Cursos>> getAllCursos() {
		List<Cursos> Cursos = cursosService.getAllCursos();
		return ResponseEntity.ok(Cursos);
	}

	@PostMapping
	@Operation(summary = "Cadastra um cursos")
	public ResponseEntity<Cursos> criarCursos(@RequestBody @Valid Cursos cursos) {
		Cursos criarCursos = cursosService.salvarCursos(cursos);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCursos);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um cursos")
	public ResponseEntity<Cursos> updateCursos(@PathVariable Long id, @RequestBody @Valid Cursos cursos) {
		Cursos updatedCursos = cursosService.updateCursos(id, cursos);
		if (updatedCursos != null) {
			return ResponseEntity.ok(updatedCursos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um cursos")
	public ResponseEntity<String> deleteCursos(@PathVariable Long id) {
		boolean deleted = cursosService.deleteCursos(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
