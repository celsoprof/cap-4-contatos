package br.com.fiap.contatos.service;

import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato gravar(Contato contato){
        return repository.save(contato);
    }

    public Contato buscarPeloId(Long id){
        return repository.findById(id).get();
    }

    public List<Contato> listarTodos(){
        return repository.findAll();
    }

    public void excluir(Long id){
        repository.delete(repository.findById(id).get());
    }

    public Contato atualizar(Contato contato){
        return repository.save(contato);
    }

}
