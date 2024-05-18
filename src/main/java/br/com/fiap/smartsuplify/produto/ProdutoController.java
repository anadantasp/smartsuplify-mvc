package br.com.fiap.smartsuplify.produto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model){
        model.addAttribute("produtos", repository.findAll());
        return "produto/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        var produto = repository.findById(id);

        if(produto.isEmpty()){
            redirect.addFlashAttribute("message", "Erro ao apagar. Produto não encontrado");
            return "redirect:/produtos";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Produto apagado com sucesso");
        return "redirect:/produtos";
    }

    @GetMapping("/new")
    public String form(Produto produto){
        return "produto/form";
    }

    @PostMapping
    public String create(@Valid Produto produto,BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "produto/form";
        }
        
        repository.save(produto);
        redirect.addFlashAttribute("message", "Produto cadastrado com sucesso");

        return "redirect:/produtos";
    }
    
}


