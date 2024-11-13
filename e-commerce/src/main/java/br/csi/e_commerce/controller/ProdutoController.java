package br.csi.e_commerce.controller;

import br.csi.e_commerce.model.produto.Produto;
import br.csi.e_commerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Busca os produtos", description = "Retorna tosos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produtos não encontrados", content = @Content)
    })
    public List<Produto> listar(){
        return this.service.listar();
    }

    @PostMapping
    @Operation(summary = "Cria um produto", description = "Retorna um produto correspondente ao UUID criado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não cadastrado", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Produto produto, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(produto);
        //created(uri) irá colocar no cabeçalho da requisição da resposta
        // o parâmetro Location com a URI de acesso ao recurso criado
        URI uri = uriComponentsBuilder.path("/produto/uuid/{uuid}").buildAndExpand(produto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(produto);

    }


    @Operation(summary = "Busca um produto", description = "Retorna um produto correspondente ao UUID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })


    @GetMapping("/uuid/{uuid}")
    public Produto produto(@PathVariable String uuid){
        return this.service.getProdutoUUID(uuid);
    }
}
