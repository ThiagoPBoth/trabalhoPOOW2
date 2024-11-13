package br.csi.e_commerce.model.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    Cliente findClienteByUuid(UUID uuidformatado);
}
