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
<title>Home</title>
<body>
<div class="container-login100" style="background-image: url('../../../La Casa/img/hero.jpg');">
    <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
        <form class="login100-form validate-form" action="<c:url value="/user/logout"/>" method="post">
				<span class="login100-form-title p-b-37">
				    Hello ${uname}
				</span>
            <ul>
                <li>first name: ${firstname}</li>
                <li>last name: ${lastname}</li>
                <li>status: ${status}</li>

            </ul>
            <div class="container-login100-form-btn">
                <button class="login100-form-btn">
                    Logout
                </button>
            </div>
        </form>

    </div>
</div>

<%@ include file="../login/footer.jsp" %>
</body>
</html>
