
import java.util.*;

public class MangaRosaMemoryGame {

    static Scanner scanner = new Scanner(System.in);
    private static int pontuacaoJ1 = 0, pontuacaoJ2 = 0; // inicia a pontuação como zero
    private static int resposta = 0;
    private static String jogador1 = "PARTICIPANTE01", jogador2 = "PARTICIPANTE02"; // Declarei o nome dos jogadores como padrão
    private static int jogadasJ1 = 0, jogadasJ2 = 0; // Contador de jogadas do Jogador 1 e 2 //gio
    private static int coluna1, linha1, coluna2, linha2, tamanho;
    public static String[][] tabuleiroPares; // matriz utilizada para guardar os pares de letras
   // static int roundcounter1 = 0;
   // static int roundcounter2 = 0;
    private static boolean player1turn = true; // quando o jogador1 está escolhendo uma carta, isso é verdade, quando não, isso é falso
    public static String tamTabuleiro;
    public static String[][] tabuleiro2; // matriz para o tabuleiro que fica escondido
    public static String[][] cardcolors; // matriz das cores


    //Definição das cores
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[41m";
    public static final String BLUE = "\u001B[44m";
    public static final String YELLOW = "\u001B[43m";
    public static final String BLACK = "\u001B[40m";

    public static void main(String[] args) {
        System.out.println("\u2550".repeat(22));
        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550".repeat(22));

        while (true) { // Inicia o menu de opções
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

    //Gera o tabuleiro que mostra só a letra "C"
    private static void tabuleiroOculto(){
        tabuleiro2 = new String[tamanho][tamanho];//o tamanho é usado como parametro pois precisa considerar o tamanho do tabuleiro que foi escolhido

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

        //Implementamos Switch case para verificar o tamanho escolhido pelo o usuario, e colocamos na variavel tamanho, foi usado toUpperCase para ignorar as maiusculas/minusculas
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
        exibirTabuleiro();
        tabuleiroOculto();

        while (true) {
            String currentplayer = player1turn ? jogador1 : jogador2;
            System.out.println("Vez do jogador: " + currentplayer);


            System.out.print("Digite a posição da primeira carta que deseja revelar\nLinha: ");
            linha1 = scanner.nextInt() - 1; //Diminuir 1 casa direto da informação que recebe do scan
            System.out.print("Coluna: ");
            coluna1 = scanner.nextInt() - 1;

            //Verifica se o usuario colocou a linha e a coluna valida
            if (linha1 >= 0 && linha1 < tamanho && coluna1 >= 0 && coluna1 < tamanho && tabuleiro2[linha1][coluna1].equals("C")) {
                tabuleiro2[linha1][coluna1] = tabuleiroPares[linha1][coluna1];
                System.out.println("Carta revelada: " + tabuleiroPares[linha1][coluna1]);
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
                tabuleiro2[linha2][coluna2] = tabuleiroPares[linha2][coluna2];
                System.out.println("Carta revelada: " + tabuleiroPares[linha2][coluna2]);
                exibirTabuleiro();
            } else {
                System.out.println("Posição inválida! Tente novamente.");
                tabuleiro2[linha1][coluna1] = "C";
                continue;
            }

           if (player1turn) { //gio
                jogadasJ1++; // incrementa as jogadas do jogador 1
            } else {
                jogadasJ2++; // incrementa as jogadas do jogador 2
            } //gio

            // remova as barras quando os pares da matriz forem corrigidos
            if (cardcolors[linha1][coluna1].equals(cardcolors[linha2][coluna2]) /* && matriz[linha1][coluna1].equals(matriz[linha2][coluna2]))*/) {
                String color1 = cardcolors[linha1][coluna1];
                String color2 = cardcolors[linha2][coluna2];


                if (color1.equals(YELLOW) && color2.equals(YELLOW)) { // quando color for igual a amarelo o jogador da vez ganha 2 pontos
                    System.out.println("Par com fundo amarelo! +1 ponto para " + currentplayer);
                    if (player1turn) {
                        pontuacaoJ1++;
                    } else {
                        pontuacaoJ2++;
                    }
                } else if (color1.equals(RED) && color2.equals(RED) && player1turn) { // se o jogador vermelho acertar o par da sua carta ganha 5 pontos, se for o jogador azu que tiver encontrado o seu par ele tambem ganha
                    System.out.println("Par com fundo vermelho! +5 ponto para " + currentplayer);
                    pontuacaoJ1 += 5;
                } else if (color1.equals(BLUE) && color2.equals(BLUE) && !player1turn) {
                    System.out.println("Par com fundo azul! +5 ponto para " + currentplayer);
                    pontuacaoJ1 += 5;
                } else if (color1.equals(RED) && color2.equals(RED) && !player1turn) {
                    System.out.println("Par com fundo vermelho! +1 ponto para " + currentplayer);// se o jogador encontra o par que nao for ele ganha 1 ponto
                    pontuacaoJ1++;
                } else if (color1.equals(BLUE) && color2.equals(BLUE) && player1turn) {
                    System.out.println("Par com fundo azul! +1 ponto para " + currentplayer);
                    pontuacaoJ1++;
                } else if (color1.equals(BLACK) || color2.equals(BLACK)) {
                    if (color1.equals(BLACK) && color2.equals(BLACK)) {
                        System.out.println("Par com fundo preto! +50 pontos para " + currentplayer); // se o jogador da vez encontrar o par de cartas preto ele ganha 50 pontos, se não ele perde o jogo
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
                        //Pontuacao.mostrarPontuacao();
                        return;
                    }
                }
            }
        }
    }
    // verifique se o jogo acabou pesquisando a quantidade de tabuleiro ainda não encontrado
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

    // mostra a pontuação dos jogadores
    private static void mostrarPontuacao() {
        if (pontuacaoJ1 == 0 && pontuacaoJ2 == 0) {
            System.out.println("Ainda não há pontuação");
        } else {
            System.out.println("O jogador " + jogador1 + " tem " + pontuacaoJ1 + " pontos.(Jogadas: "+jogadasJ1+")");//gio: adicionei as jogadas aq
            System.out.println("O jogador " + jogador2 + " tem " + pontuacaoJ2 + " pontos.(Jogadas: "+jogadasJ2+")");
        }
    }

    // mostra as regras do jogo
    private static void mostrarRegras() {
        System.out.println("=== Regras do Jogo ===");
        System.out.println("- Par com fundo amarelo → +1 ponto.");
        System.out.println("- Par com fundo da sua cor → +5 pontos.");
        System.out.println("- Par com fundo da cor do adversário → se errar, -2 pontos; se acertar, +1 ponto.");
        System.out.println("- A pontuação nunca pode ser negativa.");
        System.out.println("- Carta preta: Se errar o par → perde o jogo. Se acertar → ganha.");
    }

    // Gera o tabuleiro e adiciona os pares de String
    public static void gerarTabuleiro() {
        tabuleiroPares = new String[tamanho][tamanho];
        cardcolors = new String[tamanho][tamanho];

        ArrayList<String> tabuleiro = new ArrayList<>();
        Set<String> uniquePairs = new HashSet<>();

        // Utilizamos o tamanho do tabuleiro multiplicado por ele mesmo divindo por 2, para que metade da matriz seja preenchida, e a outra metade fique igual
        while (uniquePairs.size() < (tamanho * tamanho) / 2) {
            String stringPairs = gerarStringAleatoria();
            if (uniquePairs.add(stringPairs)) {
                tabuleiro.add(stringPairs);
                tabuleiro.add(stringPairs);
            }
        }

        // Embaralhar cartas
        Collections.shuffle(tabuleiro);

        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiroPares[i][j] = tabuleiro.get(index);
                index++;
            }
        }

        // Divisão das cores
        int totalcards = tamanho * tamanho;
        int redcards = totalcards / 4;
        int bluecards = totalcards / 4;
        int blackcards = 2;
        int yellowcards = totalcards - (redcards + bluecards + blackcards);

        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (blackcards > 0) {
                    cardcolors[i][j] = BLACK;
                    blackcards--;
                } else if (redcards > 0) {
                    cardcolors[i][j] = RED;
                    redcards--;
                } else if (bluecards > 0) {
                    cardcolors[i][j] = BLUE;
                    bluecards--;
                } else {
                    cardcolors[i][j] = YELLOW;
                    yellowcards--;
                }
            }
        }
        //Collections.shuffle(cardcolors); FAZER ISSO FUNCIONAR
    }

    private static void jogo() {
        System.out.println("\u2550".repeat(22));
        System.out.println("Que comecem os jogos!");
        System.out.println("Jogador 1: " + jogador1 + " - " + pontuacaoJ1 + " " + RED + "   " + RESET +
                "       Jogador 2: " + jogador2 + " - " + pontuacaoJ2 + " "  + BLUE + "   " + RESET);
    }

    private static void exibirTabuleiro() {
        tabuleiro2 = new String[tamanho][tamanho];

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
                if (" C ".equals(tabuleiro2[i][j])) {

                    System.out.print("C  ");
                } else {

                    String color = cardcolors[i][j];
                    System.out.print(color + tabuleiroPares[i][j] + RESET + "   ");
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