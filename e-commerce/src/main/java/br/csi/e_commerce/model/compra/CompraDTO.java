package br.csi.e_commerce.model.compra;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class CompraDTO {

    private Long clienteId;
    private List<ItemCompraDTO> itensCompra;

    public Long getClienteId() {
        return clienteId;
    }

    public List<ItemCompraDTO> getItensCompra() {
        return itensCompra;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ItemCompraDTO {
        private Long produtoId;
        private int quantidade;
    }
}
