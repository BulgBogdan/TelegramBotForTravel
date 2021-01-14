<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
            background: url("<c:url value="/resources/image/travel_picture.jpg" />") no-repeat center center fixed;
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
                    <c:url value="/edit/${city.id}" var="cityEdit"/>
                    <c:url value="/delete/${city.id}" var="cityDelete"/>
                    <td><h5>${city.cityName}</h5></td>
                    <td class="text-center"><h5>${city.cityInfo}</h5></td>
                    <td class="text-right">
                        <a class='btn btn-info btn-xs' href="${cityEdit}">Изменить</a>
                        <a href="${cityDelete}" class="btn btn-danger btn-xs">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/create" class="btn btn-primary btn-xs pull-right"><b>+</b> Добавить город</a>
        <span class="text-info text-right"
              style="margin-left: 70px; font-size: 120%">Количество городов: ${countCities}</span>
        <div class="container text-center">
            <c:if test="${pagesCount > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>
                <c:url value="/" var="url">
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
                    <c:url value="/" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <c:set value="current-page" var="current"/>
                    <c:set value="" var="perspective"/>
                    <a class="${page == i.index ? current : perspective}"
                       href="${url}"> ${i.index} </a>
                </c:forEach>

                <c:url value="/" var="url">
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