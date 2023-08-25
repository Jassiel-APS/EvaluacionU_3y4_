<%--
  Created by IntelliJ IDEA.
  User: DESKTOP
  Date: 8/23/2023
  Time: 12:55 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student</title>
    <jsp:include page="/layouts/head.jsp"/>
</head>
<body>
<jsp:include page="/layouts/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col text-center mt-5 align-items-center">
            <br><br>
            <h2>Lista para aceptar incidentes</h2>
            <br><br>
            <div class="card align-items-center">
                <div class="card-body w-75">
                    <table class="table">
                        <thead>
                        <th>#</th>
                        <th>Nombre</th>
                        <th>Tipo</th>
                        <th>Descripcion</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                        </thead>
                        <tbody>
                        <s:forEach var="incident" varStatus="s" items="${incidents}">
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
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            <form action="/charge/active" method="post">
                                                <button type="submit" class="btn btn-outline-success btn-sm">
                                                    <input hidden name="id" value="${incident.id}">
                                                    <i data-feather="edit"></i> ACTIVAR
                                                </button>
                                            </form>
                                            <form method="post" action="/charge/inactiva">
                                                <button type="submit" class="btn btn-outline-danger btn-sm">
                                                    <input hidden name="id" value="${incident.id}">
                                                    <i data-feather="edit"></i> RECHAZAR
                                                </button>
                                            </form>
                                        </div>
                                    </div>
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
    <script>
        window.addEventListener("DOMContentLoaded", () => {
            if (!${param['result']==false?param['result']:true}) {
                Swal.fire({
                    title: 'Información...',
                    text: '${param['message']}',
                    icon: '${param['result']==false ? 'success':'error'}',
                    confirmButtonText: 'Aceptar'
                });
            }
            if (!${param['result']==false?param['result']:true}) {
                Swal.fire({
                    title: 'Información...',
                    text: '${param['message']}',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            }
            if (${param['result']==true?param['result']:false}) {
                Swal.fire({
                    title: 'Información...',
                    text: '${param['message']}',
                    icon: 'succes',
                    confirmButtonText: 'Aceptar'
                });
            }
        }, false);
    </script>
</body>
</html>
