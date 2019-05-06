package com.meli.magneto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Stats implements Serializable {
    @JsonProperty("count_mutant_dna")
    private Long mutantCount;
    @JsonProperty("count_human_dna")
    private Long humanCount;
    @JsonProperty("ratio")
    private Float ratio;

    public Stats(Long mutantCount, Long humanCount) {
        this.mutantCount = mutantCount;
        this.humanCount = humanCount;
        this.ratio = calculateRatio();
    }

    public Long getMutantCount() {
        return mutantCount;
    }

    public Long getHumanCount() {
        return humanCount;
    }

    public Float getRatio() {
        return ratio;
    }

    private Float calculateRatio() {
        return humanCount > 0 ? mutantCount.floatValue()/humanCount.floatValue() : mutantCount.floatValue();
    }

}
