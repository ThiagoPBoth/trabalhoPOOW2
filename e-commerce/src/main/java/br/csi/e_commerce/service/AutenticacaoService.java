package br.csi.e_commerce.service;

import br.csi.e_commerce.model.cliente.Cliente;
import br.csi.e_commerce.model.cliente.ClienteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private ClienteRepository repository;

    public AutenticacaoService(ClienteRepository repository) {
        this.repository = repository;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Cliente cliente = this.repository.findByEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException("Usuario ou sneha inexistente");
        } else {
            UserDetails user = User.withUsername(cliente.getEmail()).password(cliente.getSenha()).authorities(cliente.getPermissao()).build();
            return user;
        }
    }

}
