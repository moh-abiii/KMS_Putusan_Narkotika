package kms_java.util;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    // Membaca teks biasa
    public static String validasiString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Exception Handling untuk Angka Bulat (Int)
    public static int validasiInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int nilai = Integer.parseInt(scanner.nextLine().trim());
                if (nilai < 0) throw new IllegalArgumentException("Nilai numerik tidak boleh negatif!");
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("[Exception Error] Input wajib berupa angka bulat terformat numerik!");
            } catch (IllegalArgumentException e) {
                System.out.println("[Exception Error] " + e.getMessage());
            }
        }
    }

    // Exception Handling untuk Angka Desimal (Double)
    public static double validasiDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double nilai = Double.parseDouble(scanner.nextLine().trim());
                if (nilai <= 0) throw new IllegalArgumentException("Nilai fisik/berat harus lebih besar dari 0!");
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("[Exception Error] Input wajib angka desimal (gunakan titik '.')!");
            } catch (IllegalArgumentException e) {
                System.out.println("[Exception Error] " + e.getMessage());
            }
        }
    }
}
