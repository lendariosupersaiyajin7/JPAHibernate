package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.CarrinhoDeCompras;
import model.Cliente;
import model.Jogos;
import model.Vendedor;

public class CarrinhoComprasCRUD {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // CREATE
        Cliente cliente = new Cliente("Pedro Lima", "pedro@gmail.com", "123456789", "876543210");
        Vendedor vendedor = new Vendedor("Marcelo Rocha", "marcelo@gmail.com", "senha123", "1122334455");
        Jogos jogo = new Jogos("Jogo de Corrida", "Jogo emocionante", 49.99, "Corrida", vendedor);

        // Persistir primeiro o Cliente, Vendedor e Jogos
        em.persist(cliente);
        em.persist(vendedor);
        em.persist(jogo);

        // Agora o Hibernate pode gerar o ID automaticamente para o CarrinhoDeCompras
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras(cliente, jogo, 1);
        em.persist(carrinho);

        // READ
        CarrinhoDeCompras carrinhoBuscado = em.find(CarrinhoDeCompras.class, carrinho.getId());
        System.out.println("Carrinho encontrado para o cliente: " + carrinhoBuscado.getCliente().getNome());

        // UPDATE
        carrinhoBuscado.setQuantidade(2);
        em.merge(carrinhoBuscado);

        // DELETE
        //em.remove(carrinhoBuscado);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
