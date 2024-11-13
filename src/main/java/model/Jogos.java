package model;

import java.util.List;
import java.util.Scanner;

import javax.persistence.*;

@Entity
@Table(name = "Jogos")
public class Jogos {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
    private static EntityManager em = emf.createEntityManager();
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
    
    //METODOS
    public void listaJogos() {
    	em.getTransaction().begin();
    	List<Jogos> jogos = em.createQuery("SELECT j FROM Jogos j", Jogos.class).getResultList();
    	em.getTransaction().commit();
    	
    	if(jogos.isEmpty()) {
    		System.out.println("Nenhum jogo no catálogo\n");
    	} else {
    		System.out.println("LISTA DE JOGOS\n");
    		for(Jogos jogo : jogos) {
    			System.out.println("ID: " + jogo.getId() + " | Nome: " + jogo.getNome() + " | Preço: " + jogo.getPreco());    			
    		}
    	}	
    }
    
    
    public void inserirJogo(Scanner scanner) {
	    System.out.print("\nInforme o nome do jogo:\n ");
	    String nome = scanner.nextLine();  
	
	    System.out.print("Informe a descrição do jogo:\n ");
	    String descricao = scanner.nextLine();  
	
	    System.out.print("Informe o preço do jogo:\n ");
	    double preco = scanner.nextDouble();
	    scanner.nextLine();  
	
	    System.out.print("Informe a categoria do jogo:\n ");
	    String categoria = scanner.nextLine(); 
	
	    Vendedor vendedor = new Vendedor("Vendedor Exemplo", "vendedodasdasr@exemplo.com", "senha123", "17772345678900");
	    em.getTransaction().begin();
	    em.persist(vendedor);  // Persistir vendedor no banco
	    em.getTransaction().commit();
	
	    Jogos novoJogo = new Jogos(nome, descricao, preco, categoria, vendedor);
	    
	    em.getTransaction().begin();
	    em.persist(novoJogo);  // Persistir jogo no banco
	    em.getTransaction().commit();
	
	    System.out.println("Jogo inserido com sucesso!");
	}
    
    public static void atualizarPrecoJogo(Scanner scanner) {
        System.out.print("\nInforme o ID do jogo que deseja atualizar o preço: ");
        long idJogo = scanner.nextLong();
        scanner.nextLine();

        Jogos jogoBuscado = em.find(Jogos.class, idJogo);

        if (jogoBuscado != null) {
            System.out.print("Informe o novo preço para o jogo: ");
            double novoPreco = scanner.nextDouble();
            scanner.nextLine();

            jogoBuscado.setPreco(novoPreco);

            em.getTransaction().begin();
            em.merge(jogoBuscado);
            em.getTransaction().commit();

            System.out.println("Preço do jogo atualizado com sucesso!");
        } else {
            System.out.println("Jogo não encontrado com o ID fornecido.");
        }
    }
    
    public static void removerJogo(Scanner scanner) {
        System.out.print("\nInforme o ID do jogo que deseja remover: ");
        long idJogo = scanner.nextLong();
        scanner.nextLine();

        Jogos jogoBuscado = em.find(Jogos.class, idJogo);

        if (jogoBuscado != null) {
            em.getTransaction().begin();
            em.remove(jogoBuscado);
            em.getTransaction().commit();

            System.out.println("Jogo removido com sucesso!");
        } else {
            System.out.println("Jogo não encontrado com o ID fornecido.");
        }
    }

    
    
}