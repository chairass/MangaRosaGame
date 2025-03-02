import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MangaRosaMemoryGame {

    static Scanner scanner = new Scanner(System.in);
    private static int pontuacaoJ1 = 0;
    private static int pontuacaoJ2 = 0;
    private static int resposta = 0;
    private static String jogador1 = "PARTICIPANTE01";
    private static String jogador2 = "PARTICIPANTE02";
    private static int coluna;
    private static int linha;
    public static String[][] matriz;
    public static int tamanho;
    public static String tamTabuleiro;
    public static String[][] tabuleiro2;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[41m";
    public static final String BLUE = "\u001B[44m";

    public static void main(String[] args) {
        System.out.println("\u2550".repeat(22));
        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550".repeat(22));

        while (true) {
            System.out.println("1. Iniciar\n2. Pontuação participantes\n3. Regras do Jogo\n4. Sair");
            resposta = scanner.nextInt();

            if (resposta == 1) {
                iniciarJogo();
            } else if (resposta == 2) {
                mostrarPontuacao();
            } else if (resposta == 3) {
                mostrarRegras();
            } else if (resposta == 4) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                break;  // Sai do loop e finaliza o programa
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void tabuleiroOculto(){
        tabuleiro2 = new String[tamanho][tamanho];

        ArrayList<String> tabuleiro = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro.add("C");
            }
        }

        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro2 [i][j] = tabuleiro.get(index);
                index++;
            }
        }

        // Adicionando a numeração das colunas
        System.out.print("  "); // Espaço inicial para alinhar os números corretamente
        for (int j = 0; j < tamanho; j++) {
            System.out.print(" " + (j + 1) + "  ");
        }
        System.out.println();

        // Adicionando o tabuleiro com a numeração das linhas
        for (int i = 0; i < tamanho; i++) {
            System.out.print((i + 1) + "  "); // Numeração das linhas
            for (int j = 0; j < tamanho; j++) {
                System.out.print(tabuleiro2[i][j] + "   ");
            }
            System.out.println();
        }
    }

    private static void iniciarJogo() {
        System.out.print("Jogador 1: ");
        jogador1 = scanner.next();
        System.out.print("Jogador 2: ");
        jogador2 = scanner.next();

        System.out.println("Informe o tamanho do tabuleiro:\nA: 4x4\nB: 6x6\nC: 8x8 \nD: 10x10");
        tamTabuleiro = scanner.nextLine();


        switch (scanner.nextLine().toUpperCase()) {
            case "A":
                tamanho = 4;
                gerarTabuleiro();
                jogo();
                tabuleiroOculto();
                break;

            case "B":
                tamanho = 6;
                gerarTabuleiro();
                jogo();
                tabuleiroOculto();
                break;

            case "C":
                tamanho = 8;
                gerarTabuleiro();
                jogo();
                tabuleiroOculto();
                break;

            case "D":
                tamanho = 10;
                gerarTabuleiro();
                jogo();
                tabuleiroOculto();
                break;

            default:
                System.out.println("Opção inválida! Definindo padrão 4x4.");
                tamanho = 4;
                gerarTabuleiro();
                jogo();
                tabuleiroOculto();

        }
        System.out.print("Digite a posição da primeira carta que deseja revelar\nLinha: ");
        linha = scanner.nextInt();
        System.out.print("Coluna: ");
        coluna = scanner.nextInt();

        if (linha >= 0 && linha < matriz.length && coluna >= 0 && coluna < matriz[0].length){
            System.out.println("Carta revelada: " + matriz[linha][coluna]);
        } else {
            System.out.println("Posição inválida! Tente novamente.");
        }

    }

    private static void mostrarPontuacao() {
        if (pontuacaoJ1 == 0 && pontuacaoJ2 == 0) {
            System.out.println("Ainda não há pontuação");
        } else {
            System.out.println("O jogador " + jogador1 + " tem " + pontuacaoJ1 + " pontos.");
            System.out.println("O jogador " + jogador2 + " tem " + pontuacaoJ2 + " pontos.");
        }
    }

    private static void mostrarRegras() {
        System.out.println("=== Regras do Jogo ===");
        System.out.println("- Par com fundo amarelo → +1 ponto.");
        System.out.println("- Par com fundo da sua cor → +5 pontos.");
        System.out.println("- Par com fundo da cor do adversário → se errar, -2 pontos; se acertar, +1 ponto.");
        System.out.println("- A pontuação nunca pode ser negativa.");
        System.out.println("- Carta preta: Se errar o par → perde o jogo. Se acertar → ganha.");
    }

    public static void gerarTabuleiro() {
        matriz = new String[tamanho][tamanho];

        ArrayList<String> tabuleiro = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0
                 ; j < tamanho; j++) {
                tabuleiro.add(gerarStringAleatoria());
            }
        }

        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matriz[i][j] = tabuleiro.get(index);
                index++;
            }
        }
    }

    private static void jogo() {
        System.out.println("\u2550".repeat(22));
        System.out.println("Que comecem os jogos!");
        System.out.println("Jogador 1: " + jogador1 + " - " + pontuacaoJ1 + " " + RED + "   " + RESET +
                "       Jogador 2: " + jogador2 + " - " + pontuacaoJ2 + " "  + BLUE + "   " + RESET);
    }

    private static void exibirTabuleiro() {


        // Adicionando a numeração das colunas
        System.out.print("  "); // Espaço inicial para alinhar corretamente
        for (int j = 0; j < tamanho; j++) {
            System.out.print(" " + (j + 1) + "  ");
        }
        System.out.println();

        // Adicionando o tabuleiro com a numeração das linhas
        for (int i = 0; i < tamanho; i++) {
            System.out.print((i + 1) + " "); // Numeração das linhas
            for (int j = 0; j < tamanho; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }


    public static String gerarStringAleatoria() {
        String caracteresNumero = "0123456789";
        String caracteresLetra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        sb.append(caracteresNumero.charAt(random.nextInt(caracteresNumero.length())));
        sb.append(caracteresLetra.charAt(random.nextInt(caracteresLetra.length())));

        return sb.toString();
    }
}