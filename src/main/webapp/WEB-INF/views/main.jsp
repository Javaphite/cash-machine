<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Main Menu</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="styles/lang/languages.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</head>

	<body>
		<header>
			<img class="img-fluid" src="images/stripe.png">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 text-center">
						<h1>MAIN MENU<h1>
					</div>
					<div class="col-lg-4 text-right">
						<div class="dropdown">
							<button class="btn btn-outline-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
				<div class="alert alert-info" hidden>
					<strong>Info!</strong> alert-success, alert-info, alert-warning, alert-danger.
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