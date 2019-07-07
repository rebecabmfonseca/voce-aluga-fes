package test;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import application.model.Aluguel;


public class aluguelTest {

	@Test
	void testCadastrarAluguel(){
		Aluguel al1 = new Aluguel("", 22,04,2019,20,04,2019,"ABC123", "123333331", "123");
		try{
			Aluguel.saveAluguel(al1);
		}catch(SQLException e){
			fail("Problema no aluguel");
			e.printStackTrace();
		}
	}

}
