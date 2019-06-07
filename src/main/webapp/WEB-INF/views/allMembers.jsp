<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All members</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/common.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/common.css"/>">
</head>
<body>

	<div class="container">
		<div class="page-header ">
			<h2>List of all member</h2>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<a href="${pageContext.request.contextPath}/registration"
					class="btn btn-info center-block" role="button">Register new member !</a>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 form-com-block table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Mobile</th>
							<th>DOB</th>
							<th>Gender</th>
							<th>Email</th>
							<!-- <th>Edit</th>
							<th>Delete</th> -->
						</tr>
					</thead>

					<tbody>
						<c:forEach var="member" items="${memberList}">
							<tr>
								<td>${member.firstName}</td>
								<td>${member.lastName}</td>
								<td>${member.mobileNumber}</td>
								<td>${member.dateOfBirthStr}</td>
								<td>${member.genderStr}</td>
								<td>${member.email}</td>
								<%-- <td><a
								href="${pageContext.request.contextPath}/profile/${member.memberId}">Edit</a></td>
								<td><a
								href="${pageContext.request.contextPath}/deleteMember/${member.memberId}">Delete</a></td>
								--%>
							</tr>
						</c:forEach>
					</tbody>


				</table>
			</div>
		</div>
	</div>


</body>
</html>