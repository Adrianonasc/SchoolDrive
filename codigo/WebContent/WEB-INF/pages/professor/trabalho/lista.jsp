<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../css.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SchoolDrive</title>
<script type="text/javascript" src="<c:url value="/resources/js/nicEdit.js" />">
	
</script>

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
								<a class="btn btn-default" href="formPublicarTrabalho"><span class="glyphicon glyphicon-plus">
								 </span>Cadastro Trabalho</a> 
								 <a class="btn btn-default" href="listaTrabalhos"> <span class="glyphicon glyphicon-search">
								 </span>Lista Trabalho</a>
							</div>
						</div>
					</div>
				</div>


				<div class="col-md-5">
					<table class="table table-bordered table-hover">
						<tr>
							<th>Titulo</th>
							<th>Disciplina</th>
							<th>Prazo Entrega</th>
						</tr>
						<tbody>

							<c:forEach var="trabalho" items="${trabalhos}">
								<tr>
									<td>${trabalho.titulo}</td>
									<td>${trabalho.disciplina.nome}</td>
									<td>${trabalho.dataLimite}</td>
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