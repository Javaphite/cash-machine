<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
	<head>
		<title>Login</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style><c:import url="/styles/login.css"/></style>
        <style><c:import url="/styles/lang/languages.min.css"/></style>
        <style><c:import url="/styles/bootstrap/bootstrap.min.css"/></style>
        <script language="javascript" type="text/javascript">
            <c:import url="/styles/bootstrap/jquery-3.2.1.slim.min.js"/>
        </script>
        <script language="javascript" type="text/javascript">
            <c:import url="/styles/bootstrap/popper.min.js"/>
        </script>
        <script language="javascript" type="text/javascript">
            <c:import url="/styles/bootstrap/bootstrap.min.js"/>
        </script>
    </head>

	<body>
		<header>
			<img class="img-fluid" src="./images/stripe.png">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 text-center">
						<h1>WELCOME!<h1>
					</div>
					<div class="col-lg-4 text-right">
						<div class="dropdown">
							<button class="btn btn-outline-secondary dropdown-toggle" type="button"
							 id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<span class="lang-sm lang-lbl" lang="en"/>
							</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="#"><span class="lang-sm lang-lbl" lang="en"/></a>
								<a class="dropdown-item" href="#"><span class="lang-sm lang-lbl" lang="uk"/></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>
		<article>
			<div class="container">
			    <c:choose>
			        <c:when test="${empty alert}">
			            <c:set var="alertDisplayMod" value="none"/>
			        </c:when>
			        <c:otherwise>
                    	<c:set var="alertDisplayMod" value="block"/>
                    </c:otherwise>
			    </c:choose>
				<div class="alert alert-${alert.type}" style="display: ${alertDisplayMod}">
					<strong>${alert.attentionText}</strong> ${alert.message}
				</div>
			</div>
			<div class="container">
			<div class="container login-container text-center">
				<div class="col-lg-8 login-form-1">
						<h3>Please, complete authorization!</h3>
						<form method="POST">
							<div class="form-group">
								<input name="login" type="text" class="form-control" placeholder="Login..." value=""/>
							</div>
							<div class="form-group">
								<input name="password" type="password" class="form-control" placeholder="Password..." value="" />
							</div>
							<div class="form-group">
							    <input name="command" type="hidden" value="login"/>
								<input type="submit" class="btnSubmit" value="Login" />
							</div>
						</form>
					</div>
				</div>
		</article>
		<footer class="fixed-bottom">
			<img class="img-fluid" src="./images/lesser_stripe.png">
		</footer>
	</body>
</html>