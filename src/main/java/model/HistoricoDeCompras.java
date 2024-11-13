package model;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "HistoricoDeCompras")
public class HistoricoDeCompras {
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Historico")
    private Long id;

    // RELACIONAMENTO COM O CLIENTE (CHAVE ESTRANGEIRA)
    @ManyToOne
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    private Cliente cliente;

    // RELACIONAMENTO COM O JOGO (CHAVE ESTRANGEIRA)
    @ManyToOne
    @JoinColumn(name = "Id_Jogo", referencedColumnName = "Id_Jogo")
    private Jogos jogo;

    // ATRIBUTO DE QUANTIDADE DO JOGO NA COMPRA
    @Column(name = "Quantidade")
    private int quantidade;
    
    @Column(name = "PrecoCompra")
    private double precoCompra;

    // ATRIBUTO PARA ARMAZENAR A DATA DA COMPRA
    @Column(name = "Data_Compra", nullable = false)
    private LocalDate dataCompra;  // Usando LocalDate ao invés de LocalDateTime


    public HistoricoDeCompras() {}
    

    public HistoricoDeCompras(Cliente cliente, Jogos jogo, String dataCompra, int quantidade, double precoCompra) {
        this.cliente = cliente;
        this.jogo = jogo;
        this.quantidade = quantidade;
        this.dataCompra = LocalDate.parse(dataCompra); // Ou LocalDateTime, dependendo do seu tipo
        this.precoCompra = precoCompra;
    }
    
    public double getPreco() {
    	return precoCompra;
    }
    
    public void setPreco(double precoCompra) {
        this.precoCompra = precoCompra;
    }
    

    
    // GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Jogos getJogo() {
        return jogo;
    }

    public void setJogo(Jogos jogo) {
        this.jogo = jogo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

}
