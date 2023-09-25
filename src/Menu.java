import java.util.Scanner;

public class Menu {
    
    public static void main(String[] args) {
        Menu.menuLogin();
    }

    public static void menuLogin(){
        
        RepositorioUsuarios repositorio = new RepositorioUsuarios();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n\n\n\n\n");
            System.out.println(" ==========================");
            System.out.println("|  Bem vindo ao MySquad!!  |");
            System.out.println("|  1 - Cadastrar usuário   |");
            System.out.println("|  2 - Fazer login         |");
            System.out.println("|  3 - Sair                |");
            System.out.println(" ==========================");
            System.out.print("\nOpcao: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.println("Digite o nome de usuário:");
                String nomeUsuario = scanner.next();
                System.out.println("Digite a senha:");
                String senha = scanner.next();
                repositorio.cadastrarUsuario(nomeUsuario, senha);

            } else if (opcao == 2) {
                
                boolean failure = false;
                while (!failure) {
                System.out.println("Digite o nome de usuário:");
                String nomeUsuario = scanner.next();
                System.out.println("Digite a senha:");
                String senha = scanner.next();
                System.out.println("\n");
                failure = repositorio.fazerLogin(nomeUsuario, senha);
                System.out.println("\n");

                }

                if (failure == true) {
                    Menu.menuPrincipal();
                }

            } else if (opcao == 3) {
                break;
                
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

     public static void menuPrincipal(){

        try (Scanner teclado = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n\n\n\n\n\n");
                System.out.println(" ==========================");
                System.out.println("|  O que deseja fazer?     |");
                System.out.println("|  1 - Editar perfil       |");
                System.out.println("|  2 - Adicionar jogo      |");
                System.out.println("|  3 - Pesquisar           |");
                System.out.println("|  4 - Amigos              |");
                System.out.println("|  5 - Sair                |");
                System.out.println(" ==========================");
                System.out.print("\nOpcao: ");
                int opcao = teclado.nextInt();

                System.out.println(opcao);
            }
        }

    }    


}
