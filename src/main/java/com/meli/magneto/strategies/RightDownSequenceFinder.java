package com.meli.magneto.strategies;

public class RightDownSequenceFinder implements SequenceFinder {

    private int numberOfRepetitions;

    public RightDownSequenceFinder(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    @Override
    public Boolean findPatternInSequence(String[][] dnaMatrix, int x, int y) {
        if ((x + numberOfRepetitions > dnaMatrix.length)
                || (y + numberOfRepetitions > dnaMatrix.length))
            return false;
        for (int i =  1; i < numberOfRepetitions; i++) {
            if (!dnaMatrix[x+i][y+i].equals(dnaMatrix[x][y]))
                return false;
        }
        return true;
    }

}