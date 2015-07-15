<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring4-4.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<p th:if="${url}">
    <b>Page:</b> <span >${url}</span>
</p>

<p th:if="${timestamp}" id='created'>
    <b>Occurred:</b> <span>${timestamp}</span>
</p>

<p th:if="${status}">
    <b>Response Status:</b> <span >${status}</span> <span
        th:if="${error}" >${error}</span>
</p>