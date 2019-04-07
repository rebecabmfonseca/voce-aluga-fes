package application.model;

public class Pessoa
{
	String CPF;
	String nome;
	Long telefone;
	Data dataNasc;
	Endereco endereco;
	String email;

	public Pessoa(String CPF, String nome, Long telefone, String email, int diaNasc, int mesNasc, Integer anoNasc,
				  String rua, String cidade, int numero, String CEP)
	{
		this.CPF = CPF;
		this.telefone = telefone;
		this.dataNasc = new Data(diaNasc, mesNasc, anoNasc);
		this.endereco = new Endereco(rua, cidade, numero, CEP);
		this.nome = nome;
		this.email = email;
	}

	public Pessoa(String CPF, String nome, Long telefone,String email, int diaNasc, int mesNasc, Integer anoNasc,
				  String rua, String cidade, int numero, String CEP, Integer complemento)
	{
		this.CPF = CPF;
		this.telefone = telefone;
		this.dataNasc = new Data(diaNasc, mesNasc, anoNasc);
		this.endereco = new Endereco(rua, cidade, numero, CEP, complemento);
		this.nome = nome;
		this.email = email;
	}

	private boolean ValidarCPF(String CPF)
	{
		//atualizar funcao futuramente, dependendo de como o CPF sera recebido
		//talvez retornar string com tipo de invalidez em caso de invalidez (no caso talvez tenha que usar exception)
		if(CPF.length() != 14){
			return false;
		} else return true;
	}

	public boolean AtualizarCPF(String novoCPF)
	{
		//checar se CPF e valido
		if(!ValidarCPF(novoCPF)){
			System.out.println("CPF inválido!");
			return false;
		} else{
			//atualizar CPF
			this.CPF = novoCPF;
			return true;
		}
	}
}
