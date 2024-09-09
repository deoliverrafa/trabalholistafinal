package com.todolist.todolist.Controle;

import com.todolist.todolist.DAO.ErroDao;
import com.todolist.todolist.DAO.ListaDaoClasse;
import com.todolist.todolist.DAO.ListaDaoInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletar")
public class Deletar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String tid=request.getParameter("id");
        if(tid!=null && !tid.isBlank())
        {
            try {
                ListaDaoInterface dao=new ListaDaoClasse();
                dao.deletar(Integer.parseInt(tid));
                dao.sair();
                response.sendRedirect("relatorio?mensagem=Deletado com Sucesso.");
            } catch (ErroDao e) {
                response.sendRedirect("relatorio?mensagem=Erro ao tentar Deletar. "+e);
            }

        }else {
            response.sendRedirect("relatorio?mensagem=Informe o Id.");
        }


    }
}
