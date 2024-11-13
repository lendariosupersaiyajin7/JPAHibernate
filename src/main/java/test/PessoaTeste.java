package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cliente;

public class PessoaTeste {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DesignPU");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public static void main(String[] args) {
	    System.out.println("rodando...");
	    
	    // Iniciar transação
	    entityManager.getTransaction().begin();
	    
	    // Operação de leitura ou escrita
	    Cliente cliente = entityManager.find(Cliente.class, 2L);
	    
	    // Verificar se cliente foi encontrado
	    if (cliente != null) {
	        System.out.println("Nome do cliente: " + cliente.getNome());
	    } else {
	        System.out.println("Cliente não encontrado!");
	    }
	    
	    // Confirmar transação
	    entityManager.getTransaction().commit();
	}


}
	