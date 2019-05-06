package com.meli.magneto.model;

import java.util.Arrays;

public class DNARequest {
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    @Override
    public String toString() {
        return "DNARequest{" +
                "dna=" + Arrays.toString(dna) +
                '}';
    }
}
