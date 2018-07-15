<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../header.jsp" %>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">URAN-team presentation</a>
        </div>

    </div>
</nav>
<div class="main">
    <div class="content">
        <div id="navbar" class="navbar-collapse collapse">
            <form action="/j_spring_security_check" method="post">
                <%--<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">--%>
                <h2>Вход в систему</h2>

                <div class="form-group">
                    <input type="text" name="j_username" placeholder="login" class="form-control"><br>
                </div>

                <div class="form-group">
                    <input type="password" name="j_password" placeholder="password" class="form-control"><br>
                </div>

                <input type="submit" value="Войти">

            </form>
        </div>
        <br><br>
        <a href="/registration">Регистрация</a>

    </div>
</div>

<%@ include file="../../footer.jsp" %>