
import java.util.*;

public class MangaRosaMemoryGame {

    static Scanner scanner = new Scanner(System.in);
    private static int pontuacaoJ1 = 0, pontuacaoJ2 = 0; // inicia a pontuação como zero
    private static String jogador1 = "PARTICIPANTE01", jogador2 = "PARTICIPANTE02"; // Declarei o nome dos jogadores como padrão
    private static int jogadasJ1 = 0, jogadasJ2 = 0; // Contador de jogadas do Jogador 1 e 2 //gio
    private static int tamanho;

    private static boolean player1turn = true; // quando o jogador1 está a escolher uma carta, isso é verdade, quando não, isso é falso
    public static String tamTabuleiro;
    public static String[][] tabuleiro2; // matriz para o tabuleiro que fica escondido
    public static String[][] cardcolors; // matriz das cores
    public static String[][] tabuleiroPares; // matriz utilizada para guardar os pares de letras

    //Definição das cores
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[41m";
    public static final String BLUE = "\u001B[44m";
    public static final String YELLOW = "\u001B[43m";
    public static final String BLACK = "\u001B[40m";


    public static void main(String[] args) {
        System.out.println("═".repeat(22));
        System.out.println("Manga Rosa Memory Game");
        System.out.println("═".repeat(22));

        while (true) { // Inicia o menu de opções
            System.out.println("1. Iniciar\n2. Pontuação participantes\n3. Regras do Jogo\n4. Sair");
            int resposta = scanner.nextInt();

            if (resposta == 1) {
                iniciarJogo();
            } else if (resposta == 2) {
                mostrarPontuacao();
            } else if (resposta == 3) {
                mostrarRegras();
            } else if (resposta == 4) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                break;  // Sai do ‘loop’ e finaliza o programa
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    //Gera o tabuleiro que mostra só a letra "C"
    private static void tabuleiroOculto(){
        tabuleiro2 = new String[tamanho][tamanho];//o tamanho é usado como parametro, pois precisa considerar o tamanho do tabuleiro que foi escolhido

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
        scanner.nextLine();
        System.out.print("Jogador 2: ");
        jogador2 = scanner.next();
        scanner.nextLine();

        System.out.println("Informe o tamanho do tabuleiro:\nA: 4x4\nB: 6x6\nC: 8x8 \nD: 10x10");
        String opcao = scanner.nextLine().toUpperCase();

        switch (opcao) {
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
            String currentplayer = player1turn ? jogador1 : jogador2; // que a gente definiu aqui
            System.out.println("Vez do jogador: " + currentplayer); // nessa linha de cima, se for a vez do jogador1 (com player1turn), ele define jogador1 como currentplayer, se nao, ele define jogador 2 como currentplayer

            // pede para o usuario digitar a posicao e revela a primeira carta
            System.out.print("Digite a posição da primeira carta que deseja revelar\nLinha: ");
            int linha1 = scanner.nextInt() - 1;
            System.out.print("Coluna: ");
            int coluna1 = scanner.nextInt() - 1;

            if (linha1 >= 0 && linha1 < tamanho && coluna1 >= 0 && coluna1 < tamanho && tabuleiro2[linha1][coluna1].equals("C")) { // verifica se a carta que a pessoa escolheu ainda esta oculta e se ela existe no tabuleiro
                tabuleiro2[linha1][coluna1] = tabuleiroPares[linha1][coluna1];
                exibirTabuleiro(); // Mostra o tabuleiro após escolher a carta que vai revelar
            } else {
                System.out.println("Posição inválida! Tente novamente."); // se nao for valida, ele retorna mensagem de erro
                continue;
            }

            // pede para o usuario digitar a posicao e revela a segunda carta
            System.out.print("Digite a posição da segunda carta que deseja revelar\nLinha: ");
            int linha2 = scanner.nextInt() - 1;
            System.out.print("Coluna: ");
            int coluna2 = scanner.nextInt() - 1;

            if (linha2 >= 0 && linha2 < tamanho && coluna2 >= 0 && coluna2 < tamanho && tabuleiro2[linha2][coluna2].equals("C")) {
                tabuleiro2[linha2][coluna2] = tabuleiroPares[linha2][coluna2];
                System.out.println("Carta revelada: " + tabuleiroPares[linha2][coluna2]);
                exibirTabuleiro(); // Mostra o tabuleiro após escolher a carta que vai revelar
            } else {
                System.out.println("Posição inválida! Tente novamente."); // se nao for valida, ele retorna mensagem de erro
                tabuleiro2[linha1][coluna1] = "C "; // esconde a carta de novo se nao for valida
                continue;
            }

            boolean isPair = tabuleiroPares[linha1][coluna1].equals(tabuleiroPares[linha2][coluna2]); // aqui, ele geral um boolean pra caso a linha1 e coluna1 for igual a linha2 e coluna2 (vai ficar dando true se for)

            if (isPair) { // se for true, ele chama esse if
                String pairColor = cardcolors[linha1][coluna1]; // aqui ele atribui a cor da primeira carta para essa
                // string "pairColor"
                if (pairColor.equals(YELLOW)) { // se o par for amarelo, ele adiciona 1 ponto pra o player que ta jogando
                    if (currentplayer.equals(jogador1)) pontuacaoJ1++;
                    else pontuacaoJ2++;
                    System.out.println("Par amarelo! +1 ponto para " + currentplayer); // atraves da variavel currentplayer
                } else if (pairColor.equals(RED)) { // se o par for vermelho, ele adiciona 5 pontos para o player que
                    // ta jogando
                    if (currentplayer.equals(jogador1)) { // aqui ele verifica se o currentplayer (jogador atual) e o jogador1, e se for
                        pontuacaoJ1 += 5; // ele adiciona 5 (que seriam os pontos) na pontuacaoJ1 (pontuacao do jogador 1)
                        System.out.println("Par vermelho! +5 pontos para " + currentplayer);
                    } else { // se nao for o jogador1 (if (currentplayer.equals(jogador1))), logicamente so pode ser o jogador 2
                        pontuacaoJ2 += 1; // entao ele adiciona a pontuacao pro score do jogador2
                        System.out.println("Par vermelho! +1 ponto para " + currentplayer);
                    }
                } else if (pairColor.equals(BLUE)) {
                    if (currentplayer.equals(jogador2)) {
                        pontuacaoJ2 += 5;
                        System.out.println("Par azul! +5 pontos para " + currentplayer);
                    } else {
                        pontuacaoJ1 += 1;
                        System.out.println("Par azul! +1 ponto para " + currentplayer);
                    }
                } else if (pairColor.equals(BLACK)) {
                    if (currentplayer.equals(jogador1)) pontuacaoJ1 += 50;
                    else pontuacaoJ2 += 50;
                    System.out.println("Par preto! +50 pontos para " + currentplayer);
                }
            } else {
                String firstColor = cardcolors[linha1][coluna1]; // aqui ele coloca a cor da primeira carta selecionada (linha1 e coluna1) e atribui a firstColor, agora essa variavel tem o valor da cor que foi selecionada
                String secondColor = cardcolors[linha2][coluna2];
                if (!firstColor.equals(BLACK) && secondColor.equals(BLACK)) {
                    System.out.println("Você nao encontrou o par da carta preta :( " + currentplayer);
                    if (currentplayer.equals(jogador1)) pontuacaoJ1 = Math.max(0, pontuacaoJ1 - 50);
                    else pontuacaoJ2 = Math.max(0, pontuacaoJ2 - 50);
                    System.out.println("Fim do jogo!"); // aqui ele termina o jogo se a pessoa não encontrar o par da
                    // carta preta
                    mostrarPontuacao();
                    return;
                }
                else if ((firstColor.equals(RED) && currentplayer.equals(jogador2)) || (firstColor.equals(BLUE) && currentplayer.equals(jogador1))) { // aqui, ele faz a verificacao da primeira cor e da cor do jogador, da seguinte forma : se a primeira cor que achou for vermelho, e o jogador atual for j2, ou, se a primneira cor for azul e o jogador atual for j1
                    if (currentplayer.equals(jogador1)) pontuacaoJ1 = Math.max(0, pontuacaoJ1 - 2); // se passar na condicao de cima, ele tira 2 pontos do jogador1 ou do jogador 2, dependendo de quem esteja jogando
                    else pontuacaoJ2 = Math.max(0, pontuacaoJ2 - 2);
                    System.out.println("Errou par adversário! -2 pontos para " + currentplayer);
                }
                tabuleiro2[linha1][coluna1] = "C "; // esconde a carta da linha 1 e da linha 2 depois de passar pelas
                // condicionais
                tabuleiro2[linha2][coluna2] = "C ";
            }

            player1turn = !player1turn; // aqui, depois que um player jogar, ele transforma a variavel player1turn (vez do jogador 1(que comeca em true)) igual a diferente de player1turn (false), e se for false, e a vez do jogador 2
            exibirTabuleiro(); // depois de fazer a troca de jogadores e esconder as cartas, ele mostra o tabuleiro de novo, com tudo como C
        }

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

    // Mostra as regras do jogo
    private static void mostrarRegras() {
        System.out.println("=== Regras do Jogo ===");
        System.out.println("- Par com fundo amarelo → +1 ponto.");
        System.out.println("- Par com fundo da sua cor → +5 pontos.");
        System.out.println("- Par com fundo da cor do adversário → se errar, -2 pontos; se acertar, +1 ponto.");
        System.out.println("- A pontuação nunca pode ser negativa.");
        System.out.println("- Carta preta: Se errar o par → perde o jogo. Se acertar → ganha.");
    }

    // Classe Card: associa cada carta a uma cor e um valor, utilizando diferentes arrays para armazená-los.
    private static class Card {
        String value;
        String color;

        public Card(String value, String color) { // deixei publica pra dar pra acessar na outra classe
            this.value = value;
            this.color = color;
        }
    }

    public static void gerarTabuleiro() {
        // Gera os arrays para armazenar os pares e as cores das cartas
        tabuleiroPares = new String[tamanho][tamanho];
        cardcolors = new String[tamanho][tamanho];

        // Calcula o total de pares possíveis no tabuleiro
        int totalPairs = (tamanho * tamanho) / 2;

        // Gera um conjunto de pares únicos
        Set<String> uniquePairs = new HashSet<>();
        while (uniquePairs.size() < totalPairs) {  // primeiro, ele chama a gerarStringAleatoria depois de verificar o tamanho de pares, vendo se o tamanho de pares e menor que o numero de pares totais
            uniquePairs.add(gerarStringAleatoria());
        }

        // Converte o conjunto de pares únicos em uma lista para atribuir cores
        List<String> uniquePairsList = new ArrayList<>(uniquePairs);

        // Define a quantidade de pares para cada cor
        List<String> pairColors = getStrings(totalPairs);
        Collections.shuffle(pairColors); // Embaralha as cores

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < uniquePairsList.size(); i++) {
            String value = uniquePairsList.get(i);
            String color = pairColors.get(i);
            cards.add(new Card(value, color));
            cards.add(new Card(value, color)); // Cada par é duplicado para garantir correspondência
        }
        Collections.shuffle(cards); Embaralha as cores

        // Distribui as cartas no tabuleiro, atribuindo valores e cores às posições
        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                Card card = cards.get(index);
                tabuleiroPares[i][j] = card.value;
                cardcolors[i][j] = card.color;
                index++;
            }
        }
    }

    private static List<String> getStrings(int totalPairs) {
        int redPairs = totalPairs / 4;
        int bluePairs = totalPairs / 4;
        int blackPairs = 1;
        int yellowPairs = totalPairs - redPairs - bluePairs - blackPairs;

        List<String> pairColors = new ArrayList<>(); // cria um novo array pra definir a quantidade dos pares (que ja foram definidos ali em cima, aqui em baixo ele so adiciona a cor na caixinha criada na de cima)
        for (int i = 0; i < redPairs; i++) pairColors.add(RED);
        for (int i = 0; i < bluePairs; i++) pairColors.add(BLUE);
        for (int i = 0; i < blackPairs; i++) pairColors.add(BLACK);
        for (int i = 0; i < yellowPairs; i++) pairColors.add(YELLOW);
        return pairColors;
    }

    private static void jogo() { // exibir a pontuacao e cor do jogador antes de escolher as cartas
        System.out.println("═".repeat(22));
        System.out.println("Que comecem os jogos!");
        System.out.println("Jogador 1: " + jogador1 + " - " + pontuacaoJ1 + " " + RED + "   " + RESET +
                "       Jogador 2: " + jogador2 + " - " + pontuacaoJ2 + " "  + BLUE + "   " + RESET);
    }

    private static void exibirTabuleiro() { // exibir tabuleiro
        System.out.print("  ");
        for (int j = 0; j < tamanho; j++) {
            System.out.print(" " + (j + 1) + "  ");
        }
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro2[i][j].equals("C")) {
                    System.out.print("C   ");
                } else {
                    String color = cardcolors[i][j];
                    System.out.print(color + tabuleiro2[i][j] + RESET + "   ");
                }
            }
            System.out.println();
        }
    }



    //Gera os pares das cartas
    public static String gerarStringAleatoria() {
        String caracteresNumero = "0123456789";
        String caracteresLetra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();

        return String.valueOf(caracteresNumero.charAt(random.nextInt(caracteresNumero.length()))) +
                caracteresLetra.charAt(random.nextInt(caracteresLetra.length()));
    }

}