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
			<div class="col-lg-7 col-lg-offset-1">

				<!-- mensagens -->
				<jsp:include page="../../mensagens.jsp">
					<jsp:param value="${tipo}" name="tipo" />
					<jsp:param value="${mensagens}" name="mensagens" />
				</jsp:include>


				<div class="page-header">
					<h3>Alocar Professor</h3>
				</div>
				<form action="alocarProfessor" method="post" role="form">
					<div class="form-group">
						<label for="nome">Nome Professor</label>
						<form:input type="text" class="form-control" id="nome" path="nome" />
					</div>
					<div class="checkbox">
						<c:forEach var="disciplina" items="disciplinas">
							<label> 
							<input type="checkbox" value="${disciplina.id}">${disciplina.nome}
							</label>
						</c:forEach>
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-saved"></span>Cadastrar Curso
					</button>

				</form>
			</div>
		</div>


	</div>
</body>
</html>