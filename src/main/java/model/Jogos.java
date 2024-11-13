package model;

import javax.persistence.*;

@Entity
@Table(name = "Jogos")
public class Jogos {
    // ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Jogo")  // Nome da coluna no banco de dados
	private Long id;


    @Column(name = "Nome_Jogo")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Preco")
    private double preco;

    @Column(name = "Categoria")
    private String categoria;
    
    public Jogos() {
        // Construtor sem parâmetros
    }
    
    public Jogos(String nome, String descricao, double preco, String categoria, Vendedor vendedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.vendedor = vendedor;
    }

    // RELACIONAMENTO COM VENDEDOR (CHAVE ESTRANGEIRA)
    @ManyToOne
    @JoinColumn(name = "Id_Vendedor", referencedColumnName = "Id_Vendedor")
    private Vendedor vendedor;

    // GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
