package br.csi.e_commerce.model.cliente;

public record DadosCliente(Long id, String email, String nome, String cpf, String permissao) {
    public DadosCliente(Cliente cliente){
        this(cliente.getId(), cliente.getEmail(), cliente.getEmail(), cliente.getCpf(), cliente.getPermissao());
    }
}
