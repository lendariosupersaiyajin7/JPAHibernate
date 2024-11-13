package test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Jogos;
import model.Cliente;
import model.HistoricoDeCompras;

public class HistoricoDeComprasCRUD {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // CREATE
        Cliente cliente = new Cliente("Maria Ferreira", "maria@gmail.com", "987654321", "1122334455");
        Jogos jogo = new Jogos("Jogo de Estratégia", "Desafio mental", 99.99, "Estratégia", null);
        em.persist(cliente);
        em.persist(jogo);
        HistoricoDeCompras historico = new HistoricoDeCompras(cliente, jogo, "2024-11-13", 1);
        em.persist(historico);

        // READ
        HistoricoDeCompras historicoBuscado = em.find(HistoricoDeCompras.class, historico.getId());
        System.out.println("Histórico encontrado para o cliente: " + historicoBuscado.getCliente().getNome());

        // UPDATE
        historicoBuscado.setDataCompra("2024-11-14");
        em.merge(historicoBuscado);

        // DELETE
        //em.remove(historicoBuscado);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
