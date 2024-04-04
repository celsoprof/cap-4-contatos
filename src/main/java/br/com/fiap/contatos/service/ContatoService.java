package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public ContatoExibicaoDto gravar(ContatoCadastroDto contatoCadastroDto){
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastroDto, contato);
        return new ContatoExibicaoDto(repository.save(contato));
    }

    public ContatoExibicaoDto buscarPeloId(Long id){
        Optional<Contato> contatoOptional = repository.findById(id);
        if(contatoOptional.isPresent()){
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não existe!");
        }
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

    public ContatoExibicaoDto buscarContatoPeloNome(String nome){
        Optional<Contato> contatoOptional = repository.buscarContatoPeloNome(nome);
        if(contatoOptional.isPresent()){
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não existe!");
        }
    }

    public List<ContatoExibicaoDto> listarAniversariantesDoPeriodo(LocalDate dataInicial, LocalDate dataFinal){
        return repository
                .listarAniversariantesDoPeriodo(dataInicial, dataFinal)
                .stream()
                .map(ContatoExibicaoDto::new)
                .toList();
    }

}
