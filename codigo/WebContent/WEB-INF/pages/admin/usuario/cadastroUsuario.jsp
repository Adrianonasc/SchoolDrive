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
							<a  class="btn btn-default" href="formUsuario">
								<span class="glyphicon glyphicon-plus"></span> Inserir
							</a> <a  class="btn btn-default" href="listaUsuario">
								<span class="glyphicon glyphicon-search"></span> Pesquisar
							</a>
						</div>
					</div>
				</div>

				<div class="page-header">
					<h2>Cadastro de Usuario</h2>
				</div>
				
				<form:form role="form" method="post" action="addUsuario"
					modelAttribute="usuario">
					<div class="form-group">
						<label for="nome">Nome Completo*</label>
						<form:input type="text" class="form-control" id="nome" path="nome" />
					</div>
					<div class="form-group">
						<label for="email">Email*</label>
						<form:input type="email" class="form-control" id="email"
							path="email" />
					</div>
					<div class="form-group">
						<label for="password">Senha*</label>
						<form:password class="form-control" id="password" placeholder="" required="required"
							path="senha" />
					</div>
					<div class="form-grupo">
						<label>Escola o Perfil so Usuario</label> <select
							class="form-control" name="role">
							<option value="ROLE_ADMIN">Administrador</option>
							<option value="ROLE_ALUNO">Aluno</option>
							<option value="ROLE_PROFESSOR">Professor</option>
						</select>
					</div>
					<br>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-saved"></span> Salvar
					</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>