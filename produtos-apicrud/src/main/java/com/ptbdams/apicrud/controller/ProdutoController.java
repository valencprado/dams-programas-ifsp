package com.ptbdams.apicrud.controller;

import com.ptbdams.apicrud.model.Desconto;
import com.ptbdams.apicrud.model.Produto;
import com.ptbdams.apicrud.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name="Produtos", description = "Métodos envolvendo a produtos")

public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @Operation(description = "Cria um produto")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));
    }

    @Operation(description = "Lista todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso")
    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @Operation(description = "Lista produto específico")

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Produto obtido com sucesso")
    public Produto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(description = "Busca produtos que possuem determinado nome")
    @GetMapping("/buscar")
    public List<Produto> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @Operation(description = "Deleta um produto")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Atualiza um produto")
    @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Produto produtoNovo, @PathVariable Long id) throws Exception {
        service.atualizar(id, produtoNovo);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Método de cálculo de desconto")
    @ApiResponse(responseCode = "200", description = "Desconto calculado com sucesso")
    @GetMapping("/{id}/desconto")
    public ResponseEntity<Desconto> calculoPrecoDesconto(@PathVariable long id, @RequestParam int percentual) {
        Desconto desconto = new Desconto();
        service.buscarPorId(id);
        if(percentual > 50)
            return ResponseEntity.badRequest().build();
        double preco = service.buscarPorId(id).getPreco();
        desconto.setNome(service.buscarPorId(id).getNome());
        desconto.setPrecoOriginal(preco);
        desconto.setDescontoPercentual(percentual);
        desconto.setPorcentagem(percentual);
        desconto.calcularPrecoFinal();
        return ResponseEntity.ok(desconto);
    }
}
