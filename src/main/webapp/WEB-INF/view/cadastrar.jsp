<%@ page import="com.todolist.todolist.Modelo.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: aluno
  Date: 20/08/2024
  Time: 08:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="mensagem">
  ${param.mensagem}
</div>
<form action="cadastrarusuario" method="post">
  <label>
    Nome
    <input type="text" name="nome">
  </label>
  <label>
    Login
    <input type="text" name="login">
  </label>
  <label>
    Senha
    <input type="text" name="senha">
  </label>
  <input type="submit" value="Cadastrar">
  <p>Ja possui conta?</p>
  <a href="login">Logar</a>
</form>
</body>
</html>
