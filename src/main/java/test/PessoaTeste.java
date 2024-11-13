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
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		System.out.println("Nome do cliente: " + cliente.getNome());
		
		
	}

}
