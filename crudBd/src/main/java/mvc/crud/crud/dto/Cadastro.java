package mvc.crud.crud.dto;

import lombok.Data;

@Data
public class Cadastro {

    public Cadastro(Long id, String inputNome, String inputEmail, String inputEndereco, String inputTelefone){
        this.id = id;
        this.inputNome = inputNome;
        this.inputEmail = inputEmail;
        this.inputEndereco = inputEndereco;
        this.inputTelefone = inputTelefone;

    }

    public Cadastro(){

    }

    private long id;
    private String inputNome;
    private String inputEmail;
    private String inputEndereco;
    private String inputTelefone;


    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setInputNome(String inputNome) {
        this.inputNome = inputNome;
    }
    public String getInputNome() {
        return inputNome;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }
    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEndereco(String inputEndereco) {
        this.inputEndereco = inputEndereco;
    }
    public String getInputEndereco() {
        return inputEndereco;
    }

    public void setInputTelefone(String inputTelefone) {
        this.inputTelefone = inputTelefone;
    }
    public String getInputTelefone() {
        return inputTelefone;
    }
}