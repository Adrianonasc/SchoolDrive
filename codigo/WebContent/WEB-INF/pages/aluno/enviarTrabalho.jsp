<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../css.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SchoolDrive</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#trabalhoId").hide();
});
</script>
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
						<h3>
							<c:out value="${trabalho.titulo}"></c:out>
						</h3>
					</div>
					<!-- Descrição trabalho -->
					<div class="col-md-6">
						<div class="thumbnail">${trabalho.descricao}</div>
					</div>
					<div class="col-md-4 col-md-offset-2">
						<table class="table table-condensed table-hover table-bordered">
							<caption>
								Status de Trabalho
							</caption>
							<tbody>
								<tr>
									<td>Status</td>
									<c:choose>
										<c:when test="${fn:contains(status,'Aberto')}">
											<td><strong>Aberto</strong></td>
										</c:when>
										<c:when test="${fn:contains(status,'Entregue')}">
											<td class="success"><strong>Entregue</strong></td>
										</c:when>
										<c:otherwise>
											<td class="danger"><strong>Fechado</strong></td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td>Prazo Entrega</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${trabalho.dataLimite}" /></td>
								</tr>
								<tr>
									<td>Data Envio do arquivo</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${entrega.dataEntrega}" />
									</td>
								</tr>
								<tr>
									<td>Nota Trabalho</td>
									<td>${entrega.nota}</td>
								</tr>
								<c:choose>
									<c:when test="${status == true}">
										<tr class="info" align="center">
											<td colspan="2">
												<form method="POST" action="uploadFile" enctype="multipart/form-data">
													Arquivo de upload: 
													<input id="file" type="file" class="file" name="file">
													<br />
													<input type="text" id="trabalhoId" name="Trabalhoid" value="${trabalho.id}">
													<button type="submit" class="btn btn-default">
														<span class="glyphicon glyphicon-cloud-upload"></span> Upload
													</button>
												</form>
										</tr>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>


				<!-- Informaçoes  -->
				<div class="row"></div>
			</div>
		</div>
	</div>
</body>
</html>