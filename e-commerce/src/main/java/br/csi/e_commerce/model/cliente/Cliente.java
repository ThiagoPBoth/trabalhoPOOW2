package br.csi.e_commerce.model.cliente;

import br.csi.e_commerce.model.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cliente")
public class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @UuidGenerator
    private UUID uuid;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @Size(max = 14, min = 14, message = "quantidade de numeros invalida para CPF")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_endereco")
    private Endereco endereco;

}
