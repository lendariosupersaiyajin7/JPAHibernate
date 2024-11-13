package model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Vendedor")
public class Vendedor {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
    private static EntityManager em = emf.createEntityManager();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Vendedor")
    private Long idVendedor;

    @Column(name = "Nome_Vendedor")
    private String nome;

    @Column(name = "Email_Vendedor")
    private String email;

    @Column(name = "Senha_Vendedor")
    private String senha;

    @Column(name = "Cpf_Vendedor")
    private String cpf;
    
    public Vendedor() {}
    
    public Vendedor(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }
    
   

    // Getters and setters
    public long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(long idVendedor) {
        this.idVendedor = idVendedor;
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
    
    // METODOS
    public static void listavendedores() {
    	em.getTransaction().begin();
    	List<Vendedor> vendedores = em.createQuery("SELECT v FROM Vendedor v", Vendedor.class).getResultList();
    	em.getTransaction().commit();
    	
    	if(vendedores.isEmpty()) {
    		System.out.println("Nenhum vendedor cadastrado");
    	} else {
    		System.out.println("LISTA DE CLIENTES CADASTRADOS\n");
    		for(Vendedor vendedor : vendedores) {
    			System.out.println("ID: " + vendedor.getIdVendedor() + " | Nome: " + vendedor.getNome() + " | E-mail: " + vendedor.getEmail() + " | CPF: " + vendedor.getCpf());    			
    		}
    	}
    }
}