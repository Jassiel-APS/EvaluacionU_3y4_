<%--
  Created by IntelliJ IDEA.
  User: pccom
  Date: 8/23/2023
  Time: 12:51 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <jsp:include page="/layouts/head.jsp"/>
</head>
<body>
<jsp:include page="/layouts/navbar.jsp"/>
<div class="row">
    <div class="col text-center mt-5 align-items-center">
        <h2>Lista de incidentes</h2>
        <br><br>
        <div class="card align-items-center" style="color: white">
            <br>
            <div class="row">
                <div class="col text-end">
                    <a href="/incident/view-create" class="btn btn-outline-success btn-sm">
                        Registrar Incidencia
                    </a>
                </div>
            </div>
            <br>
            <div class="card-body w-75">
                <table class="table">
                    <thead>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Descripcion</th>
                    <th>Estado</th>
                    </thead>
                    <tbody>
                    <s:forEach var="incident" items="${incidents}" varStatus="s">
                        <tr>
                            <td>
                                <s:out value="${s.count}"/>
                            </td>
                            <td>
                                <s:out value="${incident.name}"/>
                            </td>
                            <td>
                                <s:out value="${incident.title}"/>
                            </td>
                            <td>
                                <s:out value="${incident.description}"/>
                            </td>
                            <td>
                                <s:out value="${incident.status}"/>
                            </td>
                        </tr>
                    </s:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/layouts/footer.jsp"/>
</body>
</html>
