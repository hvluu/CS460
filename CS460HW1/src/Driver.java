import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int key;
        boolean condition = true;
        while (condition = true) {
            System.out.println("Welcome to the encryption program");
            System.out.print("Please input your message: ");
            String message = input.nextLine();
            System.out.print("Please enter shift amount for Caesar Cipher:");
            key = input.nextInt();
            input.nextLine();
            System.out.print("Please enter keyword for Vigenere Cipher & "
                    + "Fairplay Cipher:");
            String keyword = input.nextLine();

            String encryptedMessage = CaesarCipher.encrypt(message, key);
            System.out.println("\nEncrypting using Caesar Cipher: "
                    + encryptedMessage);
            System.out.println("Decrypting using Caesar Cipher: "
                    + CaesarCipher.decrypt(encryptedMessage, key));

            encryptedMessage = VigenereCipher.cipher(message, keyword, true);
            System.out.println("\nEncrypting using Vigenere Cipher: "
                    + encryptedMessage);
            System.out.println("Decrypting using Vigenere Cipher: "
                    + VigenereCipher.cipher(encryptedMessage, keyword, false));
            

            

            FairplayCipher pf = new FairplayCipher();
            String matrix = pf.generateMatrix(keyword.replace(" ", ""));
            String[] pairs = pf.divide2Pairs(message.replace("j", "i"));
            encryptedMessage = pf.encrypt(pairs, matrix);
            System.out.println("\nEncrypting using Fairplay Cipher: "
                    + encryptedMessage);
            System.out.println("Decrypting using Fairplay Cipher: "
                    + pf.decrypt(pf.divide2Pairs(encryptedMessage), matrix));

            System.out.println("\nDo you want to try another message? "
                    + "Enter 'yes' or 'no'");
            String choice = input.next();

            if ("YES".equals(choice) || "yes".equals(choice)) {
                condition = true;
                //message = "";
                input.nextLine();
            } else {
                condition = false;
                break;
            }
        }
    }
}
