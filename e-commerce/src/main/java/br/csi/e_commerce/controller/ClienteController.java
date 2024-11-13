package br.csi.e_commerce.controller;

import br.csi.e_commerce.model.cliente.Cliente;
import br.csi.e_commerce.model.produto.Produto;
import br.csi.e_commerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }



    @PostMapping
    @Operation(summary = "Cria um cliente", description = "Cadastra um cliente novo - jah deve vir com o endereco junto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não cadastrado", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriComponentsBuilder){
        this.service.salvar(cliente);
        URI uri = uriComponentsBuilder.path("/cliente/uuid/{uuid}").buildAndExpand(cliente.getUuid()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("/uuid/{uuid}")
    public Cliente cliente(@PathVariable String uuid){
        return this.service.getClienteUUID(uuid);
    }

    @GetMapping("/listar")
    @Operation(summary = "lista os clientes", description = "Vai trazer todos os clienets cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encotrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Clientes não encontrados", content = @Content)
    })
    public List<Cliente> listar(){
        return this.service.listar();

    }
}
