import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import application.model.Cliente;

class clienteTest {

	@Test
	void testGetAll() {
		List<Cliente> users = Cliente.getAll();
//		System.out.println(users);
		if(users.isEmpty())
			fail("getall");
	}

	@Test
	void testUpdateClient() {
		Random rand = new Random();
		Cliente c = new Cliente("12345678900", "REBECA FONSECA", "1234567890", "21212121211", "Teste@teste.com", rand.nextInt(31) + 1, rand.nextInt(12) + 1, rand.nextInt(2000) + 1,
								"RUA TESTE", "CIDADE MEU DEUS", 30, "20702890");
		try {
			Cliente.updateClient(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(c);
		if(c.equals(Cliente.getCliente(c.getCPF())))
			fail("update");
	}

	@Test
	void testRemoveCliente() {
		try {
			Cliente.removeCliente("12345678900");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("remove");

		}
	}
	
	@Test
	void testSaveClient() {
		Cliente c = new Cliente("12345678900", "REBECA FONSECA", "1234567890", "21212121211", "Teste@teste.com", 2, 5, 2019,
				"RUA TESTE", "CIDADE SUPER TESTE", 330, "20702890");
		try {
			Cliente.saveClient(c);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("save");
		}
	}

}
