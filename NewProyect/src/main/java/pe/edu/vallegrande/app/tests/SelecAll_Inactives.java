package pe.edu.vallegrande.app.tests;

import java.util.List;

import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.CrudClientService;

public class SelecAll_Inactives {
	public static void main(String[] args) {
		try {
			CrudClientService clientService = new CrudClientService();
			List<Client> lista = clientService.getAllInactive();
			for (Client client : lista) {
				System.out.println(client);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
