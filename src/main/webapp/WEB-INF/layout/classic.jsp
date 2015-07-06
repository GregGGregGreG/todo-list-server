<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<!DOCTYPE html>
<head>
    <%--<script type="text/javascript"--%>
    <%--src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
    <%--<script type="text/javascript"--%>
    <%--src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>--%>
    <%--<link rel="stylesheet"--%>
    <%--href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet"--%>
    <%--href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">--%>
    <%--<script type="text/javascript"--%>
    <%--src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>--%>
    <title><tiles:getAsString name="title"/></title>

    <link href="../../static/css/app.css" rel="stylesheet">
    <link href="../../static/css/them.css" rel="stylesheet">
    <script src="../../static/node_modules/jquery/dist/jquery.js"></script>
    <link href="../../static/node_modules/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet">
    <script src="../../static/node_modules/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
    <script src="../../static/node_modules/autosize/dest/autosize.min.js"></script>
    <script src="../../static/node_modules/jquery.tmpl.js/jquery.tmpl.js"></script>
    <script src="../../static/js/jquery.todo-list.js"></script>
    <%--<script src="/static/js/main.js"></script>--%>
</head>
<body>
<tilesx:useAttribute name="current"/>
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
                <a class="navbar-brand" href="<spring:url value="/" />">Todo-List</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="${current == 'index'?'active':''}">
                        <a href="<spring:url value="/" />">Home</a>
                    </li>
                    <li class="${current == 'account'?'active':''}">
                        <a href="<spring:url value="/account.html" />">Account</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="${current == 'registration'?'active':''}">
                        <a href="<spring:url value="/registration.html" />">Registration</a>
                    </li>
                    <li class="${current == 'login'?'active':''}">
                        <a href="<spring:url value="/login.html" />">Login</a>
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
