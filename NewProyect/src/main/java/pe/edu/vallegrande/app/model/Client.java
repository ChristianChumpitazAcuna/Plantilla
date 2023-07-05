package pe.edu.vallegrande.app.model;

public class Client {
	private Integer id;
	private String name;
	private String last_name;
	private String document_type;
	private String document_number;
	private String cellphone;
	private String status;

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(Integer id, String name, String last_name, String document_type, String document_number,
			String cellphone, String status) {
		super();
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.cellphone = cellphone;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		String data = "[id: " + this.id;
		data += " NOMBRE: " + this.name;
		data += " APELLIDO: " + this.last_name;
		data += " TIPO DOC: " + this.document_type;
		data += " # DOC: " + this.document_number;
		data += " CELULAR: " + this.cellphone;
		data += " ESTADO: " + this.status + "]";
		return data;
	}
}
