public class VigenereCipher {

    public static String cipher(String plaintext, String key, boolean encrypt) {

        final int textLength = plaintext.length();
        final int keySize = key.length();

        final int upperCase = 'Z' - 'A' + 1;
        final int lowerCase = 'z' - 'a' + 1;
        final int alphabets = upperCase + lowerCase;

        final StringBuilder encryptedText = new StringBuilder(textLength);
 
        for (int i = 0; i < textLength; i++) {
            final char plainChar = plaintext.charAt(i);

            final int plainGroupNumber;
            if (plainChar >= 'A' && plainChar <= 'Z') {
                plainGroupNumber = plainChar - 'A';
            } else if (plainChar >= 'a' && plainChar <= 'z') {
                plainGroupNumber = upperCase + plainChar - 'a';
            } else {
                encryptedText.append(plainChar);
                continue;
            }

            final char keyChar = key.charAt(i % keySize);
            final int keyGroupNumber;
            if (keyChar >= 'A' && keyChar <= 'Z') {
                keyGroupNumber = keyChar - 'A';
            } else if (keyChar >= 'a' && keyChar <= 'z') {
                keyGroupNumber = upperCase + keyChar - 'a';
            } else {
                throw new IllegalStateException("Invalid character in key");
            }

            final int cipherGroupNumber;
            if (encrypt) {
                cipherGroupNumber = (plainGroupNumber + keyGroupNumber) 
                        % alphabets;
            } else {

                final int someCipherGroupNumber = plainGroupNumber 
                        - keyGroupNumber;
                if (someCipherGroupNumber < 0) {
                    cipherGroupNumber = (someCipherGroupNumber + alphabets);
                } else {
                    cipherGroupNumber = someCipherGroupNumber;
                }
            }

            final char cipherChar;
            if (cipherGroupNumber < upperCase) {
                cipherChar = (char) ('A' + cipherGroupNumber);
            } else {
                cipherChar = (char) ('a' + cipherGroupNumber - upperCase);
            }
            encryptedText.append(cipherChar);
        }

        return encryptedText.toString();
    }
}
