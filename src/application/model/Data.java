package application.model;

public class Data
{
	int dia;
	int mes;
	Integer ano;
	//ano nao pode ser null se for int basico

	public Data(int dia, int mes)
	{
		this.dia = dia;
		this.mes = mes;
		this.ano = null;
	}

	public Data(int dia, int mes, Integer ano)
	{
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public String toString()
	{
		if(this.ano == null) return String.format("%d/%d", dia, mes);
		else return String.format("%d/%d/%d", dia, mes, ano);
	}
}

