package pe.edu.vallegrande.app.tests;

import pe.edu.vallegrande.app.service.CrudClientService;

public class Reactive {
	public static void main(String[] args) {
		try {
			String id = "2";

			CrudClientService clientService = new CrudClientService();
			clientService.reactive(id);
			System.out.println("Reactivacion correcta");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
