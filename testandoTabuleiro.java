import java.util.Random;

public class testandoTabuleiro {
    public static void main(String[] args) {

        int tamanho = 2;

        String resultado = testandoTabuleiro(tamanho);
        System.out.println("String gerada: " + resultado);
    }

    public static String testandoTabuleiro(int tamanho) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();


        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        return sb.toString();
    }
}