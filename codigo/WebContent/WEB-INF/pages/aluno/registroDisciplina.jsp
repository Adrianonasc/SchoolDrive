<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../css.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SchoolDrive</title>
</head>
<body>
	<jsp:include page="topoAluno.jsp"></jsp:include>

	<!-- Corpo da pagina -->
	<div class="container-fluid">
		<div class="row-fluid">
			<jsp:include page="MenuLateral.jsp"></jsp:include>
			<div class="col-md-8">

				<!-- mensagens -->
				<jsp:include page="../mensagens.jsp">
					<jsp:param value="${tipo}" name="tipo" />
					<jsp:param value="${mensagens}" name="mensagens" />
				</jsp:include>

				<div class="row">
					<div align="center" class="page-header">
						<h2>Disciplinas Disponivel</h2>
					</div>

					<div class="col-md-6 col-md-offset-3">
						<table class="table">
							<thead>
								<tr>
									<th>Descricão</th>
									<th>professor</th>
									<th>Comfirmar Registro</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disciplina" items="${disciplinas}">
									<tr>
										<td>${disciplina.nome}</td>
										<td>${disciplina.professor.nome}</td>
										<td align="center"><a class="btn btn-primary" 
										href="registraDisciplina?disciplinaId=${disciplina.id}"><span
									    class="glyphicon glyphicon-plus"></span> Registra-se</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>