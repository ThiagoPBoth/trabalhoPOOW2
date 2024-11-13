package br.csi.e_commerce.model.compra;

import br.csi.e_commerce.model.itemCompra.ItemCompra;
import br.csi.e_commerce.model.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "compra")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "fk_id_cliente")
    private Cliente cliente;

    @JoinColumn(name = "valor")
    private float valor;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itensCompra;
}
