package application.model;

public class Cliente extends Pessoa
{
	//nome, cpf, dataNasc, endereco, telefone e email herdados de Pessoa
	String CNH;

	public Cliente(String CPF, String nome, String CNH, String telefone, String email, int diaNasc,
			int mesNasc, int anoNasc, String endereco, String cidade, int numero, String CEP)
	{
		super(CPF, nome, telefone, email, diaNasc, mesNasc, anoNasc, endereco, cidade, numero, CEP);
		this.CNH = CNH;
	}

	public Cliente(String CPF, String nome, String CNH, String telefone, String email, int diaNasc, int mesNasc, Integer anoNasc,
				   String rua, String cidade, int numero, String CEP, Integer complemento)
	{
		super(CPF, nome, telefone, email, diaNasc, mesNasc, anoNasc, rua, cidade, numero, CEP, complemento);
		this.CNH = CNH;
	}
	
	public String toString() {
		return "Nome: " + this.nome + "\nCPF: " + this.CPF + "\nCNH: " + this.CNH
				+ "\nEndereco: " + this.endereco + ", " + this.numero + ", " +
				this.cidade + "\nCEP: " + this.CEP + "\nTelefone: " + this.telefone +
				"\nData de Nascimento: " + this.diaNasc + "/" + this.mesNasc + "/" + this.anoNasc;
	}
}

