package br.csi.e_commerce.service;

import br.csi.e_commerce.model.cliente.Cliente;
import br.csi.e_commerce.model.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void salvar(Cliente cliente){
        this.repository.save(cliente);
    }

    public List<Cliente> listar(){
        return this.repository.findAll();
    }

    public Cliente getClienteUUID(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findClienteByUuid(uuidformatado);
    }
}
