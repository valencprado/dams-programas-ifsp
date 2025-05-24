package com.ptbdams.apicrud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Schema(description = "Entidade que representa o produto")
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id" // Use the 'id' field of Produto as the identifier
)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 30, message = "Nome do produto deve ter entre 3 e 30 caracteres")
    @Column(unique = true)
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    @DecimalMax(value="10000", message = "Valor não pode ser superior a R$10.000,00")
    private Double preco;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    Categoria categoria;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Categoria getCategoria() { return this.categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }


    public Double getPreco() { return preco; }

    public void setPreco(Double preco) throws Exception {
        if(this.nome.contains("Promoção")&& preco > 500) {
        throw new Exception("Produto em promoção: não pode ter valor superior a R$500,00");
        } else {
        this.preco = preco;
        }
    }

}