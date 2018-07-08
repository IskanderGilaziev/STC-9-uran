<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
        <h1>Редактирование</h1>
<form action="/teacher/update" method="post">
    <label>Имя: </label><input type="text" value="${teacher.name}" name="name"><BR>
    <label>День рождения: </label><input type="text" value="${teacher.birthday}" name="birthday"><BR>
    <label>Адрес: </label><input type="text" value="${teacher.address}" name="address"><BR> <label>Специальность:
    (выпадающий список)</label><input type="text" value="" name="speciality"><BR>
            <input type="submit" value="OK">
        </form>
<%@ include file="../../../footer.jsp" %>