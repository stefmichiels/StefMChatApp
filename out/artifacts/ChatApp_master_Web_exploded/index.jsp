<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body>

<main>
	<c:if test="${errors.size()>0 }">
		<div class="danger">
			<ul>
				<c:forEach var="error" items="${errors }">
					<li>${error }</li>
				</c:forEach>
			</ul>
		</div>
	</c:if> <c:choose>
	<c:when test="${user!=null}">
		<p>Welkom ${user.getFirstName()}!</p>
        <p id="userId">${user.getUserId()}</p>

        <form method="post" action="Controller?action=LogOut">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>
		<jsp:include page="chat.jsp"/>
	</c:when>
	<c:otherwise>
		<form method="post" action="Controller?action=LogIn">
			<p>
				<label for="email">Your email </label>
				<input type="text" id="email" name="email" value="stef@ucll.be">
			</p>
			<p>
				<label for="password">Your password</label>
				<input type="password" id="password" name="password" value="t">
			</p>
			<p>
				<input type="submit" id="loginbutton" value="Log in">
			</p>
		</form>
		<br>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script>
            $(document).ready(function(){
                $('#show').click(function() {
                    $('.menu').toggle("slide");
                });
            });
		</script>

		<div id="show">Klik hier om je te registreren</div>

		<div class="menu" style="display: none;">
			<form  method="post" action="Controller?action=AddUser">
			<p>
				<label for="newEmail">Email</label>
				<input type="text" id="newEmail" name="email">
			</p>
			<p>
				<label for="firstName">Voornaam</label>
				<input type="text" name="firstName" id="firstName">
			</p>
			<p>
				<label for="lastName">Achternaam</label>
				<input type="text" name="lastName" id="lastName">
			</p>

			<p>
				<label for="newPassword">Wachtwoord</label>
				<input type="password" name="password" id="newPassword">
			</p>
			<p>
				<label for="passwordRepeat">Herhaal wachtwoord </label>
				<input type="password" id="passwordRepeat" name="passwordAgain">
			</p>

			<p>
				<label for="gender1">Geslacht</label>
				<input type="radio" name="gender" id="gender1" value="female">Man

				<input type="radio" name="gender" id="gender2" value="male">Vrouw
			</p>

			<p>
				<label for="age">Leeftijd</label>
				<select name="age" id="age">
					<c:forEach begin="0" end="100" var="num">
						<option value="${num}">${num}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<input type="submit" value="Profiel aanmaken">
			</p>
		</form>
	</div>
		<jsp:include page="feedbacks.jsp"/>
	</c:otherwise>
</c:choose>

</main>
</body>
<script src="js/js.js"></script>
<script src="js/friendlist.js"></script>
<script src="js/websockets.js"></script>
<script src="js/chatter.js"></script>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>




</html>
</body>
</html>