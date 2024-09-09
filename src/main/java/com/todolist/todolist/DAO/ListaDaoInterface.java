package com.todolist.todolist.DAO;

import com.todolist.todolist.Modelo.Tarefa;

import java.util.List;

public interface ListaDaoInterface {
    public Tarefa criar(Tarefa p) throws ErroDao;
    public void deletar(int id) throws ErroDao;
    public void deletar(Tarefa p) throws ErroDao;
    public void editar(Tarefa p) throws ErroDao;
    public List<Tarefa> buscar(int id) throws ErroDao;
//    public Tarefa buscar(int id) throws ErroDao;
    public List<Tarefa> buscar(String nome) throws ErroDao;
    public void sair() throws ErroDao;
    public void adicionarTarefaAoUsuario(int usuarioId, int tarefaId) throws ErroDao;

}
