import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cliente;
import model.HistoricoDeCompras;
import model.Jogos;
import model.Vendedor;
import model.CarrinhoDeCompras;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Scanner para capturar entradas do usuário
        Scanner sc1 = new Scanner(System.in); // Scanner adicional
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesignPU");
        EntityManager em = emf.createEntityManager();

        while (true) {
            System.out.println("====- MENU -====");
            System.out.println("1. Cadastro");
            System.out.println("2. Catálogo de Jogos");
            System.out.println("3. Carrinho de Compras");
            System.out.println("4. Histórico de Compras");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = sc.nextInt();  // Captura a opção do usuário
            sc.nextLine();  // Limpar o buffer de entrada

            if (opcao == 5) {
                break;  // Sair do loop e finalizar o programa
            }

            // Menu de Cadastro - Caso a opção 1 seja escolhida
            if (opcao == 1) {
                System.out.println("Você deseja cadastrar que tipo de conta?");
                System.out.println("1. Conta de Cliente");
                System.out.println("2. Conta de Vendedor");
                System.out.print("=> INSIRA SEU INPUT: ");
                int escolha = sc.nextInt();
                sc.nextLine();  // Limpar o buffer de entrada

                if (escolha == 1) {
                    // Cadastro de Cliente
                    System.out.println("==== Cadastro de Cliente ====");
                    System.out.print("Digite o nome do cliente: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite o e-mail do cliente: ");
                    String email = sc.nextLine();
                    System.out.print("Digite a senha do cliente: ");
                    String senha = sc.nextLine();
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = sc.nextLine();

                    // Criando o cliente
                    em.getTransaction().begin();
                    Cliente novoCliente = new Cliente(nome, email, senha, cpf);
                    em.persist(novoCliente);  // Persiste o cliente no banco
                    em.getTransaction().commit();
                    System.out.println("Cliente criado com sucesso!");
                } else if (escolha == 2) {
                    // Cadastro de Vendedor
                    System.out.println("==== Cadastro de Vendedor ====");
                    System.out.print("Digite o nome do vendedor: ");
                    String nomeVendedor = sc.nextLine();
                    System.out.print("Digite o e-mail do vendedor: ");
                    String emailVendedor = sc.nextLine();
                    System.out.print("Digite a senha do vendedor: ");
                    String senhaVendedor = sc.nextLine();
                    System.out.print("Digite o CPF do vendedor: ");
                    String cpfVendedor = sc.nextLine();

                    // Criando o vendedor
                    em.getTransaction().begin();
                    Vendedor novoVendedor = new Vendedor(nomeVendedor, emailVendedor, senhaVendedor, cpfVendedor);
                    em.persist(novoVendedor);  // Persiste o vendedor no banco
                    em.getTransaction().commit();
                    System.out.println("Vendedor criado com sucesso!");
                } else {
                    System.out.println("Opção inválida para cadastro.");
                }
            }

            // Catálogo de Jogos
            else if (opcao == 2) {
                System.out.println("Catálogo de Jogos");
                Jogos jogos = new Jogos();
                jogos.listaJogos();
                System.out.println("\nO que deseja fazer?");
                System.out.println("1. Inserir novo jogo");
                System.out.println("2. Atualizar preço de jogo existente");
                System.out.println("3. Deletar jogo existente");
                System.out.print("=> INSIRA SEU INPUT: ");
                
                int escolha = sc.nextInt(); 
                
                if(escolha == 1) {
                    jogos.inserirJogo(sc);   
                } else if(escolha == 2) {
                    jogos.atualizarPrecoJogo(sc);
                } else if(escolha == 3) {
                    jogos.removerJogo(sc);
                }     
            } 

            // Carrinho de Compras
            else if (opcao == 3) {
                System.out.println("Carrinho de Compras");
                System.out.println("1. Adicionar Jogo ao Carrinho");
                System.out.println("2. Remover Jogo do Carrinho");
                System.out.print("Escolha uma opção: ");
                int escolhaCarrinho = sc.nextInt();
                sc.nextLine();

                if (escolhaCarrinho == 1) {
                    // Adicionar Jogo ao Carrinho
                    System.out.print("Digite o ID do cliente: ");
                    Long clienteId = sc.nextLong();
                    System.out.print("Digite o ID do jogo: ");
                    Long jogoId = sc.nextLong();
                    System.out.print("Digite a quantidade do jogo: ");
                    int quantidade = sc.nextInt();

                    Cliente cliente = em.find(Cliente.class, clienteId);
                    Jogos jogo = em.find(Jogos.class, jogoId);

                    if (cliente != null && jogo != null) {
                        em.getTransaction().begin();
                        CarrinhoDeCompras carrinho = new CarrinhoDeCompras(cliente, jogo, quantidade);
                        em.persist(carrinho);  // Adiciona o jogo ao carrinho
                        em.getTransaction().commit();
                        System.out.println("Jogo adicionado ao carrinho com sucesso!");
                    } else {
                        System.out.println("Cliente ou Jogo não encontrado!");
                    }
                } else if (escolhaCarrinho == 2) {
                    // Remover Jogo do Carrinho
                    System.out.print("Digite o ID do carrinho para remover o jogo: ");
                    Long idCarrinho = sc.nextLong();
                    CarrinhoDeCompras carrinho = em.find(CarrinhoDeCompras.class, idCarrinho);

                    if (carrinho != null) {
                        em.getTransaction().begin();
                        em.remove(carrinho);  // Remove o jogo do carrinho
                        em.getTransaction().commit();
                        System.out.println("Jogo removido do carrinho com sucesso!");
                    } else {
                        System.out.println("Carrinho não encontrado!");
                    }
                }
            } 

            // Histórico de Compras
            else if (opcao == 4) {
                System.out.println("Histórico de Compras\n");
                HistoricoDeCompras hC = new HistoricoDeCompras();
                hC.listaCompras();
                      
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        em.close();
        emf.close();  // Fechar o EntityManager e o EntityManagerFactory
        sc.close();  // Fechar o Scanner
    }
}
