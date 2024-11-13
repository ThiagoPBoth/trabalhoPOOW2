package br.csi.e_commerce.model.produto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
@Getter
@Setter
@ToString
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    private UUID uuid;

    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;

    @Column(name = "quantidade")
    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private int quantidade;

    @Column(name = "valor")
    @NotNull(message = "valor não pode ser nulo")
    @Min(value = 1, message = "O valor deve ser maior que 0")
    private float valor;
}
