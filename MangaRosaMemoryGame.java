import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MangaRosaMemoryGame {

    /// declarei as variaveis
    static Scanner scanner = new Scanner(System.in);
    private static int pontuacaoJ1 = 0;
    private static int pontuacaoJ2 = 0;
    private static int resposta = 3;
    private static String jogador1 = "PARTICIPANTE01";
    private static String jogador2 = "PARTICIPANTE02";
    private static String tamTabuleiro;
    private static int coluna;
    private static int linha;
    public static String[][] matriz;
    public static String[][] tabuleiroOculto;


    /// cores
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[41m";
    public static final String BLUE = "\u001B[44m";

    public static void main(String[] args) {
<<<<<<< HEAD
        System.out.println("\u2550".repeat(22));        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550".repeat(22));

        while (resposta <= 3) {
=======
        System.out.println("\u2550".repeat(22));
        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550".repeat(22));

        String[][] matrizTabuleiro = new String[4][4];

        ArrayList<String> tabuleiro1 = new ArrayList<>();

        while (resposta <= 3){
>>>>>>> 19db57922f9932f5371ecae0c22ef06c4b9629a7
            System.out.println("1. Iniciar\n2. Pontuação participantes\n3. Regras do Jogo\n4. Sair");
            resposta = scanner.nextInt();
            if (resposta == 1) {
                System.out.println("\u2550".repeat(22));
                System.out.println("Para começar o jogo informe o nome dos participantes");
                System.out.print("Jogador 1: ");
                jogador1 = scanner.next();
                System.out.print("Jogador 2: ");
                jogador2 = scanner.next();

                System.out.println("\u2550".repeat(22));
                System.out.println("Informe o tamanho do tabuleiro que voce deseja:\nA: 4x4\nB: " +
                        "6x6\nC: 8x8 \nD: 10x10");
                tamTabuleiro = scanner.next();

                if (tamTabuleiro.equalsIgnoreCase("A")) {
                    System.out.println("Jogador 1: " + jogador1 + " - " + pontuacaoJ1 + " " + RED + "   " + RESET + "       Jogador 2: " + jogador2 + " - " + pontuacaoJ2 + " "  + BLUE + "   " + RESET);
                    gerarTabuleiro();
                    exibirTabuleiro();
                    jogo();
                }
                else if (tamTabuleiro.equalsIgnoreCase("B")) {
                    System.out.println("6x6");
                }
                else if (tamTabuleiro.equalsIgnoreCase("C")) {
                    System.out.println("8x8");
                }
                else if (tamTabuleiro.equalsIgnoreCase("D")) {
                    System.out.println("10x10");
                }
            }

            if (resposta == 2) {
                if (pontuacaoJ1 == 0 && pontuacaoJ2 == 0) {
                    System.out.println("Ainda não há pontuação");
                } else {
                    System.out.println("O jogador" + jogador1 + "tem " + pontuacaoJ1 + "pontos " +
                            "O jogador" + jogador2 +
                            " tem " + pontuacaoJ2 + "pontos ");
                }
            }
            if (resposta == 3) {
                System.out.println("Aqui vai as regras do jogo");
                System.out.println("Nomes dos jogadores: Cada jogador deve ter um nome. Se não for informado, os nomes padrão serão \"PARTICIPANTE01\" e \"PARTICIPANTE02\".\n" +
                        "Pontuação:\n" +
                        "Par com fundo amarelo → +1 ponto.\n" +
                        "Par com fundo da sua cor → +5 pontos.\n" +
                        "Par com fundo da cor do adversário → se errar, -2 pontos; se acertar, +1 ponto.\n" +
                        "A pontuação nunca pode ser negativa (se perder mais do que tem, fica com 0).\n" +
                        "Carta preta:\n" +
                        "Se errar o par → perde o jogo.\n" +
                        "Se acertar o par → ganha o jogo.\n" +
                        "Jogo:\n" +
                        "Escolha o tamanho do tabuleiro corretamente. Se for inválido, será exibida uma mensagem de aviso.\n" +
                        "Informe a linha e coluna da carta que deseja virar.\n" +
                        "Se escolher uma posição inválida, tente novamente até acertar ou errar 3 vezes. Após 3 erros, perde a vez.\n" +
                        "Se escolher uma carta já virada, será avisado e precisará escolher outra. Após 3 erros, perde a vez.");

            }
        }
    }

    public static void gerarTabuleiro() {
        matriz = new String[4][4]; // Agora a matriz global é inicializada

        ArrayList<String> tabuleiro = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tabuleiro.add(gerarStringAleatoria()); // usa a função de gerar a string
            }
        }

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = tabuleiro.get(index);
                index++;
            }
        }
    }
    public  static void tabuleiroOculto(){

    }
    public static void exibirTabuleiro(){
        System.out.println("\u2550".repeat(22));
        System.out.println("Que comece os jogos !!!");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void tabuleiro(){
        System.out.print("Digite a posição da primeira carta que deseja revelar\nLinha: ");
        linha = scanner.nextInt();
        System.out.println("Coluna");
        coluna = scanner.nextInt();

        matriz = new String[4][4];
    }

    public static void jogo(){

        if (linha >= 0 && linha < matriz.length && coluna >= 0 && coluna < matriz[0].length) {
            System.out.println("Carta revelada: " + matriz[linha][coluna]);
        } else {
            System.out.println("Posição inválida! Tente novamente.");
        }

    }

    public static String gerarStringAleatoria() {

        String caracteresNumero = "0123456789";
        String caracteresLetra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 1; i++) {

            int index = random.nextInt(caracteresNumero.length());
            sb.append(caracteresNumero.charAt(index));
        }

        for (int i = 0; i < 1; i++) {

            int index = random.nextInt(caracteresLetra.length());
            sb.append(caracteresLetra.charAt(index));
        }

        return sb.toString();
    }

}


