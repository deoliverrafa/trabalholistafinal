package com.todolist.todolist.DAO;

import com.todolist.todolist.Modelo.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ListaDaoClasse implements ListaDaoInterface {

    private Connection con;

    public ListaDaoClasse() throws ErroDao {
        con = FabricaConexao.pegaConexao();
    }

    @Override
    public Tarefa criar(Tarefa t) throws ErroDao {
        try {
            PreparedStatement stm = con.prepareStatement(
                    "insert into tarefa (titulo, descricao) values (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stm.setString(1, t.getTitulo());
            stm.setString(2, t.getDescricao());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                t.setId(rs.getInt(1));
            }
            stm.close();
            return t;
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void deletar(int id) throws ErroDao {
        try {
            PreparedStatement stm = con.prepareStatement("delete from tarefa where id=?");
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void deletar(Tarefa p) throws ErroDao {
    }

    @Override
    public void editar(Tarefa p) throws ErroDao {
    }

    @Override
    public List<Tarefa> buscar(int usuarioId) throws ErroDao {
        List<Tarefa> tarefas = new LinkedList<>();
        String sql = "SELECT t.* FROM tarefa t JOIN usuario_tarefa ut ON t.id = ut.tarefa_id WHERE ut.usuario_id = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, usuarioId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id"));
                t.setTitulo(rs.getString("titulo"));
                t.setDescricao(rs.getString("descricao"));
                t.setConcluido(rs.getBoolean("concluido"));
                tarefas.add(t);
            }
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return tarefas;
    }

//    @Override
//    public Tarefa buscar(int id) throws ErroDao {
//        return null;
//    }

    @Override
    public List<Tarefa> buscar(String nome) throws ErroDao {
        return List.of();
    }

    @Override
    public void sair() throws ErroDao {
        try {
            con.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void adicionarTarefaAoUsuario(int usuarioId, int tarefaId) throws ErroDao {
        String sql = "INSERT INTO usuario_tarefa (usuario_id, tarefa_id) VALUES (?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, usuarioId);
            stm.setInt(2, tarefaId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
}
