package com.meli.magneto.strategies;

public class DownSequenceFinder implements SequenceFinder {

    private int numberOfRepetitions;

    public DownSequenceFinder(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    @Override
    public Boolean findPatternInSequence(String[][] dnaMatrix, int x, int y) {
        if (y + numberOfRepetitions > dnaMatrix.length)
            return false;
        for (int i = 1; i < numberOfRepetitions; i++) {
            if (!dnaMatrix[x][y+i].equals(dnaMatrix[x][y]))
                return false;
        }
        return true;
    }

}