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
<script type="text/javascript" src="<c:url value="/resources/js/nicEdit.js" />">
	
</script>
<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		new nicEditor({
			fullPanel : true,
			iconsPath : '/ShoolDrive/resources/js/nicEditorIcons.gif'
		}).panelInstance("areaEditor");
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
					<div class="col-md-5">
						<div class="page-header">
							<h2>Cadastro de Trabalho</h2>
						</div>
					</div>

					<div class="col-md-offset-2 col-md-4">
						<div class="page-header">
							<div class="btn-group btn-group-justified">
								<a class="btn btn-default" href="formPublicarTrabalho"> <span
									class="glyphicon glyphicon-plus"> </span> Cadastro Trabalho
								</a> <a class="btn btn-default" href="listaTrabalhos"> <span
									class="glyphicon glyphicon-search"></span> Lista Trabalho
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-offset-1 col-md-5">
					<form:form action="incluirTrabalho" method="post" role="form"
						modelAttribute="trabalho">
						<div class="form-group">
							<label>Titulo</label>
							<form:input path="titulo" cssClass="form-control" type="text" />
						</div>

						<div class="form-group">
							<label>Prazo Entrega</label>
							<form:input path="dataLimite"  id="dataEntrega" cssClass="form-control" type="date" />
						</div>

						<label>Disciplina</label>
						<div class="form-group form-inline">
							<select name="disciplina" class="form-control" required='required'
								style="width: 60%">
								<c:forEach var="disciplina" items="${disciplinas}">
									<option class="form-control" value="${disciplina.id}">
										<c:out value="${disciplina.nome}" />
									</option>
								</c:forEach>
							</select>

							<button class="btn btn-success" id="btnSalvar" type="submit">
								<span class="glyphicon glyphicon-saved"> </span> Salvar e Publicar
							</button>
						</div>

						<div class="form-group">
							<form:textarea path="descricao" id="areaEditor"
								cssStyle="height: 400px; width: 500px;" />
						</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>