<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Cities</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
            integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
            crossorigin="anonymous"></script>
    <style>
        body {
            background: url("<c:url value="/resources/image/picture.jpg" />") no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row col-md-12 col-md-offset-2">
        <table class="table table-striped">
            <c:forEach items="${cityList}" var="city">
                <tr>
                    <c:url value="/jsp/cities/edit/${city.id}" var="cityEdit"/>
                    <c:url value="/jsp/cities/delete/${city.id}" var="cityDelete"/>
                    <td><h5>${city.cityName}</h5></td>
                    <td class="text-center"><h5>${city.cityInfo}</h5></td>
                    <td class="text-right">
                        <a class='btn btn-info btn-xs' href="${cityEdit}">
                            <svg width="1em" height="1em"
                                 viewBox="0 0 16 16"
                                 class="bi bi-pencil-fill"
                                 fill="currentColor"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
                                      d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                            </svg>
                        </a>
                        <a href="${cityDelete}" class="btn btn-danger btn-xs">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash"
                                 fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd"
                                      d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg>
                        </a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/jsp/cities/create" class="btn btn-primary btn-xs pull-right"><b>+</b> Добавить город</a>
        <span class="text-right"
              style="margin-left: 70px; font-size: 120%">Количество городов: ${countCities}</span>
        <div class="container text-center">
            <c:if test="${pagesCount > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>
                <c:url value="/jsp/cities/" var="url">
                    <c:param name="page" value="1"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="text-info">первая</span> &nbsp
                </a>
                <c:if test="${pagesCount <= 5}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${pagesCount}"/>
                </c:if>
                <c:if test="${pagesCount > 5}">
                    <c:choose>
                        <c:when test="${page < 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <c:when test="${page > pagesCount - 2}">
                            <c:set var="begin" value="${pagesCount - 4}"/>
                            <c:set var="end" value="${pagesCount}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${page - 2}"/>
                            <c:set var="end" value="${page + 2}"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                    <c:url value="/jsp/cities/" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <c:set value="current-page" var="current"/>
                    <c:set value="" var="perspective"/>
                    <a class="${page == i.index ? current : perspective}"
                       href="${url}"> ${i.index} </a>
                </c:forEach>

                <c:url value="/jsp/cities/" var="url">
                    <c:param name="page" value="${pagesCount}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="text-info">последняя</span>
                </a>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>