<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <jsp:include page="./layouts/head.jsp"/>
</head>
<body style="background-color: blue">

<div class="container-fluid vh-100">
  <div class="row">
    <div class="col">
      <div class="card position-absolute top-50 start-50 translate-middle" style="width: 25rem;">
        <br><br>
          <h4 style="color: black" class="text-center"> Inicio de sesión </h4>
        <br><br>
        <div class="card-body text-center">
          <form id="loginForm" action="/api/auth" class="needs-validation"
                novalidate method="post">
            <div class="form-floating mb-3">
              <input type="text" class="form-control" name="username" id="username"
                     placeholder="name@example.com" required>
              <label for="username">Nombre de Usuario</label>
              <div class="invalid-feedback text-start">
                Campo obligatorio
              </div>
            </div>
            <div class="form-floating mb-3">
              <input type="password" name="password" class="form-control" id="password"
                     placeholder="Password" required>
              <label for="password">Contraseña</label>
              <div class="invalid-feedback text-start">
                Campo obligatorio
              </div>
            </div>
            <div class="form-group mb-3">
              <button id="login" class="btn btn-outline-primary w-100" type="submit">
                <i data-feather="log-in"></i> Iniciar de Sesión
              </button>
              <p></p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="./layouts/footer.jsp"/>
<!-- -->
<script>
  window.addEventListener("DOMContentLoaded", () => {
    feather.replace();
    const form = document.getElementById("loginForm");
    const btn = document.getElementById("login");
    form.addEventListener('submit', event => {
      btn.innerHTML = `<div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
                                    <span class="visually-hidden">Loading...</span>
                                </div>
                            </div>`;
      btn.classList.add("disabled");
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
        btn.classList.remove("disabled");
        btn.innerHTML = `<i data-feather="log-in"></i> INICIAR SESIÓN`;
      }
      form.classList.add('was-validated');
    }, false);
    if (!${param['result']}) {
      Swal.fire({
        title: 'Acceso denegado',
        text: '${param['message']}',
        icon: 'error',
        confirmButtonText: 'Aceptar'
      });
    }
  }, false);
</script>
</body>
</html>