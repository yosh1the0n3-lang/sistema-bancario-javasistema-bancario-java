import java.util.Scanner;
import java.util.ArrayList;

public class MeuPrimeiroProjeto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Usuario usuarioLogado = null;

        // Cria os usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Vinicius", "123"));
        usuarios.add(new Usuario("Melissa", "<3"));
        usuarios.add(new Usuario("Marco", "2025"));

        usuarios.get(0).getConta().depositar(2500);
        usuarios.get(1).getConta().depositar(5000);
        usuarios.get(2).getConta().depositar(5000);

        // Responsavel pelo login
        while (usuarioLogado == null) {

            System.out.println("Usuário:");
            String nome = sc.nextLine();

            System.out.println("Senha:");
            String senha = sc.nextLine();

            usuarioLogado = buscarUsuario(nome, senha, usuarios);

            if (usuarioLogado == null) {
                System.out.println("Login inválido.\n");
            }
        }

        System.out.println("Login realizado com sucesso!");

        int opcao = -1;

        // Esse é o Menu
        while (opcao != 0) {

            System.out.println("\nUsuário logado: " + usuarioLogado.getNome());
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Histórico");
            System.out.println("6 - Trocar de usuário");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("Saldo: " + usuarioLogado.getConta().getSaldo());
                    break;

                case 2:
                    System.out.println("Valor:");
                    usuarioLogado.getConta().depositar(sc.nextInt());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.println("Valor:");
                    if (!usuarioLogado.getConta().sacar(sc.nextInt())) {
                        System.out.println("Saldo insuficiente");
                    }
                    sc.nextLine();
                    break;

                case 4:
                    System.out.println("Transferir para quem?");
                    String nomeDestino = sc.nextLine();

                    Usuario destino = null;
                    for (Usuario u : usuarios) {
                        if (u.getNome().equalsIgnoreCase(nomeDestino)) {
                            destino = u;
                        }
                    }

                    if (destino == null || destino == usuarioLogado) {
                        System.out.println("Usuário inválido");
                        break;
                    }

                    System.out.println("Digite sua senha:");
                    String senha = sc.nextLine();

                    if (!usuarioLogado.verificarSenha(senha)) {
                        System.out.println("Senha incorreta");
                        break;
                    }

                    System.out.println("Valor:");
                    int valor = sc.nextInt();
                    sc.nextLine();

                    if (!usuarioLogado.getConta()
                            .transferir(valor, destino.getConta(), destino.getNome())) {
                        System.out.println("Saldo insuficiente");
                    }
                    break;

                case 5:
                    for (String h : usuarioLogado.getConta().getHistorico()) {
                        System.out.println(h);
                    }
                    break;

                case 6:
                    usuarioLogado = null;
                    opcao = -1;
                    System.out.println("Logout realizado.\n");

                    // volta pro login quando der logout
                    while (usuarioLogado == null) {
                        System.out.println("Usuário:");
                        String n = sc.nextLine();
                        System.out.println("Senha:");
                        String s = sc.nextLine();
                        usuarioLogado = buscarUsuario(n, s, usuarios);
                        if (usuarioLogado == null) {
                            System.out.println("Login inválido.\n");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

        sc.close();
    }

    // busca o usuario
    public static Usuario buscarUsuario(
            String nome,
            String senha,
            ArrayList<Usuario> usuarios) {

        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)
                    && u.verificarSenha(senha)) {
                return u;
            }
        }
        return null;
    }
}