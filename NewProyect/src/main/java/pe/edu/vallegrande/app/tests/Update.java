package pe.edu.vallegrande.app.tests;

import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.CrudClientService;

public class Update {
	public static void main(String[] args) {
		try {
			Client bean = new Client(5, "Mario", "Avila Aburto", "DNI", "15361382", "990145329", "A");
			CrudClientService clientService = new CrudClientService();
			clientService.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
