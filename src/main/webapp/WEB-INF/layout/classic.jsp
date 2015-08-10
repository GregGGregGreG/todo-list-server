<%@ include file="../layout/taglib.jsp" %>
<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>
<!DOCTYPE html>
<html>
<head>
    <security:csrfMetaTags/>
    <c:set var="titleKey">
        <tiles:insertAttribute name="title" ignore="true"/>
    </c:set>
    <title><spring:message code="${titleKey}"/></title>
    <!-- stylesheets -->
    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
    <!-- end stylesheets -->
</head>
<body>
<!-- header -->
<header>
    <tiles:insertAttribute name="header"/>
</header>
<!-- end header  -->
<!-- content -->
<main class="container">
    <tiles:insertAttribute name="body"/>
</main>
<!-- end content -->
<!-- footer -->
<footer class="footer">
    <tiles:insertAttribute name="footer"/>
</footer>
<!-- end footer -->
<!-- scripts -->
<c:forEach var="script" items="${javascripts}">
    <script src="<c:url value="${script}"/>"></script>
</c:forEach>
<!-- end scripts -->
</body>
</html>
