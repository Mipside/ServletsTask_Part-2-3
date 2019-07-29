<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 18.05.2019
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="../login/header.jsp" %>
<body>
<div class="container-login100" style="background-image: url('../../../La Casa/img/hero.jpg');">
    <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
        <form class="login100-form validate-form" action="<c:url value="/user/register"/>" method="post">
				<span class="login100-form-title p-b-37">
					Sign Up
				</span>

            <div class="wrap-input100 validate-input m-b-20" data-validate="Enter username or email">
                <input class="input100" type="text" name="username" placeholder="username">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-25" data-validate="Enter password">
                <input class="input100" type="password" name="password" placeholder="password">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-25" data-validate="Enter first name">
                <input class="input100" type="text" name="firstName" placeholder="first name">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-25" data-validate="Enter last name">
                <input class="input100" type="text" name="lastName" placeholder="last name">
                <span class="focus-input100"></span>
            </div>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn">
                    Sign Up
                </button>
            </div>
            <br>

            <div class="text-center">
                <a href="<c:url value="/user/login/"/>" class="txt2 hov1">
                    Sign In
                </a>
            </div>
        </form>


    </div>
</div>

<%@ include file="../login/footer.jsp" %>
</body>
</html>
