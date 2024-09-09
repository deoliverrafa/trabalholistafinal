package com.todolist.todolist.DAO;

import com.todolist.todolist.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoClasse implements UsuarioDaoInterface {

    private Connection con;
    public UsuarioDaoClasse() throws ErroDao{
        con=FabricaConexao.pegaConexao();
    }


    public void criar(Usuario user) throws ErroDao{
        try {
            PreparedStatement stm=con.prepareStatement
                    ("insert into usuario (login,nome,senha) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

            stm.setString(1,user.getNome());
            stm.setString(2,user.getLogin());
            stm.setString(3,user.getSenha());
            stm.executeUpdate();

            ResultSet rs=stm.getGeneratedKeys();

            if(rs.next())
            {
                user.setId(rs.getInt(1));
            }
            stm.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sair() throws ErroDao {

    }

    @Override
    public Usuario logar(Usuario u) throws ErroDao {

        try{
            PreparedStatement stm=con.prepareStatement("select * from usuario where login=? and senha=?", PreparedStatement.RETURN_GENERATED_KEYS);

            stm.setString(1,u.getLogin());
            stm.setString(2,u.getSenha());

            ResultSet rs=stm.executeQuery();

            if (rs.next()) {
                u.setId(rs.getInt(1));
            }

            stm.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return u;
    }


}
