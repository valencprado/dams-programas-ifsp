package com.ptbdams.apicrud.repository;

import com.ptbdams.apicrud.model.Categoria;
import com.ptbdams.apicrud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
