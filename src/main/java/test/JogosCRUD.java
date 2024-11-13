package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Jogos;
import model.Vendedor;

public class JogosCRUD {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // CREATE
        Vendedor vendedor = new Vendedor("Ana Souza", "souza@gmail.com", "senha245", "964654332");
        em.persist(vendedor);
        Jogos jogo = new Jogos("Jogo de Ação", "Jogo divertido", 79.99, "Ação", vendedor);
        em.persist(jogo);

        // READ
        Jogos jogoBuscado = em.find(Jogos.class, jogo.getId());
        System.out.println("Jogo encontrado: " + jogoBuscado.getNome());

        // UPDATE
        jogoBuscado.setPreco(89.99);
        em.merge(jogoBuscado);

        // DELETE
        //em.remove(jogoBuscado);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
