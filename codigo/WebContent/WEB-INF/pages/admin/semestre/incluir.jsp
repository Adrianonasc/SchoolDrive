<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../css.jsp"></jsp:include>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.maskedinput.js"/>"></script>
<script type="text/javascript">
	jQuery(function($) {
		$("#anoSemestre").mask("9999.9");
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SchoolDrive</title>
</head>
<body>

	<jsp:include page="../topoAdmin.jsp"></jsp:include>

	<!-- Corpo da pagina -->
	<div class="container-fluid">
		<div class="row-fluid">
			<jsp:include page="../MenuLateral.jsp"></jsp:include>

			<div class="col-md-7">

				<!-- mensagens -->
				<jsp:include page="../../mensagens.jsp">
					<jsp:param value="${tipo}" name="tipo" />
					<jsp:param value="${mensagens}" name="mensagens" />
				</jsp:include>

				<div class="row">
					<div class="col-md-5">
						<div class="page-header">
							<h3>Abrir Semestre</h3>
						</div>
					</div>
				</div>

				<form:form action="incluirSemestre" method="post" role="form"
					cssClass="form-inline" modelAttribute="semestre">
					<div class="form-group">
						<label for="nome">Ano e Semestre</label>
						<form:input type="text" class="form-control" id="anoSemestre"
							path="anoSemestre" required="required" />
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-saved"></span>Abrir Novo Semestre
					</button>
				</form:form>

				<br> <br> <br>
				
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading"><strong>Ultimos Semestres</strong></div>
						<div class="panel-body">
							<table class="table">
								<thead>
									<tr>
										<th>Ano. Semestre</th>
										<th>Fechar Semestre</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="semestre" items="${semestres}">
										<tr>
											<td>${semestre.anoSemestre}</td>
											<td><c:choose>
													<c:when test="${! semestre.status}">
														<a disabled class="btn btn-default"> <span
															class="glyphicon glyphicon-remove-sign"></span> Fechado
														</a>
													</c:when>
													<c:when test="${semestre.status}">
														<a class="btn btn-danger" href="fecharSemestre?id=${semestre.id}">
															<span class="glyphicon glyphicon-remove-sign"></span> Fechar
														</a>
													</c:when>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>