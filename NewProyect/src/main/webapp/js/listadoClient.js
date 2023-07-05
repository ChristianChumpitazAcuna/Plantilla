const ACCION_NUEVO = "NUEVO";
const ACCION_EDITAR = "EDITAR";
const ACCION_ELIMINAR = "ELIMINAR";
const ACCION_REACTIVAR = "REACTIVAR";

let arreglo = [];

let btnBuscar = document.getElementById("btnBuscar");
let btnNuevo = document.getElementById("btnNuevo");
let btnProcesar = document.getElementById("btnProcesar");
let btnReactivar = document.getElementById("btnReactivar");

btnBuscar.addEventListener("click", fnBtnBuscar);
btnNuevo.addEventListener("click", fnBtnNuevo);
btnProcesar.addEventListener("click", fnBtnProcesar);
btnReactivar.addEventListener("click", fnBtnBuscarInactivos);

fnBtnBuscar();

function fnEditar(id) {
	document.getElementById("tituloRegistro").innerHTML = ACCION_EDITAR + " REGISTRO";
	document.getElementById("accion").value = ACCION_EDITAR;
	fnCargarForm(id);
}

function fnEliminar(id) {
	document.getElementById("accion").value = ACCION_ELIMINAR;
	fnCargarForm(id);
	fnBtnProcesar();
}

function fnReactivar(id) {
	document.getElementById("accion").value = ACCION_REACTIVAR;
	fnCargarForm(id);
	fnBtnProcesar();
}

function fnBtnBuscar() {
	let name = document.getElementById("name").value;
	let last_name = document.getElementById("last_name").value;
	let document_number = document.getElementById("document_number").value;
	let cellphone = document.getElementById("cellphone").value;

	let url = "ClientBuscar?name=" + name +
		"&last_name=" + last_name +
		"&document_number=" + document_number +
		"&cellphone=" + cellphone;

	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let respuesta = xhttp.responseText;
			arreglo = JSON.parse(respuesta);
			let detalleTabla = "";
			arreglo
				.forEach(function(item) {
					detalleTabla += "<tr>";
					detalleTabla += "<td>" + item.id + "</td>";
					detalleTabla += "<td>" + item.name + "</td>";
					detalleTabla += "<td>" + item.last_name
						+ "</td>";
					detalleTabla += "<td>" + item.document_type
						+ "</td>";
					detalleTabla += "<td>" + item.document_number
						+ "</td>";
					detalleTabla += "<td>" + item.cellphone
						+ "</td>";
					detalleTabla += "<td>" + item.status
						+ "</td>";
					detalleTabla += "<td>";
					detalleTabla += "<button type='button' class='btn btn-success btn-sm' data-bs-toggle='modal' data-bs-target='#ModalForm'><a  class='bi bi-pencil-square' href='javascript: fnEditar("
						+ item.id + ");'></a></button>";

					detalleTabla += "<button type='button' class='btn btn-danger btn-sm'  style= 'margin-top:1.8px;'><a class='bi bi-trash3' href='javascript: fnEliminar("
						+ item.id + ");'></a></button>";

					detalleTabla + "</td>";
					detalleTabla += "</tr>";
				});
			document.getElementById("detalleTabla").innerHTML = detalleTabla;
			document.getElementById("divResultado").style.display = "block";
		}
	};
	xhttp.send();
}

function fnBtnNuevo() {
	document.getElementById("frmId").value = "0";
	document.getElementById("frmName").value = "";
	document.getElementById("frmLast_name").value = "";
	document.getElementById("frmDocument_type").value = "";
	document.getElementById("frmDocument_number").value = "";
	document.getElementById("frmCellphone").value = "";

	document.getElementById("tituloRegistro").innerHTML = ACCION_NUEVO + " REGISTRO";
	document.getElementById("accion").value = ACCION_NUEVO;
}

function fnBtnProcesar() {
	let datos = "accion=" + document.getElementById("accion").value;
	datos += "&id=" + document.getElementById("frmId").value;
	datos += "&name=" + document.getElementById("frmName").value;
	datos += "&last_name=" + document.getElementById("frmLast_name").value;
	datos += "&document_type=" + document.getElementById("frmDocument_type").value;
	datos += "&document_number=" + document.getElementById("frmDocument_number").value;
	datos += "&cellphone=" + document.getElementById("frmCellphone").value;
	datos += "&status=" + document.getElementById("frmStatus").value;

	// Envio con AJAX
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "ClientProcesar", true);
	xhr.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {

			fnBtnBuscar();
			console.log(xhr.responseText);
			alert(xhr.responseText);

		}

	};
	xhr.send(datos);
}

function fnBtnBuscarInactivos() {
	let url = "ClientBuscarInactivos";

	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let respuesta = xhttp.responseText;
			arreglo = JSON.parse(respuesta);
			let detalleTabla = "";
			arreglo
				.forEach(function(item) {
					detalleTabla += "<tr>";
					detalleTabla += "<td>" + item.id + "</td>";
					detalleTabla += "<td>" + item.name + "</td>";
					detalleTabla += "<td>" + item.last_name
						+ "</td>";
					detalleTabla += "<td>" + item.document_type
						+ "</td>";
					detalleTabla += "<td>" + item.document_number
						+ "</td>";
					detalleTabla += "<td>" + item.cellphone
						+ "</td>";
					detalleTabla += "<td>" + item.status
						+ "</td>";
					detalleTabla += "<td>";

					detalleTabla += "<button type='button' class='btn btn-dark btn-sm'  style= 'margin-top:1.8px;'><a class='bi bi-trash3' href='javascript: fnReactivar("
						+ item.id + ");'></a></button>";

					detalleTabla + "</td>";
					detalleTabla += "</tr>";
				});
			document.getElementById("detalleTabla").innerHTML = detalleTabla;
			document.getElementById("divResultado").style.display = "block";
		}
	};
	xhttp.send();
}

function fnCargarForm(id) {
	arreglo.forEach(function(item) {
		if (item.id == id) {
			frmId.value = item.id;
			frmName.value = item.name;
			frmLast_name.value = item.last_name;
			frmDocument_type.value = item.document_type;
			frmDocument_number.value = item.document_number;
			frmCellphone.value = item.cellphone;
			frmStatus.value = item.status;
			return true;
		}
	});
}