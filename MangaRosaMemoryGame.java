import java.sql.SQLOutput;
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
    private static int jogadasJ1 = 0; // Contador de jogadas do Jogador 1 //gio
    private static int jogadasJ2 = 0; // Contador de jogadas do Jogador 2 //gio
    private static int coluna1;
    private static int linha1;
    private static int coluna2;
    private static int linha2;
    public static String[][] matriz;
    static int roundcounter1 = 0;
    static int roundcounter2 = 0;
    public static int tamanho;
    private static boolean player1turn = true; // when player1 is choosing a card, this is true, when not, this is false
    public static String tamTabuleiro;
    public static String[][] tabuleiro2;
    public static String[][] cardcolors; // adicionei tambem esse array pras cores

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[41m";
    public static final String BLUE = "\u001B[44m";
    public static final String YELLOW = "\u001B[43m"; // adicionei as cores que faltavam
    public static final String BLACK = "\u001B[40m"; // adicionei as cores que faltavam

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
                break;

            case "B":
                tamanho = 6;
                break;

            case "C":
                tamanho = 8;
                break;

            case "D":
                tamanho = 10;
                break;

            default:
                System.out.println("Opção inválida! Definindo padrão 4x4.");
                tamanho = 4;
        }

        gerarTabuleiro();
        jogo();
        tabuleiroOculto();

        while (true) {
            String currentplayer = player1turn ? jogador1 : jogador2;
            System.out.println("Vez do jogador: " + currentplayer);


            System.out.print("Digite a posição da primeira carta que deseja revelar\nLinha: ");
            linha1 = scanner.nextInt() - 1;
            System.out.print("Coluna: ");
            coluna1 = scanner.nextInt() - 1;

            if (linha1 >= 0 && linha1 < tamanho && coluna1 >= 0 && coluna1 < tamanho && tabuleiro2[linha1][coluna1].equals("C")) {
                tabuleiro2[linha1][coluna1] = matriz[linha1][coluna1];
                System.out.println("Carta revelada: " + matriz[linha1][coluna1]);
                exibirTabuleiro();
            } else {
                System.out.println("Posição inválida! Tente novamente.");
                continue;
            }

            System.out.print("Digite a posição da segunda carta que deseja revelar\nLinha: ");
            linha2 = scanner.nextInt() - 1;
            System.out.print("Coluna: ");
            coluna2 = scanner.nextInt() - 1;

            if (linha2 >= 0 && linha2 < tamanho && coluna2 >= 0 && coluna2 < tamanho && tabuleiro2[linha2][coluna2].equals("C")) {
                tabuleiro2[linha2][coluna2] = matriz[linha2][coluna2];
                System.out.println("Carta revelada: " + matriz[linha2][coluna2]);
                exibirTabuleiro();
            } else {
                System.out.println("Posição inválida! Tente novamente.");
                tabuleiro2[linha1][coluna1] = "C";
                continue;
            }

            // incrementa as jogadas do jogador atual
            if (player1turn) { //gio
                jogadasJ1++; // incrementa as jogadas do jogador 1
            } else {
                jogadasJ2++; // incrementa as jogadas do jogador 2
            } //gio

                                                                                // remove bars when matriz evens be fixed
            if (cardcolors[linha1][coluna1].equals(cardcolors[linha2][coluna2]) /* && matriz[linha1][coluna1].equals(matriz[linha2][coluna2]))*/ {
                String color1 = cardcolors[linha1][coluna1];
                String color2 = cardcolors[linha2][coluna2];

                if (color1.equals(YELLOW) && color2.equals(YELLOW)) {
                    System.out.println("Par com fundo amarelo! +1 ponto para " + currentplayer);
                    if (player1turn) {
                        pontuacaoJ1++;
                    } else {
                        pontuacaoJ2++;
                    }
                } else if (color1.equals(RED) && color2.equals(RED) && player1turn) {
                    System.out.println("Par com fundo vermelho! +5 ponto para " + currentplayer);
                    pontuacaoJ1 += 5;
                } else if (color1.equals(BLUE) && color2.equals(BLUE) && !player1turn) {
                    System.out.println("Par com fundo azul! +5 ponto para " + currentplayer);
                    pontuacaoJ1 += 5;
                } else if (color1.equals(RED) && color2.equals(RED) && !player1turn) {
                    System.out.println("Par com fundo vermelho! +1 ponto para " + currentplayer);
                    pontuacaoJ1++;
                } else if (color1.equals(BLUE) && color2.equals(BLUE) && player1turn) {
                    System.out.println("Par com fundo azul! +1 ponto para " + currentplayer);
                    pontuacaoJ1++;
                } else if (color1.equals(BLACK) || color2.equals(BLACK)) {
                    if (color1.equals(BLACK) && color2.equals(BLACK)) {
                        System.out.println("Par com fundo preto! +50 pontos para " + currentplayer);
                        if (player1turn) {
                            pontuacaoJ1 += 50;
                        } else {
                            pontuacaoJ2 += 50;
                        }
                    } else {
                        System.out.println("Voce revelou uma carta preta e nao encontrou o par! -50 pontos para " + currentplayer);
                        if (player1turn) {
                            pontuacaoJ1 = Math.max(0, pontuacaoJ1 - 50);
                        } else {
                            pontuacaoJ2 = Math.max(0, pontuacaoJ2 - 50);
                        }
                        System.out.println("Fim do jogo!");
                        Pontuacao.mostrarPontuacao();
                        return;
                    }
                }
            }
        }
    }
    // verify if the game is over by searching the amount of board not founded yet
    private static boolean isGameOver() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro2[i][j].equals("C")) {
                    return false;
                }
            }
        }
        return true;
    }


    private static void mostrarPontuacao() {
        if (pontuacaoJ1 == 0 && pontuacaoJ2 == 0) {
            System.out.println("Ainda não há pontuação");
        } else {
            System.out.println("O jogador " + jogador1 + " tem " + pontuacaoJ1 + " pontos.(Jogadas: "+jogadasJ1+")");//gio: adicionei as jogadas aq
            System.out.println("O jogador " + jogador2 + " tem " + pontuacaoJ2 + " pontos.(Jogadas: "+jogadasJ2+")");
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
        cardcolors = new String[tamanho][tamanho];

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

        int totalcards = tamanho * tamanho;
        int redbluecards = totalcards / 2;
        int blackcards = 1;
        int yellowcards = totalcards - redbluecards - blackcards;

        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) { // colocar a verificacao da linha + coluna aqui dentro
                if (redbluecards > 0) {
                    cardcolors[i][j] = (random.nextBoolean()) ? RED : BLUE;
                    redbluecards--;
                } else if (blackcards > 0) {
                    cardcolors[i][j] = BLACK;
                    blackcards--;
                } else {
                    cardcolors[i][j] = YELLOW;
                }

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
                if (tabuleiro2[i][j].equals("C")) {

                    System.out.print("C  ");
                } else {

                    String color = cardcolors[i][j];
                    System.out.print(color + matriz[i][j] + RESET + "  ");
                }
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
