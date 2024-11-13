package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Vendedor;


public class VendedorCRUD {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // CREATE - Inserindo dois vendedores
        Vendedor vendedor1 = new Vendedor("Carlos Pereira", "carlos@gmail.com", "senha123", "1234-5678");
        Vendedor vendedor2 = new Vendedor("Ana Souza", "ana@gmail.com", "senha456", "9876-5432");

        em.persist(vendedor1);  // Persistindo vendedor1
        em.persist(vendedor2);  // Persistindo vendedor2

        // READ - Buscando os vendedores
        Vendedor vendedorBuscado1 = em.find(Vendedor.class, vendedor1.getIdVendedor());
        Vendedor vendedorBuscado2 = em.find(Vendedor.class, vendedor2.getIdVendedor());

        // Exibindo os vendedores encontrados
        System.out.println("Vendedor 1 encontrado: " + vendedorBuscado1.getNome() + " / CPF: " + vendedorBuscado1.getCpf());
        System.out.println("Vendedor 2 encontrado: " + vendedorBuscado2.getNome() + " / CPF: " + vendedorBuscado2.getCpf());

        // UPDATE - Atualizando o CPF do vendedor1
        vendedorBuscado1.setCpf("22222222222");  // Novo CPF
        em.merge(vendedorBuscado1);  // Atualizando vendedor no banco

        // DELETE - Removendo o vendedor2
        em.remove(vendedorBuscado2);  // Removendo vendedor2 do banco de dados

        // Cometendo as transações
        em.getTransaction().commit();

        // Verificando as operações realizadas
        System.out.println("Vendedor 1 após atualização: " + vendedorBuscado1.getNome() + " / Novo CPF: " + vendedorBuscado1.getCpf());
        System.out.println("Vendedor 2 removido do banco.");

        // Fechando o EntityManager
        em.close();
        emf.close();
    }
}

