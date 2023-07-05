package pe.edu.vallegrande.app.tests;

import pe.edu.vallegrande.app.service.CrudClientService;

public class Delete {
	public static void main(String[] args) {
		try {
			String id = "4";

			CrudClientService clientService = new CrudClientService();
			clientService.delete(id);
			System.out.println("Eliminacion correcta");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
