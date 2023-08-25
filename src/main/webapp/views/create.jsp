<%--
  Created by IntelliJ IDEA.
  User: pccom
  Date: 8/23/2023
  Time: 12:55 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro de falta o retardo</title>
    <jsp:include page="/layouts/head.jsp"/>
</head>
<body>
<div class="container-fluid w-75">
    <div class="row">
        <div class="col">
            <div class="card mt-5"><br><br>
                <h2 class="text-center">Verficacion de Incidencia</h2>
                <br><br>
                <div class="card-body">
                    <form action="/incident/save" id="incident-form" class="needs-validation" novalidate  method="post">

                        <div class="form-group mb-3">

                            <div class="row">
                                <div class="col">
                                    <label for="name" class="fw-bold">Nombre: </label>
                                    <input type="text" name="name" id="name" class="form-control"
                                           required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label for="title" class="fw-bold">Tipo </label>
                                    <input type="text" name="title" id="title" class="form-control"
                                           required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label for="description" class="fw-bold">Descripcion: </label>
                                    <input type="text" name="description" id="description" class="form-control"
                                           required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="form-group mb-3 align-items-center">
                                <div class="row">
                                    <div class="col text-end">
                                        <a href="incident/incidents" class="btn btn-outline-danger btn-sm">
                                            CANCELAR
                                        </a>

                                        <button type="submit" class="btn btn-outline-success btn-sm">
                                            ACEPTAR
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layouts/footer.jsp"/>
<script>
    (function () {
        const form = document.getElementById("incident-form");
        form.addEventListener("submit", function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add("was-validated");
        }, false);
    })();
</script>

</body>
</html>
