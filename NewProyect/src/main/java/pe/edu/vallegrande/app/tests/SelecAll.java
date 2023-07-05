package pe.edu.vallegrande.app.tests;

import java.util.List;

import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.CrudClientService;

public class SelecAll {
	public static void main(String[] args) {
		try {
			CrudClientService clientService = new CrudClientService();
			List<Client> lista = clientService.getAll();
			for (Client client : lista) {
				System.out.println(client);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
