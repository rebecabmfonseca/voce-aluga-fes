package application.model;

public class Endereco
{
	String rua;
	String cidade;
	int numero;
	Integer complemento;
	//complemento nao pode ser null se for int basico
	String CEP;

	public Endereco(String rua, String cidade, int numero, String CEP)
	{
		this.rua = rua;
		this.cidade = cidade;
		this.numero = numero;
		this.CEP = CEP;
		this.complemento = null;
	}

	public Endereco(String rua, String cidade, int numero, String CEP, Integer complemento)
	{
		this.rua = rua;
		this.cidade = cidade;
		this.numero = numero;
		this.CEP = CEP;
		this.complemento = complemento;
	}

	public String toString(){
		if(this.complemento == null) return String.format("CEP: %s, %s, rua %s, %d", CEP, cidade, rua, numero);
		else return String.format("CEP: %s, %s, rua %s, %d, complemento %d", CEP, cidade, rua, numero, complemento);
	}
}
