<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
.recuo {
	padding-right: 32px;
}
</style>
<sec:authorize access="isAuthenticated()">

	<div class="container">

		<nav class="navbar navbar-default " role="navigation">
			<div class="container">

				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Navegação</span> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Pagina Principal</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="formUsuario">Usuario</a></li>
						<li><a href="formSemestre">Semestre</a></li>
						<li><a href="formCurso">Curso</a></li>
						<li><a href="formDisciplina">Disciplina</a></li>
						<li><a href="formAlocarProfessor">Alocar Professor</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						
						<li class="recuo"><p class="navbar-text">
								<a href="<c:url value="/j_spring_security_logout"/>"
									class="navbar-link"><span
									class="glyphicon glyphicon-log-out"></span> Sair</a>
							</p></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>


</sec:authorize>