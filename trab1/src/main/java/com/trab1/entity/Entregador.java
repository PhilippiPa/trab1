package com.trab1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "engregador")
public class Entregador {
	
	@Id
	private String cpf; 
	private String nome; 
	private int capacidadeVeiculo;
	
	public Entregador() {
		
	}
	
	public Entregador(String cpf, String nome, int capacidade) {
		this.cpf = cpf;
		this.nome = nome;
		this.capacidadeVeiculo = capacidade;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCapacidadeVeiculo() {
		return capacidadeVeiculo;
	}
	public void setCapacidadeVeiculo(int capacidadeVeiculo) {
		this.capacidadeVeiculo = capacidadeVeiculo;
	}
}
