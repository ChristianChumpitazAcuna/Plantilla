<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


<title>Clientes</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>

	<div class="card-deck" style="margin: 60px 30px 0px 30px;">

		<div class="card text-center">
			<div class="card-body">Listado de Clientes</div>
		</div>

		<div class="card">
			<div class="card-body">

				<form action="#" method="post">
					<div class="row text-center">

						<div class="col">
							<input type="text" class="form-control text-center" id="name"
								placeholder="Nombre">
						</div>

						<div class="col">
							<input type="text" class="form-control text-center"
								id="last_name" placeholder="Apellido">
						</div>

						<div class="col">
							<input type="text" class="form-control text-center"
								id="document_number" placeholder="# Documento">
						</div>

						<div class="col">
							<input type="text" class="form-control text-center"
								id="cellphone" placeholder="Celular">
						</div>

						<div class="col">
							<button type="button" class="btn btn-primary" id="btnBuscar">Buscar</button>
						</div>

						<div class="col">
							<button type="button" class="btn btn-primary" id="btnNuevo"
								data-bs-toggle="modal" data-bs-target="#ModalForm">Nuevo</button>

							<button type="button" class="btn btn-primary" id="btnReactivar">Reactivar</button>
						</div>

					</div>
				</form>

			</div>
		</div>

		<div class="card" id="divResultado">
			<div class="card-body">

				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>NOMBRE</th>
							<th>APELLIDO</th>
							<th>TIPO DOC.</th>
							<th># DOC</th>
							<th>CELULAR</th>
							<th>ESTADO</th>
							<th>ACCIONES</th>
						</tr>
					</thead>
					<tbody id="detalleTabla">

					</tbody>
				</table>

			</div>
		</div>

		<!-- Modal Form -->
		<div class="modal fade" id="ModalForm" tabindex="-1"
			aria-labelledby="miModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-scrollable ">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="tituloRegistro">{accion}</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Cerrar"></button>
					</div>
					<div class="modal-body">
						<form class="row gy-2 gx-3 align-items-center needs-validation"
							novalidate>
							<input type="hidden" id="accion" name="accion">

							<div class="col-md-6">
								<label for="frmId" class="form-label">ID</label> <input
									type="text" class="form-control" id="frmId" disabled="disabled"
									value="0">
							</div>

							<div class="col-md-6">
								<label for="frmName" class="form-label">Nombre</label> <input
									type="text" class="form-control" id="frmName">
							</div>

							<div class="col-md-6">
								<label for="frmLast_name" class="form-label">Apellido</label> <input
									type="text" class="form-control" id="frmLast_name">
							</div>

							<div class="col-md-6">
								<label for="frmDocument_type" class="form-label">Tipo de
									documento</label> <input type="text" class="form-control"
									id="frmDocument_type">
							</div>

							<div class="col-md-6">
								<label for="frmDocument_number" class="form-label">Numero
									de documento</label> <input type="text" class="form-control"
									id="frmDocument_number">
							</div>

							<div class="col-md-6">
								<label for="frmCellphone" class="form-label">Celular</label> <input
									type="text" class="form-control" id="frmCellphone">
							</div>

							<div class="col-md-6">
								<label for="frmStatus" class="form-label">Estado</label> <input
									type="text" class="form-control" id="frmStatus"
									disabled="disabled" value="A">
							</div>

						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="btnProcesar">Procesar</button>
					</div>

				</div>
			</div>
		</div>

	</div>

</body>

<script src="js/listadoClient.js"></script>
</html>