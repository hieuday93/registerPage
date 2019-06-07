<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration page</title>
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
		<div class="row">

			<div class="col-sm-4 pos-form well well-lg">

				<form:form method="POST" action="registration"
					modelAttribute="memberModel">
					<div id="frm" class="form-group">
						<fieldset id="panel">
							<h4>Registration</h4>
							<div class="form-com-block">
								<form:input path="firstName" placeholder="First Name"
									cssClass="form-control input-sm" />
								<form:errors path="firstName" cssClass="errorblock"
									element="span"></form:errors>
							</div>
							<div class="form-com-block">
								<form:input path="lastName" placeholder="Last Name"
									cssClass="form-control input-sm" />
								<form:errors path="lastName" cssClass="errorblock"
									element="span"></form:errors>
							</div>
							<div class="form-com-block">
								<form:input path="mobileNumber" placeholder="Mobile Number"
									cssClass="form-control input-sm" />
								<form:errors path="mobileNumber" cssClass="errorblock"
									element="span"></form:errors>
							</div>
							<div class="form-com-block">
								<div>
									<span class="text-muted">Date Of Birth</span>
								</div>
								<form:select path="dobDay" id="dobDay">
									<form:options items="${memberModel.daysOfMonth}" />
								</form:select>
								<form:select path="dobMonth" id="dobMonth">
									<form:options items="${memberModel.monthsOfYear}" />
								</form:select>
								<form:select path="dobYear" id="dobYear">
									<form:options items="${memberModel.years}" />
								</form:select>
							</div>
							<div class="form-com-block">
								<div>
									<label class="radio-inline"><form:radiobutton
											path="gender" value="1" /> Male</label> <label class="radio-inline"><form:radiobutton
											path="gender" value="0" /> Female</label>
								</div>
							</div>
							<div class="form-com-block">
								<form:input path="email" placeholder="Email"
									cssClass="form-control input-sm" />
								<form:errors path="email" cssClass="errorblock" element="span"></form:errors>
							</div>
							<div class="form-com-block">
								<input class="btn-block btn-lg bg-btn"
									id="btn_register" type="submit" value="Register" />
							</div>

						</fieldset>
					</div>
					<c:if test="${success == 1 }">
						<div class="form-com-block">
							<input class="btn-block btn-lg bg-btn" type="button"
								id="btn_login" value="Login" />
						</div>
					</c:if>

				</form:form>
			</div>

			<c:if test="${success == 1 }">
				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-lock"></span> Login
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<form method="POST" action="login" role="form" name="loginFrm">
									<div class="form-group">
										<label for="usrname"><span
											class="glyphicon glyphicon-user"></span> Username</label> 
											<input id="username" name="username" placeholder="Enter your Email" class="form-control" />
									</div>
									<div class="form-group">
										<label for="psw"><span
											class="glyphicon glyphicon-eye-open"></span> Password</label> 
											<input id="password" name="password" type="password" placeholder="Enter your Mobile number"  class="form-control" />
									</div>
									<button type="submit" class="btn btn-success btn-block bg-btn" id="btn_dologin">
										<span class="glyphicon glyphicon-off"></span> Login
									</button>
								</form>
							</div>
							<div class="modal-footer">
								<button type="submit"
									class="btn btn-danger btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>
								<p>
									Not a member? <a href='<c:url value="registration"></c:url>'>Sign Up</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Modal -->
			  <div class="modal fade" id="reg_noti" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Notification</h4>
			        </div>
			        <div class="modal-body">
			          <p>${message }</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
			      
			    </div>
			  </div>
			</c:if>

		</div>
	</div>
	</div>
	</div>
	<script type="text/javascript">
		var daysOption = ${memberModel.daysOfMonthJsStr};
		$(document).ready(function() {

			$("#dobMonth").on("change", function() {
				var day = $("#dobDay").val();
				var month = $("#dobMonth").val();
				var year = $("#dobYear").val();
				changeDateList(day, month, year);
			});

			$("#dobYear").on("change", function() {
				var day = $("#dobDay").val();
				var month = $("#dobMonth").val();
				var year = $("#dobYear").val();
				changeDateList(day, month, year);
			});
			$(".errorblock").tooltip('show');

			<c:if test="${success == 1 }">
				$("#panel").attr("disabled", "disabled");
				$("#btn_register").attr("disabled", "disabled");
				$("#frm").addClass("bg-form-grey-out");
				$("#reg_noti").modal();
				$("#btn_login").click(function() {
					$("#myModal").modal();
				});
				
				$("#btn_dologin").click(function(e){
					e.preventDefault();
					$('input').next().remove();
					
					var frm = {};
					frm['username'] = $("#username").val();
					frm['password'] = $("#password").val();
					
					$.ajax({
						type: 'POST',
						contentType:'application/json',
						url: 'doLogin',
						data: JSON.stringify(frm),
						dataType: 'json',
						success: function(res) {
							if(res.validated) {
								$("#reg_noti .modal-body p").html('Successfull log in');
								$("#reg_noti").modal();
								$("#reg_noti").on("hide.bs.modal", function() {
									$(location).attr("href", "allMembers");
								});
							} else {
								$.each(res.errorMessages,function(key,value){
									if((key == 'memberLoginModel') || (key == null)) {
										$("#reg_noti .modal-body p").html(value);
										$("#reg_noti").modal();
									} else {
										$('input[name=' + key + ']').after('<span class="errorblock">' + value + '</span>');
									}
								});
							}
						}
					});
				});
			</c:if>

		});

		function changeDateList(day, month, year) {
			if (month == 2) {
				if (isLeapYear(year)) {
					changeOptions(daysOption, "dobDay", 29);
					day = (day > 29) ? 0 : day;
					$("#dobDay").val(day);
				} else {
					changeOptions(daysOption, "dobDay", 28);
					day = (day > 28) ? 0 : day;
					$("#dobDay").val(day);
				}
			} else if ((month == 4) || (month == 6) || (month == 9)
					|| (month == 11)) {
				changeOptions(daysOption, "dobDay", 30);
				day = (day > 30) ? 0 : day;
				$("#dobDay").val(day);
			} else {
				changeOptions(daysOption, "dobDay", 31);
				day = (day > 31) ? 0 : day;
				$("#dobDay").val(day);
			}
		}
	</script>

</body>
</html>