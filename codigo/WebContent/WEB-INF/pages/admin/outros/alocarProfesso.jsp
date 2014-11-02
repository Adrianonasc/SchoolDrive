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
						<label>Escolha um Professor</label> 
						<select name="professor"	cssClass="form-control" required='required'>
							<c:forEach var="professor" items="${professores}">
								<option value="${professor.id}"><c:out value="${professor.nome}" /></option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Escolha a Disciplina</label> 
						<select name="disciplina"	cssClass="form-control" required='required'>
							<c:forEach var="disciplina" items="${disciplinas}">
								<option value="${disciplina.id}"><c:out value="${disciplina.nome}" /></option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-saved"></span>Alocar Professor
					</button>

				</form>
			</div>
		</div>


	</div>
</body>
</html>