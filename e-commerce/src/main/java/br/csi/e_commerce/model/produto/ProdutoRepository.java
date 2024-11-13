package br.csi.e_commerce.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findProdutoByUuid(UUID uuid);
}
