package br.csi.e_commerce.service;

import br.csi.e_commerce.model.compra.CompraRetornoDTO;
import br.csi.e_commerce.model.itemCompra.ItemCompra;
import br.csi.e_commerce.model.itemCompra.ItemCompraRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemCompraService {

    private ItemCompraRepository itemCompraRepository;

    public ItemCompraService(ItemCompraRepository itemCompraRepository) {
        this.itemCompraRepository = itemCompraRepository;
    }

    public ItemCompra buscarItemCompraPorID(Long id) {
        return itemCompraRepository.getReferenceById(id);
    }
}
