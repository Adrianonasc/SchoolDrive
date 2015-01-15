<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../css.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SchoolDrive</title>
<style type="text/css">
table {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="../topoAdmin.jsp"></jsp:include>

	<!-- Corpo da pagina -->
	<div class="container-fluid">
		<div class="row-fluid">
			<jsp:include page="../MenuLateral.jsp"></jsp:include>
			<div class="col-md-8">

				<!-- mensagens -->
				<jsp:include page="../../mensagens.jsp">
					<jsp:param value="${tipo}" name="tipo" />
					<jsp:param value="${mensagens}" name="mensagens" />
				</jsp:include>

				<div class="row">
					<div class="col-md-5">
						<div class="page-header">
							<h2>Cadastro de Trabalho</h2>
						</div>
					</div>

					<div class="col-md-offset-2 col-md-4">
						<div class="page-header">
							<div class="btn-group btn-group-justified">
								<a class="btn btn-default" href="formPublicarTrabalho">
									<span class="glyphicon glyphicon-plus"> </span> Cadastro	Trabalho</a> 
									<a class="btn btn-default" href="listaTrabalhos"> 
									<span class="glyphicon glyphicon-search"> </span> Lista Trabalho</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-8">
					<table class="table table-bordered table-hover">
						<tr class="active" style="text-align: center;">
							<th>Titulo</th>
							<th>Disciplina</th>
							<th>Prazo Entrega</th>
							<th>Visualizar Trabalho</th>
						</tr>
						<tbody>
							<c:forEach var="trabalho" items="${trabalhos}">
								<tr>
									<td>${trabalho.titulo}</td>
									<td>${trabalho.disciplina.nome}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy"  value="${trabalho.dataLimite}" />	</td>
									<td><a type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal${trabalho.id}"><span
											class="glyphicon glyphicon-file"> </span></a></td>
								</tr>

								<div class="modal fade" id="modal${trabalho.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<h4 class="modal-title">${trabalho.titulo}</h4>
											</div>
											<div class="modal-body">${trabalho.descricao}</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>