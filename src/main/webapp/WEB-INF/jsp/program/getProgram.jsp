<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>${program.specialty.name} - ${program.subject.name}</h1>
        <p><b>Специальность:</b> ${program.specialty.name}</p>
        <p>Семестры:</b> ${program.semester}</p>
<p>Предмет:</b> ${program.subject.name}</p>
        <p>Учебные часы:</b> ${program.hours}</p>
<%@ include file="../../../footer.jsp" %>