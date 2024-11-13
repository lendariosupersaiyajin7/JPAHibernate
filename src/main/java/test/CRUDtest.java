package test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Cliente;
import model.Vendedor;
import model.CarrinhoDeCompras;
import model.HistoricoDeCompras;
import model.Jogos;

public class CRUDtest {
	public static void main(String[] args) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
	    EntityManager em = emf.createEntityManager();

	    // Inicia a transação antes de qualquer operação
	    em.getTransaction().begin();

	    // CREATE: Criando instâncias de Cliente, Vendedor, Jogo e Carrinho de Compras
	    Cliente cliente1 = new Cliente("Mauro", "mauro@gmail.com", "1111111", "1111-1111");
	    Vendedor vendedor1 = new Vendedor("Vendedor1", "vendedor1@gmail.com", "senha1", "1234-5678");
	    Jogos jogo1 = new Jogos("Jogo 1", "Descrição do Jogo 1", 59.99, "Ação", vendedor1);
	    CarrinhoDeCompras carrinho1 = new CarrinhoDeCompras(cliente1, jogo1, 2);

	    em.persist(cliente1);
	    em.persist(vendedor1);
	    em.persist(jogo1);
	    em.persist(carrinho1);

	    // READ: Consultando todos os clientes e exibindo informações
	    Query queryCliente = em.createQuery("select c from Cliente c");
	    List<Cliente> clientes = queryCliente.getResultList();
	    for (Cliente c : clientes) {
	        System.out.println("Nome do Cliente: " + c.getNome() + " / CPF: " + c.getCpf());
	    }

	    // UPDATE: Atualizando o CPF de um cliente
	    Cliente clienteProcurado = buscarPorCpf("1111-1111", em);
	    if (clienteProcurado != null) {
	        System.out.println("CPF do cliente antes da atualização: " + clienteProcurado.getCpf());
	        // Atualizando o CPF
	        clienteProcurado.setCpf("22222222222"); // Novo CPF
	        em.merge(clienteProcurado); // Commit da atualização
	        System.out.println("CPF do cliente após atualização: " + clienteProcurado.getCpf());
	    } else {
	        System.out.println("Cliente não encontrado para o CPF: 1111111");
	    }

	    // DELETE: Removendo um cliente com CPF "1111111"
	    Cliente cliente = buscarPorCpf("1111-1111", em);
	    if (cliente != null) {
	        em.remove(cliente);
	        System.out.println("Cliente removido com CPF: 1111111");
	    } else {
	        System.out.println("Cliente não encontrado para remoção.");
	    }

	    // Commitando a transação após todas as operações
	    em.getTransaction().commit();

	    // Fechando o EntityManager
	    em.close();
	    emf.close();
	}

	public static Cliente buscarPorCpf(String cpf, EntityManager em) {
	    Query query = em.createQuery("select c from Cliente c where c.cpf = :cpf");
	    query.setParameter("cpf", cpf);
	    List<Cliente> clientes = query.getResultList();
	    if (clientes.isEmpty()) {
	        System.out.println("Cliente com CPF " + cpf + " não encontrado.");
	        return null;
	    }
	    return clientes.get(0);
	}



}
