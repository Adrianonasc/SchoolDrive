<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../css.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"	src="<c:url value="/resources/js/jquery.maskedinput.js"/>"></script>
<title>SchoolDrive</title>
<c:if test="${foraPrazo}">
<script type="text/javascript">
$(document).ready(function() {
	$('#notas *').find(':input').prop('disabled', true);
});
</script>
</c:if>
<script type="text/javascript">
jQuery(function($) {
	$("#nota").mask("99.9");
});
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
					<div class="page-header">
						<h2>${trabalho.titulo}<small></small>
						</h2>
					</div>
				</div>

				<div class="row">
					<div id="notas" class="col-md-7">
						<form action="notas" method="get">
						<c:if test="${isEntrega}">
							<table class="table table-hover table-bordered" style="vertical-align: middle; text-align: center;">
								<caption>
									<h4>Entregas</h4>
								</caption>
								<thead>
									<tr class="success">
										<th style="text-align: center;">Nome Aluno</th>
										<th style="text-align: center;">Data Entrega</th>
										<th style="text-align: center;">Nota</th>
										<th style="text-align: center;">Anexo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="entrega" items="${entregas}">
										<tr>
											<td>${entrega.aluno.nome}</td>
											<td><fmt:formatDate pattern="dd/MM/yyyy" value="${entrega.dataEntrega}" /></td>
											<td style="width: 15%"><input type="text" id="nota" name="${entrega.aluno.id}" value="${entrega.nota}"
												class="form-control"></td>
											<td><a href="downloadEntrega?entregaId=${entrega.id}" class="btn btn-primary"><span
													class="glyphicon glyphicon-cloud-download"> </span> </a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
								<input style="display: none;" type="text" name="trabalhoId" value="${trabalho.id}">
								<input type="submit" class="btn btn-primary" value="Enviar notas">
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>