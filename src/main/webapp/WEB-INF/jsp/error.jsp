<%@include file="../layout/taglib.jsp" %>
<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring4-4.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<div class="alert alert-danger">
    <p>
        <c:out value="${errorMessage}" />
    </p>
    <p><spring:message code="label.default.error.page.text"/></p>
</div>