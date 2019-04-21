package com.meli.magneto.model;

public class DNA {
    private String[] dnaSequence;
    private Boolean isMutant;

    public String[] getDnaSequence() {
        return dnaSequence;
    }

    public void setDnaSequence(String[] dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public Boolean getMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }
}
