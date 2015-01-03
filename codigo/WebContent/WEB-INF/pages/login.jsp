<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="css.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/login.css" />">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2 align="center">
				<a href="">Envio de Trabalhos</a>
			</h2>
			<div class="wrapper">
				<form class="form-signin" method="POST"
					action="<c:url value='/ShoolDrive/j_spring_security_check'/>"
					name="formLogin">
					<h2 class="form-signin-heading" align="center">Acesso</h2>
					<input type="text" class="form-control" name="j_username"	id="j_username" placeholder="Nome do Usuario" 
						required="" autofocus="" /> <input type="password" class="form-control"
						name="j_password" id="j_password" placeholder="Senha" required="required" />
					<input type="checkbox" value="remember-me" id="rememberMe"
						name="rememberMe" class="chk"><label for="rememberMe">Remember me </label> 
						<label><a align="center" href="">Esqueceu o seu	usuário ou senha?</a></label>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Acesso</button>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<p>Essa é a plataforma de envio de trabalhos do Prof. Jarley
					Nóbrega. Os alunos matriculados no semestre deverão se cadastrar
					(apenas uma vez) para acessar a funcionalidade de envio de
					trabalhos.</p>
			</div>
		</div>
	</div>
</body>
</html>