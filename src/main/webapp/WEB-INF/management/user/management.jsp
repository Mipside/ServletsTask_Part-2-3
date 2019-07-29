<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 30.05.2019
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../../user/login/header.jsp" %>
<title>Management Page</title>
<body>
<div class="container-login100" style="background-image: url('../../../La Casa/img/hero.jpg');">
    <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
        <form class="login100-form validate-form">
				<span class="login100-form-title p-b-37">
				    Hello ${uname} (Admin)
				</span>
            <ul>
                <li>first name: ${firstname}</li>
                <li>last name: ${lastname}</li>
                <li>status: ${status}</li>
            </ul>
        </form>
        <br>
        <div class="container-login100-form-btn">
            <button class="login100-form-btn">
                <a href="<c:url value="/management/user/create"/>" class="txt2 hov1">
                    CREATE USER
                </a>
            </button>
        </div>
        <br>
        <div class="container-login100-form-btn">
            <button class="login100-form-btn">
                <a href="<c:url value="/management/usersred"/>" class="txt2 hov1">
                    CRUD users </a>

            </button>
        </div>

    </div>
</div>
<%@ include file="../../user/login/footer.jsp" %>
</body>
</html>
