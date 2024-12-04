package br.csi.e_commerce.service;

import br.csi.e_commerce.model.cliente.Cliente;
import br.csi.e_commerce.model.cliente.ClienteRepository;
import br.csi.e_commerce.model.cliente.DadosCliente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        this.repository.save(cliente);
    }

    public List<DadosCliente> listar(){
        return this.repository.findAll().stream().map(DadosCliente::new).toList();
    }

    public DadosCliente getClienteUUID(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findClienteByUuid(uuidformatado);
    }



}
