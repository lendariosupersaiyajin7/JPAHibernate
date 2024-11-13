package model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "CarrinhoDeCompras")
public class CarrinhoDeCompras {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
    private static EntityManager em = emf.createEntityManager();
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Carrinho")
    private Long id;

    // RELACIONAMENTO COM O CLIENTE (CHAVE ESTRANGEIRA)
    @ManyToOne
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    private Cliente cliente;

    // RELACIONAMENTO COM O JOGO (CHAVE ESTRANGEIRA)
    @ManyToOne
    @JoinColumn(name = "Id_Jogo", referencedColumnName = "Id_Jogo")
    private Jogos jogo;

    // ATRIBUTO DE QUANTIDADE DO JOGO NO CARRINHO
    @Column(name = "Quantidade")
    private int quantidade;
    
    public CarrinhoDeCompras() {}
    
    public CarrinhoDeCompras(Cliente cliente, Jogos jogo, int quantidade) {
        this.cliente = cliente;
        this.jogo = jogo;
        this.quantidade = quantidade;
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
    
   

    public void adicionarNoCarrinho() {

    	em.getTransaction().begin();
    	CarrinhoDeCompras carrinhoExistente = em.createQuery(
    			"SELECT c FROM CarrinhoDeCompras c WHERE c.cliente = :cliente AND c.jogo = :jogo", 
    			CarrinhoDeCompras.class)
    			.setParameter("cliente", this.cliente)
    			.setParameter("jogo", this.jogo)
    			.getResultList().stream().findFirst().orElse(null);
    
    	if (carrinhoExistente != null) {

    		carrinhoExistente.setQuantidade(carrinhoExistente.getQuantidade() + this.quantidade);
    		em.merge(carrinhoExistente);
    		System.out.println("Quantidade atualizada no carrinho.");
    	} else {

    		em.persist(this);
    		System.out.println("Jogo adicionado ao carrinho.");
    	}
    	em.getTransaction().commit();
    }
    
    public void removerDoCarrinho() {
        em.getTransaction().begin();

        // Encontrar o carrinho existente
        CarrinhoDeCompras carrinhoExistente = em.createQuery(
            "SELECT c FROM CarrinhoDeCompras c WHERE c.cliente = :cliente AND c.jogo = :jogo", 
            CarrinhoDeCompras.class)
            .setParameter("cliente", this.cliente)
            .setParameter("jogo", this.jogo)
            .getResultList().stream().findFirst().orElse(null);

        if (carrinhoExistente != null) {
            // Verificar a quantidade e atualizar ou remover
            if (carrinhoExistente.getQuantidade() > 1) {
                carrinhoExistente.setQuantidade(carrinhoExistente.getQuantidade() - 1);
                em.merge(carrinhoExistente);
                System.out.println("Quantidade reduzida no carrinho.");
            } else {
                em.remove(carrinhoExistente);
                System.out.println("Jogo removido do carrinho.");

                // Adicionar ao histórico de compras
                HistoricoDeCompras historico = new HistoricoDeCompras(
                    this.cliente, 
                    this.jogo, 
                    LocalDate.now().toString(), // Data atual
                    1,  // Quantidade removida (supondo que está removendo uma unidade)
                    this.jogo.getPreco()  // Preço do jogo
                );

                // Persistir o histórico
                em.persist(historico);
                System.out.println("Jogo adicionado ao histórico de compras.");
            }
        } else {
            System.out.println("Jogo não encontrado no carrinho.");
        }

        em.getTransaction().commit();
    }
    

}