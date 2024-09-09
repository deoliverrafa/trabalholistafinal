package com.todolist.todolist.Controle;

import com.todolist.todolist.DAO.ErroDao;
import com.todolist.todolist.DAO.ListaDaoClasse;
import com.todolist.todolist.DAO.ListaDaoInterface;
import com.todolist.todolist.Modelo.Tarefa;
import com.todolist.todolist.Modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class CriarTarefa  extends HttpServlet {

    @Override
    @GetMapping("cadastrartarefa")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String titulo=request.getParameter("titulo");
        String descricao=request.getParameter("descricao");
        if(titulo!=null && descricao!=null && !titulo.isBlank() && !descricao.isBlank())
        {
            Tarefa t=new Tarefa(titulo,descricao);
            try {
                ListaDaoInterface dao =new ListaDaoClasse();
                dao.criar(t);

                HttpSession session = request.getSession();
                Usuario u = (Usuario) session.getAttribute("usuario");

                if (u == null) {
                    response.sendRedirect("login.jsp");
                }
                dao.adicionarTarefaAoUsuario(u.getId(),t.getId());

                dao.sair();
                response.sendRedirect("index.jsp?mensagem=Cadastrado com Sucesso.");
            } catch (ErroDao e) {
                response.sendRedirect("index.jsp?mensagem=Erro ao tentar Cadastrar. "+e);
            }

        }else {
            response.sendRedirect("index.jsp?mensagem=Erro ao tentar Cadastrar.Informe todos os Dados.");
        }


    }
}
