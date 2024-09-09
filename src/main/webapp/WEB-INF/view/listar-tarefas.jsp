<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Tarefas</title>
</head>
<body>
<div class="mensagem">
    ${mensagem}
</div>

<c:if test="${not empty tarefas}">
    <ul>
        <c:forEach var="tarefa" items="${tarefas}">
            <li>
                    ${tarefa.titulo} - ${tarefa.descricao}
                <form action="/tarefas/concluir/${tarefa.id}" method="post" style="display:inline;">
                    <button type="submit">
                        <c:choose>
                            <c:when test="${tarefa.concluido}">Marcar como Não Concluída</c:when>
                            <c:otherwise>Marcar como Concluída</c:otherwise>
                        </c:choose>
                    </button>
                </form>
                <form action="/tarefas/deletar/${tarefa.id}" method="post" style="display:inline;">
                    <button type="submit">Deletar</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>

<a href="/tarefas/nova">Nova Tarefa</a>
</body>
</html>
