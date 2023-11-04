package mvc.crud.crud.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import mvc.crud.crud.dao.CadastroDAO;
import mvc.crud.crud.dto.Cadastro;


@Controller
public class CadastroResource {
    
    @Autowired
    private CadastroDAO dao;

    @GetMapping("/cadastro")
    public String doGet(){
        return "cadastro";
    }

    @GetMapping("/")
    public String getListagem(Model model){

        model.addAttribute("cadastro", dao.getContatos());
        return "listagem";
    }

    @PostMapping("cadastroPost")
    public String doPost(Cadastro dto, Model model){

        Long id = dao.getProximoId();

        dao.inserirContato(new Cadastro(id, dto.getInputNome(), dto.getInputEmail(), dto.getInputEndereco(), dto.getInputTelefone()));

        model.addAttribute("cadastro", dao.getContatos());

        return "listagem";
    }

    @GetMapping("atualizaCadastro/{id}")
    public String doFind(@PathVariable("id") Long id, Model model){

        Cadastro encontrarCadastro = dao.getContatoId(id);
        model.addAttribute("cadastroAtualizar", encontrarCadastro);

        return "atualizaCadastro";

    }

    @PostMapping("cadastroAtualizar")
    public String doUpdate(Cadastro dto, Model model){

       if(dao.getContatoId(dto.getId()) != null){
            dao.updateContato(dto);
       }
        model.addAttribute("cadastro", dao.getContatos());
    
        return "listagem";

    }

    @PostMapping("deletaCadastro")
    public String doDelete(Long id, Model model){
        dao.deleteContatoId(id);     

        model.addAttribute("cadastro", dao.getContatos());

        return "listagem";

    }

    @RequestMapping("cadastroParametro")
    public String getContatos(@RequestParam(value = "inputNome", required = true) String nome, Model model) {
        model.addAttribute("cadastro",dao.getContatos(nome));
        return "listagem";
    }

}