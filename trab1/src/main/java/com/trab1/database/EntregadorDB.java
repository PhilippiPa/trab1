package com.trab1.database;

import java.util.LinkedList;
import java.util.List;

import com.trab1.entity.Entregador;

public class EntregadorDB {

	private List<Entregador> database = new LinkedList<Entregador>();

	public EntregadorDB() {
		createEntregador("1", "bisteca1", 1);
		createEntregador("2", "bisteca2", 2);
		createEntregador("3", "bisteca3", 3);
		createEntregador("4", "bisteca4", 4);
	}

	public Entregador createEntregador(String cpf, String nome, int capacidade) {
		Entregador e = null;
		for (Entregador entregador : database) {
			if (entregador.getCpf().equals(cpf)) {
				e = entregador;
			}
		}
		if (e == null) {
			e = new Entregador();
			e.setCapacidadeVeiculo(capacidade);
			e.setCpf(cpf);
			e.setNome(nome);
			database.add(e);
			return e;
		}
		return null;
	}

	public boolean removeEntregador(String cpf) {
		Entregador toRemove = null;
		for (Entregador entregador : database) {
			if (entregador.getCpf().equals(cpf)) {
				toRemove = entregador;
			}
		}
		if (toRemove != null) {
			database.remove(toRemove);
			return true;
		}
		return false;
	}

	public Entregador updateEntregador(String cpf, String nome, int capacidade) {
		Entregador e = null;
		for (Entregador entregador : database) {
			if (entregador.getCpf().equals(cpf)) {
				e = entregador;
			}
		}
		if (e != null) {
			e.setCapacidadeVeiculo(capacidade);
			// e.setCpf(cpf);
			e.setNome(nome);
		}
		return e;
	}

	public List<Entregador> listEntregadores() {
		return this.database;
	}

}
