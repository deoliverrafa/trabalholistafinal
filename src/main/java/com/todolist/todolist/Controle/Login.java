package com.todolist.todolist.Controle;

import com.todolist.todolist.DAO.ErroDao;
import com.todolist.todolist.DAO.UsuarioDaoClasse;
import com.todolist.todolist.Modelo.Usuario;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login extends HttpServlet {

    @PostMapping("loginusuario")
    protected ModelAndView doPost(String login, String senha, ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {

        try {
            if (login == null || senha == null || login.isBlank() || senha.isBlank()) {
                mv.setViewName("redirect:login");
                mv.addObject("mensagem", "Erro ao tentar Cadastrar. Informe todos os Dados.");
                return mv;
            }

            Usuario u = new Usuario(login, senha);
            UsuarioDaoClasse dao = new UsuarioDaoClasse();
            Usuario usuarioBanco = dao.logar(u);

            HttpSession session = request.getSession(false);
            if (session == null) {
                session = request.getSession();
                session.setAttribute("usuario", usuarioBanco);
                dao.sair();
                mv.setViewName("redirect:login");
                mv.addObject("mensagem", "Logado com Sucesso.");
            } else {
                Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                if (usuarioLogado != null) {
                    mv.setViewName("redirect:/tarefas/listar");
                    mv.addObject("mensagem", "Usuário já está logado.");
                } else {
                    session.setAttribute("usuario", usuarioBanco);
                    mv.setViewName("redirect:/tarefas/listar");
//                    mv.addObject("mensagem", "Logado com Sucesso.");
                }
            }
        } catch (ErroDao e) {
            mv.setViewName("redirect:login");
            mv.addObject("mensagem", "Erro ao logar usuário: " + e.getMessage());
            System.out.println(e);
        }

        return mv;
    }
}
