import java.util.Scanner;

public class MangaRosaMemoryGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");
        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");

        int pontuacaoJ1 = 0;
        int pontuacaoJ2 = 0;
        int resposta = 3;
        String jogador1 = "PARTICIPANTE01";
        String jogador2 = "PARTICIPANTE02";
        String tamTabuleiro;

        while (resposta <= 3){
            System.out.println("1. Iniciar\n2. Pontuação participantes\n3. Regras do Jogo\n4. Sair");
            resposta = scanner.nextInt();
            if (resposta == 1){
                System.out.println("Para começar o jogo informe o nome dos participantes");
                System.out.print("Jogador 1: ");
                jogador1 = scanner.next();
                System.out.print("Jogador 2: ");
                jogador2 = scanner.next();

                System.out.println("Informe o tamanho do tabuleiro que voce deseja:\n A: 4x4\n B:" +
                        " " +
                        "8x8\n C: 16x16");
                tamTabuleiro = scanner.next();

                if (tamTabuleiro == "A"){
                    System.out.println("4x4");
                }
                if (tamTabuleiro == "B"){
                    System.out.println("8x8");
                }
                if (tamTabuleiro == "C"){
                    System.out.println("16x16");
                }
            }
            if (resposta == 2){
                if (pontuacaoJ1 == 0 && pontuacaoJ2 == 0){
                    System.out.println("Ainda não há pontuação");
                }
                else {
                    System.out.println("O jogador" + jogador1 + "tem " + pontuacaoJ1 + "pontos " +
                            "O jogador" + jogador2 +
                            " tem " + pontuacaoJ2 + "pontos ");
                }
            }
            if (resposta == 3){
                System.out.println("\u001b[1m Aqui vai as regras do jogo");
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
}