package com.meli.magneto.utils;

public final class Utils {

    public static String[][] toMatrix(String[] dna) {
        Integer dnaLength = dna.length;
        String[][] matrix = new String[dnaLength][dnaLength];

        for (int i = 0; i < dnaLength; i++) {
            String[] sequence = dna[i].split("");
            for (int j = 0; j < dnaLength; j++) {
                matrix[i][j] = sequence[j];
            }
        }

        return matrix;
    }

}
