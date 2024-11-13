package model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cliente") // Nome da tabela no banco de dados
public class Cliente {
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Cliente")  // Nome da coluna no banco de dados
    private Long id;

    @Column(name = "Nome_Cliente")
    private String nome;

    @Column(name = "Email_Cliente")
    private String email;

    @Column(name = "Senha_Cliente")
    private String senha;

    @Column(name = "Cpf_Cliente")
    private String cpf;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoDeCompras> carrinhosDeCompras;


    public Cliente() {
        // Construtor sem parâmetros
    }

    
    public Cliente(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    
    // GETTER E SETTER
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
