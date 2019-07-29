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
<%@ include file="header.jsp" %>
<body>
<div class="container-login100" style="background-image: url('../../../La Casa/img/hero.jpg');">
    <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
        <form class="login100-form validate-form" action="<c:url value="/user/login/"/>" method="post">
				<span class="login100-form-title p-b-37">
					Sign In
				</span>

            <div class="wrap-input100 validate-input m-b-20" data-validate="Enter username or email">
                <input class="input100" type="text" name="username" placeholder="username">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-25" data-validate="Enter password">
                <input class="input100" type="password" name="password" placeholder="password">
                <span class="focus-input100"></span>
            </div>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn">
                    Sign In
                </button>
            </div>

            <div class="text-center p-t-57 p-b-20">
					<span class="txt1">
						Or login with
					</span>
            </div>

            <div class="flex-c p-b-112">
                <a href="#" class="login100-social-item">
                    <i class="fa fa-facebook-f"></i>
                </a>

                <a href="#" class="login100-social-item">
                    <img src="../../../Login_v9/images/icons/icon-google.png" alt="GOOGLE">
                </a>
            </div>

            <div class="text-center">
                <a href="<c:url value="/user/register"/>" class="txt2 hov1">
                    Sign Up
                </a>
            </div>
        </form>


    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
