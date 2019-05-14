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
	
	public String getCPF() {
		return this.CPF;
	}
	
	public String getDataNasc() {
		return this.diaNasc + "/" + this.mesNasc + "/" + this.anoNasc;
	}
	
	public String getCep() {
		return this.CEP;
	}
	
	public String getEndereco() {
		return this.endereco + ", " + this.numero + ", " + this.cidade;
	} 
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getTelefone() {
		return this.telefone;
	}


	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

}
