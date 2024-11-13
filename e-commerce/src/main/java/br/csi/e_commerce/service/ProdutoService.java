package br.csi.e_commerce.service;

import br.csi.e_commerce.model.produto.Produto;
import br.csi.e_commerce.model.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar(){
        return this.repository.findAll();
    }

    public void salvar(Produto produto){
        this.repository.save(produto);
    }


    public Produto getProdutoUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findProdutoByUuid(uuidformatado);
    }
}
