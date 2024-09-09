package com.todolist.todolist.DAO;

import com.todolist.todolist.Modelo.Usuario;

public interface UsuarioDaoInterface {
    public void criar(Usuario u) throws ErroDao;
    public void sair() throws ErroDao;
    public Usuario logar(Usuario u) throws ErroDao;
}
