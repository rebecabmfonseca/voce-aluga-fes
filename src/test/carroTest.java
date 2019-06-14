package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import application.model.Carro;

class carroTest {
	
	String[] placas = {"PAS 9893", "SEI 9893", "AME 9893", "NNN 9893"};
	
	@Test
	void testRemoveCarro() {
		System.out.println("TESTE REMOVE");
		try {
			for(int i = 0; i < placas.length; i++) {
				Carro.removeCarro(placas[i]);
				System.out.println(Carro.getCarro(placas[i]));
				if(!(Carro.getCarro(placas[i]) == null))
					fail("Remove carro error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Remove carro error");
		}
	}
	
	@Test
	void testSaveCarro() {
		Carro c = new Carro(placas[0], 3000, "CARRO", "CHEV", "PRATA", 2008, "ECO");
		Carro ca = new Carro(placas[1], 3400, "CARRO NOVO", "NISSAN", "PRETO", 2015, "ECO");
		Carro car = new Carro(placas[2], 6000, "CARRO NEM TAO NOVO", "VW", "VERDE", 2012, "CLEAN");
		Carro carr = new Carro(placas[3], 7000, "CARRO LUXO", "BMW", "BRANCO", 2015, "LUXO");
		try {
			Carro.saveCarro(c);
			Carro.saveCarro(ca);
			Carro.saveCarro(car);
			Carro.saveCarro(carr);
		} catch (SQLException e) {
			fail("Problems creating car");
			e.printStackTrace();
		}
	}

	@Test
	void testGetCarro() {
		Carro ca = new Carro("PAS 9893", 3000, "CARRO", "CHEV", "PRATA", 2008, "ECO");
		Carro carr = Carro.getCarro("PAS 9893");
		
		Carro carro = new Carro(placas[1], 3400, "CARRO NOVO", "NISSAN", "PRETO", 2015, "ECO");
		Carro carros = Carro.getCarro(placas[1]);

		
		Carro car = new Carro("AME 9893", 6000, "CARRO NEM TAO NOVO", "VW", "VERDE", 2012, "CLEAN");
		Carro c = Carro.getCarro("AME 9893");
		
		if( !carro.getModelo().equalsIgnoreCase(carros.getModelo()) || !c.getModelo().equalsIgnoreCase(car.getModelo()) || !carr.getModelo().equalsIgnoreCase(ca.getModelo()))
			fail("Fails in getCarro");
	}

	@Test
	void testGetAll() {
		List<Carro> c = Carro.getAll();
		if(c.isEmpty())
			fail("FAIL CARRO GetAll");
	}

	@Test
	void testUpdateCar() {
		Random rand = new Random();
		Carro c = Carro.getCarro(placas[2]);
		Carro ca = new Carro(placas[2], rand.nextInt(2000), "CARRO NEM TAO NOVO", "VW", "VERDE", 2015, "CLEAN");
		if(c.getQuilometragem() == ca.getQuilometragem())
			fail("UPDATE FAIL");
	}

}
