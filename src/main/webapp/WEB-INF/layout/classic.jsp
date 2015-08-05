<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<tilesx:useAttribute name="current"/>
<!DOCTYPE html>
<html>
<head>
    <security:csrfMetaTags/>
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
    <script>
        $(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });
    </script>
    <style>
        @import url(http://fonts.googleapis.com/css?family=Montserrat);

        body {
            background-color: #f5f8f9;
            /*font-family: montserrat, arial, verdana;*/
            /*background: linear-gradient(rgba(196, 102, 0, 0.2), rgba(155, 89, 182, 0.2)),*/
            /*url('http://thecodeplayer.com/uploads/media/gs.png');*/
        }

        .glyphicon.glyphicon-off {
            font-size: 15px;
        }
    </style>
</head>
<body>
<header>
    <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<spring:url value="/" />">
                    <spring:message code="spring.todo.list.title"/>
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <security:authorize access="isAuthenticated()">
                    <ul class="nav navbar-nav">
                        <li class="${current == 'account'?'active':''}">
                            <a href="<spring:url value="/account" />">
                                <spring:message code="label.navigation.account.link"/>
                            </a>
                        </li>
                    </ul>
                </security:authorize>
                <security:authorize access="! isAuthenticated()">
                    <ul class=" navbar-form navbar-right">
                        <a href="<spring:url value="/join" />" class="btn btn-success">
                            <spring:message code="label.navigation.sign.up.link"/>
                        </a>
                        <a href="<spring:url value="/login" />" type="button" class="btn btn-default">
                            <spring:message code="label.navigation.log.in.link"/>
                        </a>
                    </ul>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="<spring:url value="/logout"/>">
                                <spring:message code="label.navigation.logout.link"/>
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                            </a>
                        </li>
                    </ul>
                </security:authorize>
            </div>
            <!--/.nav-collapse -->
        </div>
        <!--/.container-fluid -->
    </nav>
</header>
<main class="container">
    <tiles:insertAttribute name="body"/>
</main>
<footer class="footer">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>
