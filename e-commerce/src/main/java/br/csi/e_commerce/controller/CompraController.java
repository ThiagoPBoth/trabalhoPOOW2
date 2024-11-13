package br.csi.e_commerce.controller;


import br.csi.e_commerce.model.compra.Compra;
import br.csi.e_commerce.model.compra.CompraDTO;
import br.csi.e_commerce.model.compra.CompraRetornoDTO;
import br.csi.e_commerce.model.produto.Produto;
import br.csi.e_commerce.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private CompraService service;

    public CompraController(CompraService service) {
        this.service = service;
    }

    @PostMapping("/comprar")
    @Operation(summary = "Cria uma compra", description = "Cadastra uma compra com todas as regras de negócio - calcula o preco - verifica se tem produto e em estoque - etc / existe um DTO para receber com o id do cliente e os ids dos produtos comprados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra efetuada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "404", description = "Compra não caadstrada", content = @Content)
    })
    public void cadastrarCompra(@RequestBody CompraDTO compraDTO){
        this.service.cadastrarCompra(compraDTO);
    }


}
