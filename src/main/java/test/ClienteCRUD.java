package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cliente;


public class ClienteCRUD {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // CREATE - Inserindo dois clientes
        Cliente cliente1 = new Cliente("João Silva", "joao@gmail.com", "123456789", "senha123");
        Cliente cliente2 = new Cliente("Maria Oliveira", "maria@gmail.com", "987654321", "senha456");

        em.persist(cliente1);  // Persistindo cliente1
        em.persist(cliente2);  // Persistindo cliente2

        // READ - Buscando os clientes
        Cliente clienteBuscado1 = em.find(Cliente.class, cliente1.getId());
        Cliente clienteBuscado2 = em.find(Cliente.class, cliente2.getId());

        // Exibindo os clientes encontrados
        System.out.println("Cliente 1 encontrado: " + clienteBuscado1.getNome() + " / CPF: " + clienteBuscado1.getCpf());
        System.out.println("Cliente 2 encontrado: " + clienteBuscado2.getNome() + " / CPF: " + clienteBuscado2.getCpf());

        // UPDATE - Atualizando o CPF do cliente1
        clienteBuscado1.setCpf("11111111111");  // Novo CPF
        em.merge(clienteBuscado1);  // Atualizando cliente no banco

        // DELETE - Removendo o cliente2
        em.remove(clienteBuscado2);  // Removendo cliente2 do banco de dados

        // Cometendo as transações
        em.getTransaction().commit();

        // Verificando as operações realizadas
        System.out.println("Cliente 1 após atualização: " + clienteBuscado1.getNome() + " / Novo CPF: " + clienteBuscado1.getCpf());
        System.out.println("Cliente 2 removido do banco.");

        // Fechando o EntityManager
        em.close();
        emf.close();
    }
}
