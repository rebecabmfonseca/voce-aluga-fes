package application.model;

public class Pessoa
{
	String CPF;
	String nome;
	String telefone;
	int diaNasc;
	int mesNasc;
	int anoNasc;
	String endereco;
	int numero;
	String cidade;
	String email;
	String CEP;
	Integer complemento;

	public Pessoa(String CPF, String nome, String telefone, String email, int diaNasc,
			int mesNasc, int anoNasc, String endereco, String cidade, int numero, String CEP)
	{
		this.CPF = CPF;
		this.telefone = telefone;
		this.diaNasc = diaNasc;
		this.mesNasc = mesNasc;
		this.anoNasc = anoNasc;
		this.endereco = endereco;
		this.numero = numero;
		this.cidade = cidade;
		this.nome = nome;
		this.email = email;
		this.CEP = CEP;
		this.complemento = null;
	}

	public Pessoa(String CPF, String nome, String telefone, String email, int diaNasc,
			int mesNasc, int anoNasc, String endereco, String cidade, int numero, String CEP, Integer complemento)
	{
		this.CPF = CPF;
		this.telefone = telefone;
		this.diaNasc = diaNasc;
		this.mesNasc = mesNasc;
		this.anoNasc = anoNasc;
		this.endereco = endereco;
		this.numero = numero;
		this.cidade = cidade;
		this.nome = nome;
		this.email = email;
		this.CEP = CEP;
		this.complemento = complemento;
	}

}
