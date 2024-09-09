package com.todolist.todolist.Controle;

import com.todolist.todolist.Modelo.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@SessionAttributes("usuario")
@Controller
public class MeuControle implements ErrorController {

    @GetMapping("/")
    String home(){
        return "index";
    }

    @GetMapping("/nova-tarefa")
    String novaTarefa(){ return "nova-tarefa";}

    @GetMapping("/login")
    String login(){return "login";}

    @GetMapping("/cadastrar")
    String cadastrar(){return "cadastrar";}



    @GetMapping("/error")
    String pegaErro(HttpServletRequest request){
        Object status=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status!=null){
            int codigo=Integer.parseInt(status.toString());
            if(codigo==404)
                return "error/404";
            if(codigo==500)
                return "error/500";
        }
        return "error/500";
    }

}
