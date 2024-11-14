package br.csi.e_commerce.service;

import br.csi.e_commerce.model.cliente.Cliente;
import br.csi.e_commerce.model.cliente.ClienteRepository;
import br.csi.e_commerce.model.compra.Compra;
import br.csi.e_commerce.model.compra.CompraDTO;
import br.csi.e_commerce.model.compra.CompraRepository;
import br.csi.e_commerce.model.itemCompra.ItemCompra;
import br.csi.e_commerce.model.itemCompra.ItemCompraDTO;
import br.csi.e_commerce.model.itemCompra.ItemCompraRepository;
import br.csi.e_commerce.model.produto.Produto;
import br.csi.e_commerce.model.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;
    float valorTotalCompra = 0;


    @Autowired
    private ItemCompraRepository itemCompraRepository;

    public CompraService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, CompraRepository compraRepository, ItemCompraRepository itemCompraRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.itemCompraRepository = itemCompraRepository;
    }

    public void cadastrarCompra(CompraDTO compraDTO) {
        valorTotalCompra = 0;

        Cliente cliente = clienteRepository.findById(compraDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Compra compra = new Compra();
        compra.setCliente(cliente);

        // Variável para armazenar o valor total da compra


        List<ItemCompra> itens = compraDTO.getItensCompra().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            // Verifica se a quantidade desejada está disponível em estoque
            if (produto.getQuantidade() < itemDTO.getQuantidade()) {
                throw new RuntimeException("Quantidade insuficiente em estoque para o produto: " + produto.getNome());
            }

            // Atualiza a quantidade do produto no estoque
            produto.setQuantidade(produto.getQuantidade() - itemDTO.getQuantidade());
            produtoRepository.save(produto);

            // Criação do item de compra
            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setProduto(produto);
            itemCompra.setQuantidade(itemDTO.getQuantidade());
            itemCompra.setValorUnitario(produto.getValor()); // Busca o valor do banco de dados
            itemCompra.setCompra(compra);

            // Calcula o valor do item (quantidade * valor unitário) e adiciona ao valor total da compra
            valorTotalCompra += itemCompra.getQuantidade() * itemCompra.getValorUnitario();

            return itemCompra;
        }).collect(Collectors.toList());

        // Atribui os itens à compra
        compra.setItensCompra(itens);
        compra.setValor(valorTotalCompra); // Armazena o valor total da compra

        // Salva a compra no banco de dados
        compraRepository.save(compra);
    }



}
