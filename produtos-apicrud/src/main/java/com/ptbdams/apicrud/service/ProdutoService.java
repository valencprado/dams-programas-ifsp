package com.ptbdams.apicrud.service;

import com.ptbdams.apicrud.exception.RecursoNaoEncontradoException;
import com.ptbdams.apicrud.model.Produto;
import com.ptbdams.apicrud.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado."));
    }
    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);

    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
    public void atualizar(Long id, Produto produtoNovo) throws Exception{
        Produto produto = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID" + id + "não encontrado"));
        produto.setId(produtoNovo.getId());
        produto.setNome(produtoNovo.getNome());
        produto.setPreco(produtoNovo.getPreco());
        repository.save(produto);
    }
}
