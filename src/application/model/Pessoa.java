package application.model;

public class Pessoa
{
	String CPF;
	String nome;
	String telefone;
	Data dataNasc;
	Endereco endereco;
	String email;
	
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
//	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	public Pessoa(String CPF, String nome, String telefone, String email, int diaNasc, int mesNasc, Integer anoNasc,
				  String rua, String cidade, int numero, String CEP)
	{
		this.CPF = CPF;
		this.telefone = telefone;
		this.dataNasc = new Data(diaNasc, mesNasc, anoNasc);
		this.endereco = new Endereco(rua, cidade, numero, CEP);
		this.nome = nome;
		this.email = email;
	}

	public Pessoa(String CPF, String nome, String telefone,String email, int diaNasc, int mesNasc, Integer anoNasc,
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
		if (CPF.equals("00000000000") ||
	            CPF.equals("11111111111") ||
	            CPF.equals("22222222222") || CPF.equals("33333333333") ||
	            CPF.equals("44444444444") || CPF.equals("55555555555") ||
	            CPF.equals("66666666666") || CPF.equals("77777777777") ||
	            CPF.equals("88888888888") || CPF.equals("99999999999") || CPF.length() != 11){
			
			return false;
		} else {
			Integer digito1 = calcularDigito(CPF.substring(0,9), pesoCPF);
			Integer digito2 = calcularDigito(CPF.substring(0,9) + digito1, pesoCPF);
			return CPF.equals(CPF.substring(0,9) + digito1.toString() + digito2.toString());
		}
	}
	


	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }


	public boolean AtualizarCPF(String novoCPF)
	{
		//checar se CPF e valido
		if(!ValidarCPF(novoCPF)){
			System.out.println("CPF invalido!");
			return false;
		} else{
			//atualizar CPF
			this.CPF = novoCPF;
			return true;
		}
	}
}
