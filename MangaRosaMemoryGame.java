import java.util.Scanner;

public class MangaRosaMemoryGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");
        System.out.println("Manga Rosa Memory Game");
        System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");

        int pontuacaoJ1 = 0;
        int pontuacaoJ2 = 0;
        int resposta;
        String jogador1 = "PARTICIPANTE01";
        String jogador2 = "PARTICIPANTE02";

        System.out.println("1. Iniciar\n2. Pontuação participantes\n3. Regras do Jogo\n4. Sair");
        resposta = scanner.nextInt();

        if (resposta == 1){
            System.out.println("Para começar o jogo informe o nome dos participantes");
            System.out.print("Jogador 1: ");
            jogador1 = scanner.next();
            System.out.print("Jogador 2: ");
            jogador2 = scanner.next();

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
            System.out.println("");
        }
    }
}

