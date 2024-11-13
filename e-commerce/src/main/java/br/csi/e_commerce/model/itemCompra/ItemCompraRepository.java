package br.csi.e_commerce.model.itemCompra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemCompraRepository extends JpaRepository<ItemCompra,Long> {

    @Query(value = "SELECT fk_id_produto, valor_unitario FROM item_compra WHERE fk_id_compra = :id", nativeQuery = true)
    List<ItemCompraDTO> buscarProdutosCompra(@Param("id") Long id);
}
