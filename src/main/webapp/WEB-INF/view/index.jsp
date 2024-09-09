<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:if test="${empty usuario}">
    <c:redirect url="/login"/>
</c:if>

<div class="mensagem">
    ${mensagem}
</div>

<form action="/tarefas/criar" method="post">
    <label>
        Título
        <input type="text" name="titulo">
    </label>
    <label>
        Descrição
        <input type="text" name="descricao">
    </label>
    <input type="submit" value="Criar Tarefa">
</form>
</body>
</html>
