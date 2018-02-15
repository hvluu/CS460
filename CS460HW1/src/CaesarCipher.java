public class CaesarCipher {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String plainText, int shiftKey) {
        shiftKey = shiftKey % 26 + 26;
        StringBuilder encrypted = new StringBuilder();
        for (char i : plainText.toCharArray()){
            if (Character.isLetter(i)){
                if (Character.isUpperCase(i)){
                    encrypted.append((char) ('A' + (i - 'A' + shiftKey) % 26));    
                } else{
                    encrypted.append((char) ('a' + (i - 'a' + shiftKey) % 26));
                }
            } else{
                encrypted.append(i);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String cipherText, int shiftKey) {
        return encrypt(cipherText, 26 - shiftKey);
    }
}
