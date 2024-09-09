<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Produtos</title>
</head>
<body>
<div class="mensagem">
  ${param.mensagem}
</div>
<form action="loginusuario" method="post">
  <label>
    Login
    <input type="text" name="login">
  </label>
  <label>
    Senha
    <input type="text" name="senha">
  </label>
  <input type="submit" value="Logar">
</form>
<p>Não possui conta?</p>
<a href="cadastrar">Cadastrar</a>
</body>
</html>