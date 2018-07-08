<jsp:useBean id="speciality" scope="request" type="ru.innopolis.stc9.pojo.hibernate.entities.Speciality"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>${speciality.name}</h1>
<p><b>Название специальности:</b> ${speciality.name}</p>
<p><b>Срок обучения, г.:</b>${speciality.yTotal}</p>
<%--<p><b>Можно ли обучить новую группу</b>${speciality.isActive}</p>--%>
<%@ include file="../../../footer.jsp" %>