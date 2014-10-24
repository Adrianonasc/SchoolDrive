<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../css.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SchoolDrive</title>
</head>
<body>

	<jsp:include page="../topoAdmin.jsp"></jsp:include>

	<!-- Corpo da pagina -->
	<div class="container">
		<div class="row">
			<div class="col-lg-7  col-lg-offset-1">

				<!-- mensagens -->
				<jsp:include page="../../mensagens.jsp">
					<jsp:param value="${tipo}" name="tipo" />
					<jsp:param value="${mensagens}" name="mensagens" />
				</jsp:include>


				<div class="col-lg-offset-8">
					<div class="page-header">
					<div class="btn-group btn-group-justified">
						<a type="button" class="btn btn-default" href="formUsuario">
							<span class="glyphicon glyphicon-plus"></span> Inserir
						</a>
						<a type="submit" class="btn btn-default" href="listaUsuario"> 
							<span class="glyphicon glyphicon-search"></span> Pesquisar
						</a>
					</div>
					</div>
				</div>

				<div class="page-header">
					<h2>Lista Usuario</h2>
				</div>
				<form class="form-inline" role="form">
					<div class="form-group">
						<label class="sr-only" for="email">Email</label> <input
							type="email" class="form-control" id="email" placeholder="Email">
					</div>
					<div class="form-group">
						<input class="form-control" type="text" placeholder="Nome">
					</div>
					<button type="submit" class="btn btn-default">Pesquisar</button>
				</form>
				<br> <br>


				<div class="table-responsive">
					<table class="table">
						<tr>
							<th>Nome</th>
							<th>Email</th>
							<th>Perfil</th>
							<th>Excluir</th>
						</tr>
						<tbody>

							<c:forEach var="usuario" items="${usuarios}">
								<tr>
									<td>${usuario.nome}</td>
									<td>${usuario.email}<br></td>
									<td>${usuario.role}</td>
									<td align="center"><a type="button" href="excluirUsuario?id=${usuario.id}&role=${usuario.role}"
											class="btn btn-danger">
											<span class="glyphicon glyphicon-remove-sign"></span> Excluir
										</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>