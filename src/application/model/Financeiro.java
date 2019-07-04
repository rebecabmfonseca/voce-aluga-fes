package application.model;

import java.util.List;

public class Financeiro {
	private String formaPagamento;
	private double valor;


	public Financeiro(){

	}

	public Financeiro(String formaPagamento, double valor) {
		this.formaPagamento = formaPagamento;
		this.valor = valor;
	}


	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

}
