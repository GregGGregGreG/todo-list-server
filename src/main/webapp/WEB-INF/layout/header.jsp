<%@include file="../layout/taglib.jsp" %>
<header>
    <nav class="navbar navbar-default navbar-static-top"><!-- Static navbar -->
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="header-logo navbar-brand" href="<spring:url value="/" />"><spring:message code="spring.todo.list.title"/></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse"><security:authorize access="! isAuthenticated()">
                <ul class=" navbar-form navbar-right">
                    <a href="<spring:url value="/join" />" class="btn btn-success"><spring:message code="label.navigation.sign.up.link"/></a>
                    <a href="<spring:url value="/login" />" type="button" class="btn btn-default"><spring:message code="label.navigation.log.in.link"/></a>
                </ul></security:authorize><security:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<spring:url value="/logout"/>"><spring:message code="label.navigation.logout.link"/>
                            <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                        </a>
                    </li>
                </ul></security:authorize>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</header>