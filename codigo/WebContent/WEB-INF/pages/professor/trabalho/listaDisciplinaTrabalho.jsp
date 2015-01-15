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
					<div class="page-header">
						<h2>Lista de Entregas</h2>
					</div>
				</div>

				<div class="row">
					<div class="col-md-7 col-md-offset-2">
						<div role="tabpanel">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<c:forEach items="${disciplinas}" var="disciplina">
									<li role="presentation"><a href="#<c:out value="${disciplina.nome}"></c:out>"
										aria-controls="${disciplina.nome}" role="tab" data-toggle="tab">${fn:toUpperCase(disciplina.nome)}</a></li>
								</c:forEach>
							</ul>


							<!-- Tab panes -->
							<div class="tab-content">
								<br />
								<c:forEach items="${disciplinas}" var="disciplina">
									<div role="tabpanel" class="tab-pane" id="${disciplina.nome}">
										<table class="table table-hover">
											<thead>
												<tr>
													<th>Titulo Trabalho</th>
													<th>Data Limite</th>
													<th>Visualizar Entregas</th>
												</tr>
											</thead>
											<c:forEach var="trabalho" items="${disciplina.trabalhos}">
												<tbody>
													<tr>
														<td>${trabalho.titulo}</td>
														<td><fmt:formatDate pattern="dd/MM/yyyy" value="${trabalho.dataLimite}" /></td>
														<td><a href="listaEntregasTrabalho?trabalhoId=${trabalho.id}" class="btn btn-primary"> Visualizar</a></td>
													</tr>
												</tbody>
											</c:forEach>
										</table>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>