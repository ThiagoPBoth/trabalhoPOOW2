package br.csi.e_commerce.model.itemCompra;

import br.csi.e_commerce.model.produto.Produto;
import br.csi.e_commerce.model.compra.Compra;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "item_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "fk_id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fk_id_compra", nullable = false)
    private Compra compra;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "valor_unitario", nullable = false)
    private float valorUnitario;
}
