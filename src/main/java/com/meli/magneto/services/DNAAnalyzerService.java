package com.meli.magneto.services;

import org.springframework.stereotype.Service;

@Service
public class DNAAnalyzerService {

    private static Integer MUTANT_QUANTITY = 2;

    public Boolean isMutant(String[] dna) {
        String[][] matrix = toMatrix(dna);
        Integer dnaLength = dna.length;
        Integer count = 0;

        for(int i = 0; i < dnaLength; i++) {

            for (int j = 0; j < dnaLength; j++) {

                if ( (j+3 < dnaLength) && isMutantVertical(matrix, i, j) ) {
                    count++;
                    if (mutantQuantityCovered(count))
                        break;
                }

                if ( (i+3 < dnaLength) &&  isMutantHorizontal(matrix, i, j) ) {
                    count++;
                    if (mutantQuantityCovered(count))
                        break;
                }

                if ( (i+3 < dnaLength) && (j+3 < dnaLength) && isMutantDiagonal(matrix, i, j) ) {
                    count++;
                    if (mutantQuantityCovered(count))
                        break;
                }
            }

            if (mutantQuantityCovered(count))
                break;

        }

        return mutantQuantityCovered(count) ? true : false;

    }

    private String[][] toMatrix(String[] dna) {
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

    private Boolean isMutantVertical(String[][] matrix, int i, int j) {
        return (matrix[i][j].equals(matrix[i][j+1]))
                && (matrix[i][j].equals(matrix[i][j+2]))
                && (matrix[i][j].equals(matrix[i][j+3]));
    }

    private Boolean isMutantHorizontal(String[][] matrix, int i, int j) {
        return (matrix[i][j].equals(matrix[i+1][j]))
                && (matrix[i][j].equals(matrix[i+2][j]))
                && (matrix[i][j].equals(matrix[i+3][j]));
    }

    private Boolean isMutantDiagonal(String[][] matrix, int i, int j) {
        return (matrix[i][j].equals(matrix[i+1][j+1]))
                && (matrix[i][j].equals(matrix[i+2][j+2]))
                && (matrix[i][j].equals(matrix[i+3][j+3]));
    }

    private Boolean mutantQuantityCovered(Integer count) {
        return count >= MUTANT_QUANTITY;
    }

}
