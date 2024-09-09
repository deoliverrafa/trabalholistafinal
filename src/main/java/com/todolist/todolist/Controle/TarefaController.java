package com.todolist.todolist.Controle;

import com.todolist.todolist.DAO.ErroDao;
import com.todolist.todolist.DAO.ListaDaoClasse;
import com.todolist.todolist.Modelo.Tarefa;
import com.todolist.todolist.Modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping("/nova")
    public String formNovaTarefa() {
        return "nova-tarefa";
    }

    @PostMapping("/criar")
    public ModelAndView criarTarefa(@RequestParam("titulo") String titulo,
                                    @RequestParam("descricao") String descricao,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return new ModelAndView("redirect:/login");
        }

        Tarefa tarefa = new Tarefa(titulo, descricao);
        try {
            ListaDaoClasse dao = new ListaDaoClasse();
            tarefa = dao.criar(tarefa);
            dao.adicionarTarefaAoUsuario(usuario.getId(), tarefa.getId());
            dao.sair();
            redirectAttributes.addFlashAttribute("mensagem", "Tarefa criada com sucesso.");
        } catch (ErroDao e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao criar tarefa: " + e.getMessage());
        }

        return new ModelAndView("redirect:/tarefas/listar");
    }

    @GetMapping("/listar")
    public ModelAndView listarTarefas(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ModelAndView mv = new ModelAndView("listar-tarefas");

        if (usuario == null) {
            mv.setViewName("redirect:/login");
            return mv;
        }

        try {
            ListaDaoClasse dao = new ListaDaoClasse();
            mv.addObject("tarefas", dao.buscar(usuario.getId())); // Passa o ID do usuário para buscar as tarefas
            dao.sair();
        } catch (ErroDao e) {
            mv.addObject("mensagem", "Erro ao listar tarefas: " + e.getMessage());
        }

        return mv;
    }

    @PostMapping("/deletar/{id}")
    public ModelAndView deletarTarefa(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            ListaDaoClasse dao = new ListaDaoClasse();
            dao.deletar(id);
            dao.sair();
            redirectAttributes.addFlashAttribute("mensagem", "Tarefa deletada com sucesso.");
        } catch (ErroDao e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao deletar tarefa: " + e.getMessage());
        }

        return new ModelAndView("redirect:/tarefas/listar");
    }

    @PostMapping("/concluir/{id}")
    public ModelAndView marcarComoConcluida(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            ListaDaoClasse dao = new ListaDaoClasse();
            Tarefa tarefa = (Tarefa) dao.buscar(id);
            tarefa.setConcluido(!tarefa.isConcluido());
            dao.editar(tarefa);
            dao.sair();
            redirectAttributes.addFlashAttribute("mensagem", "Tarefa atualizada com sucesso.");
        } catch (ErroDao e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar tarefa: " + e.getMessage());
        }

        return new ModelAndView("redirect:/tarefas/listar");
    }
}
