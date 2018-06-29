<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
        <h1>${person.name}</h1>
        <p><b>День рождения:</b> ${person.birthday}</p>
        <p>Адрес:</b> ${person.email}</p>
<p>Права:</b> ${status.name}</p>
<%@ include file="../../../footer.jsp" %>