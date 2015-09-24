<%@ include file="../layout/taglib.jsp" %>
<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/><!DOCTYPE html>
<html>
<head>
    <security:csrfMetaTags/>
    <c:set var="titleKey">
        <tiles:insertAttribute name="title" ignore="true"/>
    </c:set>
    <title><spring:message code="${titleKey}"/></title><c:forEach var="css" items="${stylesheets}">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>"></c:forEach>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon">
</head>
<body><tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="templateItem" ignore="true"/>
<c:forEach var="script" items="${javascripts}">
    <script src="<c:url value="${script}"/>"></script></c:forEach>
</body>
</html>