package com.example.produtos.repository;

import com.example.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produto, String> {

    List<Produto> findByNomeContaining(String nome);

}
