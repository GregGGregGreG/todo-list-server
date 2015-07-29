<%@ include file="../layout/taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<tilesx:useAttribute name="current"/>
<!DOCTYPE html>
<html>
<head>
    <c:set var="titleKey">
        <tiles:insertAttribute name="title" ignore="true"/>
    </c:set>
    <title><spring:message code="${titleKey}"/></title>
    <link href="../../static/css/app.css" rel="stylesheet">
    <link href="../../static/css/them.css" rel="stylesheet">
    <script src="../../static/node_modules/jquery/dist/jquery.js"></script>
    <link href="../../static/node_modules/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet">
    <script src="../../static/node_modules/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
    <script src="../../static/node_modules/autosize/dest/autosize.min.js"></script>
    <script src="../../static/node_modules/jquery.tmpl.js/jquery.tmpl.js"></script>
    <script src="../../static/js/jquery.todo-list.js"></script>
    <script src="../../static/node_modules/bootstrap.message/bootstrap.message.js"></script>
    <%--<script src="../../static/node_modules/jquery-validation-1.14.0/additional-methods.js"></script>--%>
    <script src="../../static/node_modules/jquery-validation-1.14.0/jquery.validate.js"></script>
  </head>
<body>
<header>
    <div class="container">
        <!-- Static navbar -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<spring:url value="/" />"><spring:message
                            code="spring.todo.list.title"/></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="${current == 'index'?'active':''}">
                            <a href="<spring:url value="/" />"><spring:message code="label.navigation.home.link"/></a>
                        </li>
                        <li class="${current == 'account'?'active':''}">
                            <a href="<spring:url value="/account" />"><spring:message
                                    code="label.navigation.account.link"/></a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="${current == 'join'?'active':''}">
                            <a href="<spring:url value="/join" />"><spring:message
                                    code="label.navigation.sign.up.link"/></a>
                        </li>
                        <li class="${current == 'login'?'active':''}">
                            <a href="<spring:url value="/login" />"><spring:message
                                    code="label.navigation.sign.in.link"/></a>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
            <!--/.container-fluid -->
        </nav>
    </div>
</header>
<main class="container">
    <tiles:insertAttribute name="body"/>
</main>
<footer class="container">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>
