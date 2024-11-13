package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistoricoDeCompras")
public class HistoricoDeCompras {
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // ATRIBUTO PARA ARMAZENAR A DATA DA COMPRA
    @Column(name = "Data_Compra", nullable = false)
    private LocalDateTime dataCompra;

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

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}
