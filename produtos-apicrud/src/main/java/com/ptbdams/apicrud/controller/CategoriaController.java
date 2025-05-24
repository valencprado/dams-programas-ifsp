package com.ptbdams.apicrud.controller;

import com.ptbdams.apicrud.model.Categoria;
import com.ptbdams.apicrud.model.Produto;
import com.ptbdams.apicrud.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name="Categorias", description = "Métodos envolvendo a categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(description = "Cria uma categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(categoria));
    }

    @Operation(description = "Lista categorias criadas")
    @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso")

    @GetMapping
    public List<Categoria> listar() {
        return service.listarTodos();
    }

    @ApiResponse(responseCode = "200", description = "Categoria listada com sucesso")
    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(description = "Lista categoria específica")
    @ApiResponse(responseCode = "200", description = "Produtos da categoria listados com sucesso")
    @GetMapping("/{id}/produtos")
    public ResponseEntity<List<Produto>> listarProdutosDaCategoria(@PathVariable Long id) {
        Categoria categoria = service.buscarPorId(id);
        return ResponseEntity.ok(service.listarProdutosDaCategoria(id));
    }
    @Operation(description = "Deleta uma categoria")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
