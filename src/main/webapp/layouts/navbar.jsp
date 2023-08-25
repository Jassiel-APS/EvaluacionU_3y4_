<%--
  Created by IntelliJ IDEA.
  User: pccom
  Date: 8/23/2023
  Time: 12:45 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar " data-bs-theme="dark">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1" style="color: black">Bienvenido Usuario: "${user.username}"</span>
    <form action="/api/auth/logout">
      <button class="btn btn-outline-primary">Cerrar sesión</button>
    </form>
  </div>
</nav>