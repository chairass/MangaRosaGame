import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Pontuacao {
    static Scanner scanner = new Scanner(System.in);
    private static int pontuacaoJ1 = 0;
    private static int pontuacaoJ2 = 0;
    private static String jogador1 = "PARTICIPANTE01";
    private static String jogador2 = "PARTICIPANTE02";
    private static String tamTabuleiro;
    private static String[][] matrizTabuleiro;
    private static boolean[][] revelado;

    public static void main(String[] args) {
        System.out.println("\u2550".repeat(22));
        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550".repeat(22));

        int resposta = 0;
        while (resposta != 4) {
            System.out.println("\n1. Iniciar\n2. Pontuação participantes\n3. Regras do Jogo\n4. Sair");
            resposta = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (resposta == 1) iniciarJogo();
            else if (resposta == 2) mostrarPontuacao();
            else if (resposta == 3) mostrarRegras();
        }
    }

    public static void iniciarJogo() {
        System.out.println("Para começar o jogo, informe o nome dos participantes.");
        System.out.print("Jogador 1: ");
        jogador1 = scanner.nextLine();
        System.out.print("Jogador 2: ");
        jogador2 = scanner.nextLine();

        System.out.println("Informe o tamanho do tabuleiro desejado:\nA: 4x4\nB: 6x6\nC: 8x8 \nD: 10x10");
        String escolha = scanner.nextLine().toUpperCase();

        int tamanho;
        switch (escolha) {
            case "A" -> tamanho = 4;
            case "B" -> tamanho = 6;
            case "C" -> tamanho = 8;
            case "D" -> tamanho = 10;
            default -> {
                System.out.println("Opção inválida! Definindo padrão 4x4.");
                tamanho = 4;
            }
        }

        matrizTabuleiro = gerarTabuleiro(tamanho);
        revelado = new boolean[tamanho][tamanho];

        jogar();
    }

    public static void jogar() {
        boolean jogoAtivo = true;
        int turno = 1;

        while (jogoAtivo) {
            imprimirTabuleiro();

            System.out.println("\nVez do jogador " + (turno % 2 == 1 ? jogador1 : jogador2));
            int[] pos1 = escolherCarta();
            int[] pos2 = escolherCarta();

            revelarCartas(pos1, pos2);
            imprimirTabuleiro();

            if (matrizTabuleiro[pos1[0]][pos1[1]].equals(matrizTabuleiro[pos2[0]][pos2[1]])) {
                System.out.println("Par encontrado! 🎉");

                if (turno % 2 == 1) pontuacaoJ1 += 5;
                else pontuacaoJ2 += 5;
            } else {
                System.out.println("Errado! 😢");

                if (turno % 2 == 1) pontuacaoJ1 = Math.max(0, pontuacaoJ1 - 2);
                else pontuacaoJ2 = Math.max(0, pontuacaoJ2 - 2);
            }

            turno++;

            if (verificarFimDeJogo()) {
                System.out.println("Fim de jogo!");
                jogoAtivo = false;
            }
        }

        mostrarPontuacao();
    }

    public static int[] escolherCarta() {
        int x, y;
        do {
            System.out.print("Escolha uma linha: ");
            x = scanner.nextInt();
            System.out.print("Escolha uma coluna: ");
            y = scanner.nextInt();
        } while (x < 0 || x >= matrizTabuleiro.length || y < 0 || y >= matrizTabuleiro[0].length || revelado[x][y]);

        return new int[]{x, y};
    }

    public static void revelarCartas(int[] pos1, int[] pos2) {
        revelado[pos1[0]][pos1[1]] = true;
        revelado[pos2[0]][pos2[1]] = true;
    }

    public static void imprimirTabuleiro() {
        for (int i = 0; i < matrizTabuleiro.length; i++) {
            for (int j = 0; j < matrizTabuleiro[i].length; j++) {
                if (revelado[i][j]) System.out.print("[" + matrizTabuleiro[i][j] + "] ");
                else System.out.print("[X] ");
            }
            System.out.println();
        }
    }

    public static boolean verificarFimDeJogo() {
        for (boolean[] linha : revelado) {
            for (boolean carta : linha) {
                if (!carta) return false;
            }
        }
        return true;
    }

    public static String[][] gerarTabuleiro(int tamanho) {
        ArrayList<String> valores = new ArrayList<>();
        for (int i = 0; i < (tamanho * tamanho) / 2; i++) {
            String par = gerarStringAleatoria(2);
            valores.add(par);
            valores.add(par);
        }
        Collections.shuffle(valores);

        String[][] tabuleiro = new String[tamanho][tamanho];
        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = valores.get(index++);
            }
        }
        return tabuleiro;
    }

    public static String gerarStringAleatoria(int tamanho) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }

    public static void mostrarPontuacao() {
        System.out.println("\nPontuação:");
        System.out.println(jogador1 + ": " + pontuacaoJ1 + " pontos.");
        System.out.println(jogador2 + ": " + pontuacaoJ2 + " pontos.");
    }

    public static void mostrarRegras() {
        System.out.println("""
            🃏 REGRAS DO JOGO:
            - Encontre pares de cartas iguais.
            - Se encontrar um par, ganha +5 pontos.
            - Se errar, perde -2 pontos (pontuação nunca pode ser negativa).
            - O jogo termina quando todas as cartas forem reveladas.
        """);
    }
}
