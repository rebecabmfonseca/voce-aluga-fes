package application.model;

public class Cliente extends Pessoa
{
	//nome, cpf, dataNasc, endereco, telefone e email herdados de Pessoa
	String CNH;

	public Cliente(String CPF, String nome, String CNH, String telefone, String email, int diaNasc, int mesNasc, Integer anoNasc,
				   String rua, String cidade, int numero, String CEP)
	{
		super(CPF, nome, telefone, email, diaNasc, mesNasc, anoNasc, rua, cidade, numero, CEP);
		if(ValidarCNH(CNH))	this.CNH = CNH;
	}

	public Cliente(String CPF, String nome, String CNH, String telefone, String email, int diaNasc, int mesNasc, Integer anoNasc,
				   String rua, String cidade, int numero, String CEP, Integer complemento)
	{
		super(CPF, nome, telefone, email, diaNasc, mesNasc, anoNasc, rua, cidade, numero, CEP, complemento);
		if(ValidarCNH(CNH))	this.CNH = CNH;
	}

	public boolean ValidarCNH(String CNH)
	{
		//to do
		return true;
	}

	public boolean AtualizarCNH(String novoCNH)
	{
		//checar se CNH e valido
		if(!ValidarCNH(novoCNH))
		{
			System.out.println("CNH invalido!");
			return false;
		} else
		{
			//atualizar CNH
			this.CNH = novoCNH;
			return true;
		}
	}
}

