package com.example.produtos.model;


import com.example.produtos.repository.ProdutosRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private ProdutosRepository produtosRepository;

    private ProdutosController(ProdutosRepository produtosRepository){
        this.produtosRepository = produtosRepository;
    }

    @PostMapping
    public Produto requisitarProduto(@RequestBody Produto produto){
        System.out.println("Produto adicionado: " + produto);
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        produtosRepository.save(produto);
        return produto;
    }
    @DeleteMapping("{id}")
    private void deletarProduto(@PathVariable("id") String id){
        System.out.println("Produto deletado com sucesso");
        produtosRepository.deleteById(id);
    }
    @PutMapping("{id}")
    private Produto atualizarProduto(@PathVariable("id") String id,
                                  @RequestBody Produto produto){

        System.out.println("Produto atualizado: " +produto);
        produto.setId(id);
        return produtosRepository.save(produto);

    }
    //Busca por parametro e todos da lista.
    @GetMapping
    private List<Produto> obterProdutoEspecifico(@PathParam("nome") String nome){

        if (nome == null || nome.isEmpty()){

            long contagemProdutos = produtosRepository.count();
            System.out.println("Produtos totais: " +contagemProdutos);

            return produtosRepository.findAll();
        }else {
            long contagemProdutos = produtosRepository.count();
            System.out.println("Produtos totais requisitados: " +contagemProdutos);
            return produtosRepository.findByNomeContaining(nome);
        }

    }
    //Busca por id.
    @GetMapping("{id}")
    private Produto obterPorId(@PathVariable("id") String id){

        return produtosRepository.findById(id).orElse(null);

    }


}
