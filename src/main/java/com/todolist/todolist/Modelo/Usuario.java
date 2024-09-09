package com.todolist.todolist.Modelo;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;
    Tarefa tarefa;

    public Usuario(){}

    public Usuario(String login, String senha) {
        this.login  = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha, String nome) {
        this.login  = login;
        this.senha = senha;
        this.nome = nome;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public String getNome() {
        return this.nome;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha=" + senha +
                '}';
    }
}
