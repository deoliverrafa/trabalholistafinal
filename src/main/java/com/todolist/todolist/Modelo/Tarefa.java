package com.todolist.todolist.Modelo;

import java.util.Objects;

public class Tarefa {
    private int id;
    private String titulo,descricao;
    private boolean concluido;

    public Tarefa(){}
    public Tarefa(String nome, String descricao) {
        this.titulo = nome;
        this.descricao = descricao;
    }
    public Tarefa(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", concluido=" + concluido +
                '}';
    }
    public boolean isConcluido() {
        return concluido;
    }
}
