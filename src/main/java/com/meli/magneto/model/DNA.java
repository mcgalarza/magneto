package com.meli.magneto.model;

import com.meli.magneto.strategies.SequenceFinder;
import com.meli.magneto.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "dna")
public class DNA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    transient String[] dnaSequenceAsArray;
    private String dnaSequence;
    private Boolean mutant;

    public DNA(String[] dnaSequenceAsArray) {
        this.dnaSequenceAsArray = dnaSequenceAsArray;
        this.dnaSequence = Arrays.toString(dnaSequenceAsArray);
    }

    public Boolean determineAnomaly(List<SequenceFinder> sequenceFinders) {
        String[][] dnaAsMatrix = Utils.toMatrix(dnaSequenceAsArray);
        int dnaLength = dnaSequenceAsArray.length;
        int count = 0;

        for (int x = 0; x < dnaLength; x++) {
            for (int y = 0; y < dnaLength; y++) {
                for (SequenceFinder finder : sequenceFinders) {
                    if (finder.findPatternInSequence(dnaAsMatrix, x, y)) {
                        count++;
                        if (quantityCovered(count))
                                break;
                    }
                }
            }
            if (quantityCovered(count))
                break;
        }

        this.mutant = quantityCovered(count) ? true : false;

        return mutant;
    }

    private Boolean quantityCovered(int count) {
        return count >= 2;
    }

    public Boolean isMutant() {
        return mutant;
    }

}