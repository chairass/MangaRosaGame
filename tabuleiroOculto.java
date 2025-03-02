import java.util.ArrayList;

public class tabuleiroOculto {
    private static String[][] tabuleiro2;
    private static int num = 0;

    public static void main(String[] args) {
        tabuleiro2 = new String[4][4];

        ArrayList<String> tabuleiro = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tabuleiro.add("C");
            }
        }

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tabuleiro2 [i][j] = tabuleiro.get(index);
                index++;
            }
        }

        // Adicionando a numeração das colunas
        System.out.print(" "); // Espaço inicial para alinhar os números corretamente
        for (int j = 0; j < 4; j++) {
            System.out.print(" " + (j + 1) + "  ");
        }
        System.out.println();

        // Adicionando o tabuleiro com a numeração das linhas
        for (int i = 0; i < 4; i++) {
            System.out.print((i + 1) + " "); // Numeração das linhas
            for (int j = 0; j < 4; j++) {
                System.out.print(tabuleiro2[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
