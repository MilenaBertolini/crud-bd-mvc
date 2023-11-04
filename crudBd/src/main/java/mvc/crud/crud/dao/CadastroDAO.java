package mvc.crud.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mvc.crud.crud.dto.Cadastro;

@Component
public class CadastroDAO {

    @Autowired
    private JdbcTemplate db;

    public long getProximoId(){
        String sql = "select max(id) from Contatos";

        if(db.queryForObject(sql, Long.class) == null){
            sql = "select count(id) from Contatos";
        }
        
        return db.queryForObject(sql, Long.class) + 1;
    }

    public List<Cadastro> getContatos() {
        String sql = "select id,inputNome,inputEmail,inputEndereco,inputTelefone from Contatos";

        return db.query(sql, (res, rowNum) -> {

            return new Cadastro(
                res.getLong("id"),
                res.getString("inputNome"),
                res.getString("inputEmail"),
                res.getString("inputEndereco"),
                res.getString("inputTelefone"));
        });

    }

    public List<Cadastro> getContatos(String nome) {

        String sql = "select id,inputNome,inputEmail,inputEndereco,inputTelefone from Contatos where lower(inputNome) like ?";

        return db.query(sql, 
            new BeanPropertyRowMapper<>(Cadastro.class),
            new Object[] { "%" + nome + "%" });
    }

    public void inserirContato(Cadastro contato){
        String sql = "insert into Contatos(id,inputNome,inputEmail,inputEndereco,inputTelefone) values(?, ?, ?, ?, ?)";
        
        db.update(sql, new Object[]{contato.getId(), contato.getInputNome(), contato.getInputEmail(), contato.getInputEndereco(), contato.getInputTelefone()});

    }

    public void updateContato(Cadastro contato){
        String sql = "update Contatos set inputNome = ?, inputEmail = ?, inputEndereco = ?, inputTelefone = ?  where id = ?";
        
        db.update(sql, new Object[]{contato.getInputNome(), contato.getInputEmail(), contato.getInputEndereco(), contato.getInputTelefone(), contato.getId()});

    }

    public void deleteContatoId(Long id){
        String sql = "delete from Contatos where id = ?";
        
        db.update(sql, new Object[]{id});

    }

    public Cadastro getContatoId(Long id) {
        String sql = "select id,inputNome,inputEmail,inputEndereco,inputTelefone from Contatos where id = ?";

        List<Cadastro> contatos = db.query(sql, 
            // é a mesma coisa que o return do método anterior
            new BeanPropertyRowMapper<>(Cadastro.class),
            new Object[] {id});
        
        if(contatos != null && contatos.size() > 0){
            return contatos.get(0);
        }else{
            return null;
        }
    }
}
