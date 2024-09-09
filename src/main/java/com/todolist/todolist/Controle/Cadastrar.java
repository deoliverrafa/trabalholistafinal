package com.todolist.todolist.Controle;

import com.todolist.todolist.DAO.ErroDao;
import com.todolist.todolist.DAO.UsuarioDaoClasse;
import com.todolist.todolist.Modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class Cadastrar {

    @PostMapping("cadastrarusuario")
    protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        request.setCharacterEncoding("utf-8");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (nome == null || login == null || senha == null || nome.isBlank() || login.isBlank() || senha.isBlank()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao tentar Cadastrar. Informe todos os Dados.");
            return new ModelAndView("redirect:index");
        }

        Usuario u = new Usuario(nome, login, senha);
        try {
            UsuarioDaoClasse dao = new UsuarioDaoClasse();
            dao.criar(u);
            System.out.println(u);
            dao.sair();

            redirectAttributes.addFlashAttribute("mensagem", "Cadastrado com Sucesso. Faça login com o novo usuário e senha.");
            return new ModelAndView("redirect:login");
        } catch (ErroDao e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao cadastrar usuário. " + e.getMessage());
            return new ModelAndView("redirect:login");
        }
    }
}
