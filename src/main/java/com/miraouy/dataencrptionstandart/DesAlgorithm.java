package com.miraouy.dataencrptionstandart;
import org.springframework.stereotype.Component;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

@Component
public class DesAlgorithm {
    private final  String key = "101010101011101100001001000110000010011100110110011001100111101";

    public DesAlgorithm(){
    }
    private final int[] initial_permutation = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    private final int[] expansion_table = {
            32, 1, 2, 3, 4, 5, 4, 5,
            6, 7, 8, 9, 8, 9, 10, 11,
            12, 13, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1
    };
    private final int[][][] substition_boxes=
    {{
      {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
      {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
      {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
      {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    },
    {
        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
    },
    {
        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
    },
    {
        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
    },
    {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
    },
    {
        {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
        {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
        {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
        {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
    },
     {
         {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
         {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
         {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
         {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
     },
     {
         {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
         {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
         {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
         {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
     }
     };

    private final int[] permutation_tab = {
                16,7,20,21,29,12,28,17,
                1,15,23,26,5,18,31,10,
                2,8,24,14,32,27,3,9,
                19,13,30,6,22,11,4,25
    };
    private final int[] inverse_permutation= {
               40,8,48,16,56,24,64,32,
                39,7,47,15,55,23,63,31,
                38,6,46,14,54,22,62,30,
                37,5,45,13,53,21,61,29,
                36,4,44,12,52,20,60,28,
                35,3,43,11,51,19,59,27,
                34,2,42,10,50,18,58,26,
                33,1,41,9,49,17,57,25
    };
    private final int[] pc1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };
    // Tableau PC2
    private final int[] pc2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };
    // Fonction pour effectuer une rotation circulaire vers la gauche de 1
    static String retationLeftOnce(String keyChunk) {
        return keyChunk.substring(1) + keyChunk.charAt(0);
    }
    // Fonction pour effectuer une rotation circulaire vers la gauche de 2
    static String retationLeftTwice(String keyChunk) {
        return retationLeftOnce(retationLeftOnce(keyChunk));
    }
    public List<String> generateKeys(String key) {
        String[] round_keyss=new String[16];
        List<String> round_keys=new ArrayList<>();

        String round="";
        //  Compression de la clé en utilisant le tableau PC1
        String permKey = "";
        for (int i = 0; i < 56; i++) {
            permKey += key.charAt(this.pc1[i] - 1);
        }
        //  Division du résultat en deux moitiés égales
        String left = permKey.substring(0, 28);
        String right = permKey.substring(28);

        //  16 clés
        for (int i = 0; i < 16; i++) {
            //  les tours 1, 2, 9, 16, les blocs de clé sont déplacés d'un cran.
            if (i == 0 || i == 1 || i == 8 || i == 15) {
                left = retationLeftOnce(left);
                right = retationLeftOnce(right);
            } else {
                // les autres tours, les blocs de clé sont déplacés de deux crans.
                left = retationLeftTwice(left);
                right = retationLeftTwice(right);
            }
            // Les blocs sont combinés
            String combinedKey = left + right;
            String roundKey = "";

            //  le tableau PC2 est utilisé pour transposer les bits de clé
            for (int j = 0; j < 48; j++) {
                roundKey += combinedKey.charAt(this.pc2[j] - 1);
            }
            round_keyss[i] = roundKey;
             System.out.println("Clé " + (i + 1) + ": " + round_keyss[i]);
             round=round_keyss[i];
            System.out.println("round " + (i + 1) + ": " + round);
            round_keys.add(round);

        }
        System.out.println("list **************************");
        round_keys.forEach(System.out::println);
        return round_keys;

    }
    //  convertir un nombre décimal en binaire
    String convertDecimalToBinary(int decimal) {
        String binary = "";
        while (decimal != 0) {
            binary = (decimal % 2 == 0 ? "0" : "1") + binary;
            decimal = decimal / 2;
        }
        while (binary.length() < 4) {
            binary = "0" + binary;
        }
        return binary;
    }

    // Fonction pour convertir un nombre binaire en décimal
    int convertBinaryToDecimal(String binary) {
        int decimal = 0;
        int counter = 0;
        int size = binary.length();
        for (int i = size - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, counter);
            }
            counter++;
        }
        return decimal;
    }



    //  XOR entre deux chaînes de caractères
    String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        int size = b.length();
        for (int i = 0; i < size; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }
    public static String stringToBinary(String text) {
        StringBuilder binaryString = new StringBuilder();
        for (char character : text.toCharArray()) {
            String binaryChar = Integer.toBinaryString(character);
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binaryString.append(binaryChar);
        }
        return binaryString.toString();
    }
    public static String binaryToString(String binary) {
        StringBuilder result = new StringBuilder();
        int length = binary.length();

        for (int i = 0; i < length; i += 8) {
            String chunk = binary.substring(i, Math.min(length, i + 8));
            int decimalValue = Integer.parseInt(chunk, 2);
            result.append((char) decimalValue);
        }

        return result.toString();
    }

    public String des(String pt) {

        List<String> round_keys=this.generateKeys(key);

      /*  System.out.println("_________________________________"+round_keys.size());
        for (int i = 0; i < round_keys.size(); i++) {
            System.out.println("cle "+i+": "+round_keys.get(i));
        }*/

        //  Application de la permutation initiale
        String perm = "";
        String mot_biniaire = stringToBinary(pt);
        System.out.println("**********_______********mot en binaire :" +mot_biniaire.length());
        pt= "1010101111001101111001101010101111001101000100110010010100110110";
        for (int i = 0; i < 64; i++) {
            perm += mot_biniaire.charAt(initial_permutation[i] - 1);
        }
        System.out.println("*******perùitaion initial : ");
        System.out.println(perm);
        //  division du résultat en deux moitiés égales
        String left = perm.substring(0, 32);
        String right = perm.substring(32, 64);
        System.out.println("*******left : /n");
        System.out.println(left);
        System.out.println("*******right : /n");
        System.out.println(right);

        // chiffré Le texte en clair  16 fois
        for (int i = 0; i < 16; i++) {
            // expansion de la moitié droite du texte en clair
            String right_expanded = "";
            for (int j = 0; j < 48; j++) {
                right_expanded += right.charAt(expansion_table[j] - 1);
            }
            // Le résultat est XORé avec une clé
            String xored = xor(round_keys.get(i), right_expanded);
            String res = "";

            /**
             *  Le résultat est divisé en 8 parties égales et passé
             * à travers 8 boîtes de substitution. Après être passé par une
             *  boîte de substitution, chaque boîte est réduite de 6 à 4 bits.
             */
            for (int j = 0; j < 8; j++) {
                // Recherche des indices de ligne et de colonne pour accéder à la
                // boîte de substitution
                String row1 = xored.substring(j * 6, j * 6 + 1) + xored.substring(j * 6 + 5, j * 6 + 6);
                int row = convertBinaryToDecimal(row1);
                String col1 = xored.substring(j * 6 + 1, j * 6 + 3) + xored.substring(j * 6 + 4, j * 6 + 6);
                int col = convertBinaryToDecimal(col1);
                int val = substition_boxes[j][row][col];
                res += convertDecimalToBinary(val);
            }

            // Une autre permutation est appliquée
            String perm2 = "";
            for (int j = 0; j < 32; j++) {
                perm2 += res.charAt(permutation_tab[j] - 1);
            }

            // Le résultat est XORé avec la moitié gauche
            xored = xor(perm2, left);

            //Les moitiés gauche et droite du texte en clair sont échangées
            left = xored;

            if (i < 15) {
                String temp = right;
                right = xored;
                left = temp;
            }
        }

        // Les moitiés du texte en clair sont appliquées
        String combined_text = left + right;
        String ciphertext = "";
        // L'inverse de la permutation initiale est appliqué
        for (int i = 0; i < 64; i++) {
            ciphertext += combined_text.charAt(inverse_permutation[i] - 1);
        }

        // Et nous obtenons finalement le texte chiffré
        String ciphertextfinal=binaryToString(ciphertext);
        return ciphertextfinal;
    }


}
