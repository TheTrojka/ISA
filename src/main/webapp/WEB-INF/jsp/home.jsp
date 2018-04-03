<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<title>Spring Boot Example</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
<script src="/js/angular.js"></script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" ng-app="app">
		<h1>AngularJS - Spring JPA - MySQL</h1>
 
		<div class="row">
			<div ng-controller="postController" class="col-md-3">
				<form name="customerForm" ng-submit="submitForm()">
					<label>FirstName</label>
					<input type="text" name="name"	class="form-control" ng-model="name" />
					<label>LastName</label>
					<input type="text" name="address" class="form-control" ng-model="address" />
					
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
				<p>{{postResultMessage}}</p>
			</div>
		</div>
		<div class="row">
			<div ng-controller="getallcustomersController" class="col-md-3">
				<h3>All Customers</h3>
 
				<button ng-click="getAllCustomers()">Get All Customers</button>
 
				<div ng-show="showAllCustomers">
					<ul class="list-group">
						<li ng-repeat="customer in allcustomers"><h4 class="list-group-item">
								<strong>Customer {{$index}}</strong><br />
								Id: {{customer.id}}<br />
								First Name: {{customer.name}}<br />
								Last Name: {{customer.address}}
						</h4></li>
					</ul>
				</div>
				<p>{{getResultMessage}}</p>
			</div>
 
			<div ng-controller="getcustomerController" class="col-md-3">
				<h3>Customer by ID</h3>
 
				<input type="text" class="form-control" style="width: 100px;"
					ng-model="id" /> <br />
				<button ng-click="getCustomer()">Get Customer</button>
 
				<div ng-show="showCustomer">
					Id: {{customer.id}}<br />
					First Name: {{customer.name}}<br />
					Last Name: {{customer.address}}
				</div>
				<p>{{getResultMessage}}</p>
			</div>
		</div>
	</div>
</body>
</html>