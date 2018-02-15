import java.util.LinkedHashSet;
import java.util.Set;

public class FairplayCipher {

    private static final String ALPHABET = "abcdefghiklmnopqrstuvwxyz";

    public String generateMatrix(String key) {
        String matrixstring = key + ALPHABET;
        StringBuilder matrix = new StringBuilder();
        Set set = new LinkedHashSet();

        for (char chr : matrixstring.toLowerCase().toCharArray()) {
            set.add(chr);
        }
        set.forEach((chr) -> {
            matrix.append(chr);
        });

        return matrix.toString();
    }

    public String[] divide2Pairs(String message) {
        message = formatMessage(message);
        String pairs[] = new String[message.length() / 2];
        int j = 0;

        for (int i = 0; i < message.length(); i = i + 2) {
            pairs[j] = message.substring(i, i + 2);
            j++;
        }

        return pairs;
    }

    public String formatMessage(String message) {
        message = message.toLowerCase().replace(" ", "");
        StringBuilder mes = new StringBuilder(message);

        for (int i = 0; i < message.length() - 1; i += 2) {
            if (mes.charAt(i) == mes.charAt(i + 1)) {
                mes.insert(i + 1, "x");
            }
        }
        if (mes.length() % 2 == 1) {
            mes.append("x");
        }

        return mes.toString();
    }

    public String encrypt(String[] pairs, String matrix) {
        StringBuilder ciphertex = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);
            
            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 + 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 + 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 + 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 + 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertex.append(Character.toString(chr1))
                    .append(Character.toString(chr2));
        }

        return ciphertex.toString();
    }
    
     public String decrypt(String[] pairs, String matrix) {
        StringBuilder ciphertex = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);
            
            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 - 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 - 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 - 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 - 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertex.append(Character.toString(chr1))
                    .append(Character.toString(chr2));
        }

        return ciphertex.toString();
    }
}