package com.trab1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trab1.entity.Entregador;
import com.trab1.repository.EntregadorRepository;

@RestController()
@RequestMapping("/entregador")
public class EntregadorController {

	@Autowired
	private EntregadorRepository entregadorRepo;

	@GetMapping("/list")
	public Iterable<Entregador> list() {
		// http://localhost:8080/entregador/list
		return entregadorRepo.findAll();
	}

	@GetMapping("/remove")
	public boolean remove(@RequestParam(value = "cpf") String cpf) {
		// http://localhost:8080/entregador/remove?cpf=2
		Optional<Entregador> e = entregadorRepo.findById(cpf);
		if(!e.isEmpty()) {
			entregadorRepo.delete(e.get());
			return true;
		}
		return false;
	}

	@GetMapping("/create")
	public Entregador create(@RequestParam(value = "cpf") String cpf, @RequestParam(value = "nome") String nome,
			@RequestParam(value = "capacidade") int capacidade) {
		// exemplo de requisicao
		// http://localhost:8080/entregador/create?cpf=10&nome=leandro&capacidade=77
		Entregador newEntregador = new Entregador(cpf, nome, capacidade);
		entregadorRepo.save(newEntregador);
		return newEntregador;
	}

	@GetMapping("/update")
	public Entregador update(@RequestParam(value = "cpf") String cpf, @RequestParam(value = "nome") String nome,
			@RequestParam(value = "capacidade") int capacidade) {
		// exemplo de requisicao
		// http://localhost:8080/entregador/update?cpf=10&nome=leandro&capacidade=77
		Optional<Entregador> e = entregadorRepo.findById(cpf);
		if(!e.isEmpty()) {
			Entregador entregador = e.get();
			entregador.setCpf(cpf);
			entregador.setNome(nome);
			entregador.setCapacidadeVeiculo(capacidade);
			entregadorRepo.save(entregador);
			return entregador;
		}
		return null;
	}

}
