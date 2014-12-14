<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<h2>Disciplina Cadastradas</h2>
						</div>
					</div>
					<div class="col-md-offset-2 col-md-4">
						<div class="page-header">
							<div class="btn-group btn-group-justified">
								<a class="btn btn-default" href="formDisciplina"> <span
									class="glyphicon glyphicon-plus"></span> Inserir
								</a> <a class="btn btn-default" href="listaDisciplina"> <span
									class="glyphicon glyphicon-search"></span> Pesquisar
								</a>
							</div>
						</div>

					</div>
				</div>
				<br>

				<div class="row">
					<div class="col-md-6">
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead align="center">
									<tr>
										<th>Nome Disciplina</th>
										<th>Curso</th>
										<th>Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="disciplina" items="${disciplinas}">
										<tr>
											<td align="center">${disciplina.nome}</td>
											<td align="center"><c:if
													test="${! empty disciplina.curso.nome }">${disciplina.curso.nome}</c:if>
											</td>
											<td align="center"><a class="btn btn-danger"
												href="excluirDisciplina?id=${disciplina.id}"> <span
													class="glyphicon glyphicon-remove-sign"></span>Excluir
											</a></td>
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