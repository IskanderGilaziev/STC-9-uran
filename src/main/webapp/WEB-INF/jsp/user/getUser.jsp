<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>${teacher.name}</h1>
<p><b>День рождения:</b> ${teacher.birthday}</p>
<p>Адрес:</b> ${teacher.email}</p>
<%@ include file="../../../footer.jsp" %>