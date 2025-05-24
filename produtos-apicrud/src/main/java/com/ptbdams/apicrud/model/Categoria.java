package com.ptbdams.apicrud.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.util.ArrayList;
import java.util.List;
@Entity
@Schema(description = "Estrutura que reperesenta a categoria de um produto")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id" // Use the 'id' field of Categoria as the identifier
)
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "Nome da categoria é obrigatório")
    @Size(min = 3, max = 30, message = "Nome da categoria deve ter entre 3 e 30 caracteres")
    @Column(unique = true)
    String nome;
    @OneToMany(mappedBy = "categoria",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> getProdutos() {return this.produtos;}
    public long getId() {return this.id;}
    public String getNome() {return this.nome;}

//    public void setProdutos() {}
    public void setId(Long id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}

}
