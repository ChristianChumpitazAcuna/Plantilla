package pe.edu.vallegrande.app.tests;

import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.CrudClientService;

public class Insert {
	public static void main(String[] args) {
		try {
			Client bean = new Client(0, "Mario", "Avila Aburto", "DNI", "15361382", "990145329", "I");
			CrudClientService clientService = new CrudClientService();
			clientService.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
