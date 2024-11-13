package br.csi.e_commerce.model.itemCompra;

public class ItemCompraDTO {

    private Long fk_id_produto;
    private float valor_unitario;

    // Construtor
    public ItemCompraDTO(Long fk_id_produto, float valor_unitario) {
        this.fk_id_produto = fk_id_produto;
        this.valor_unitario = valor_unitario;
    }

    // Getter correto para 'produtoId'
    public Long getProdutoId() {
        return fk_id_produto;
    }

    public void setProdutoId(Long fk_id_produto) {
        this.fk_id_produto = fk_id_produto;
    }

    // Getter correto para 'valorUnitario'
    public float getValorUnitario() {
        return valor_unitario;
    }

    public void setValorUnitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

}
