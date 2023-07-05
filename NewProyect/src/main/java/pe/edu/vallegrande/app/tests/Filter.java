package pe.edu.vallegrande.app.tests;

import java.util.List;

import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.CrudClientService;

public class Filter {
	public static void main(String[] args) {
		try {
			Client bean = new Client();
			bean.setName("");
			bean.setLast_name("");
			bean.setDocument_number("1");
			bean.setCellphone("");

			CrudClientService clientService = new CrudClientService();
			List<Client> lista = clientService.get(bean);
			for (Client client : lista) {
				System.out.println(client);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
