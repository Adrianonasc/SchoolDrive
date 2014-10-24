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
	<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>Nome Disciplina</th>
							<th>Curso</th>
							<th>Excluir</th>
						</tr>

						<tbody>
							<tr>
								<td rowspan="3">Filosofia</td>
								<td>ADM</td>
								<td align="center" rowspan="3"><button type="button"
										class="btn btn-danger">
										<span class="glyphicon glyphicon-remove-sign"></span>Excluir
									</button></td>
							</tr>
							<tr>
								<td>CONT</td>
							</tr>
							<tr>
								<td>COMP</td>
							</tr>

							<tr>
								<td>APOO</td>
								<td>COMP</td>
								<td align="center"><button type="button"
										class="btn btn-danger">
										<span class="glyphicon glyphicon-remove-sign"></span>Excluir
									</button></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
				<ul class="pagination">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</div>
	</div>
</body>
</html>