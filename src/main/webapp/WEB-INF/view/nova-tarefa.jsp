<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Criar Tarefa</title>
</head>
<body>
<div class="mensagem">
  ${mensagem}
</div>
<form action="/tarefas/criar" method="post">
  <label>
    Titulo
    <input type="text" name="titulo">
  </label>
  <label>
    Descricao
    <input type="text" name="descricao">
  </label>
  <input type="submit" value="Criar Tarefa">
</form>
<a href="/tarefas/listar">ver tarefas</a>
</body>
</html>
