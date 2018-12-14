<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
	<head>
		<title>Main Menu</title>
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
			<img class="img-fluid" src="images/stripe.png">
			<div class="container">
				<div class="row">
				    <div class="col-lg-4">
				        <c:url var="logoutCommand" value="">
                              <c:param name="command" value="logout"/>
                        </c:url>
                    	<a href="${logoutCommand}">Log out</a>
                    </div>
					<div class="col-lg-4 text-center">
						<h1>MAIN MENU<h1>
					</div>
					<div class="col-lg-4 text-right">
						<div class="dropdown">
							<button class="btn btn-outline-secondary dropdown-toggle"
							 type="button" id="dropdownMenuButton"
							 data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
			<div class="col-lg-6 text-center">
			<div class="btn-group-vertical">
				<div class="btn-group dropright">
					<button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Turns menu
					</button>
					<div class="dropdown-menu">
						<a href="#" class="dropdown-item">Open turn</a>
						<a href="#" class="dropdown-item">Close turn</a>
					</div>
				</div>
				<div class="btn-group dropright">
					<button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Invoice menu
					</button>
					<div class="dropdown-menu">
						<a href="#" class="dropdown-item">New invoice</a>
						<a href="#" class="dropdown-item">Refund invoice</a>
					</div>
				</div>
				<div class="btn-group dropright">
					<button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Reports menu
					</button>
					<div class="dropdown-menu">
						<a href="#" class="dropdown-item">X-report</a>
						<a href="#" class="dropdown-item">Z-report</a>
						<a href="#" class="dropdown-item">Turns summary</a>
					</div>
				</div>
				<div class="btn-group dropright">
					<button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Supplies menu
					</button>
					<div class="dropdown-menu">
						<a href="#" class="dropdown-item">Show supplies</a>
						<a href="#" class="dropdown-item">Update supplies</a>
					</div>
				</div>
			</div>
			</div>
		</article>
		<footer class="fixed-bottom">
			<img class="img-fluid" src="images/lesser_stripe.png">
		</footer>
	</body>
</html>