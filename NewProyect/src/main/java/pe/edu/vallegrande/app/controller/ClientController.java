package pe.edu.vallegrande.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.CrudClientService;

@WebServlet({ "/ClientBuscar", "/ClientBuscarInactivos", "/ClientProcesar" })
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CrudClientService service = new CrudClientService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		switch (path) {
		case "/ClientBuscar":
			buscar(request, response);
			break;

		case "/ClientBuscarInactivos":
			buscarInactivos(request, response);
			break;

		case "/ClientProcesar":
			procesar(request, response);
			break;

		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		String accion = request.getParameter("accion");
		Client bean = new Client();
		bean.setId(Integer.parseInt(request.getParameter("id")));
		bean.setName(request.getParameter("name"));
		bean.setLast_name(request.getParameter("last_name"));
		bean.setDocument_type(request.getParameter("document_type"));
		bean.setDocument_number(request.getParameter("document_number"));
		bean.setCellphone(request.getParameter("cellphone"));
		bean.setStatus(request.getParameter("status"));

		try {
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO:
				service.insert(bean);
				break;

			case ControllerUtil.CRUD_EDITAR:
				service.update(bean);
				break;

			case ControllerUtil.CRUD_ELIMINAR:
				service.delete(bean.getId().toString());
				break;

			case ControllerUtil.CRUD_REACTIVAR:
				service.reactive(bean.getId().toString());
				;
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);

			}
			ControllerUtil.responseJson(response, "Proceso Exitoso");

		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscarInactivos(HttpServletRequest request, HttpServletResponse response) {
		List<Client> lista = service.getAllInactive();

		Gson gson = new Gson();
		String data = gson.toJson(lista);

		ControllerUtil.responseJson(response, data);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String last_name = request.getParameter("last_name");
		String document_number = request.getParameter("document_number");
		String cellphone = request.getParameter("cellphone");

		Client bean = new Client();
		bean.setName(name);
		bean.setLast_name(last_name);
		bean.setDocument_number(document_number);
		bean.setDocument_type(cellphone);

		List<Client> lista = service.getAll();

		Gson gson = new Gson();
		String data = gson.toJson(lista);

		ControllerUtil.responseJson(response, data);
	}

}
