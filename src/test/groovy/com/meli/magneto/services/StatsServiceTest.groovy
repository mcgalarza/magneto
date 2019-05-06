package com.meli.magneto.services

import com.meli.magneto.model.DNA
import com.meli.magneto.repository.DNARepository
import spock.lang.Specification

class StatsServiceTest extends Specification {

    def "GetStats"() {
        given:
        def dnaRepository = Mock(DNARepository) {
            countByMutant(true) >> 4
            countByMutant(false) >> 10
        }

        def statsService = new StatsService(dnaRepository)

        when:
        def stats = statsService.getStats()

        then:
        assert stats.getHumanCount() == 10
        assert stats.getMutantCount() == 4
        assert stats.getRatio() == 0.4f

    }
}
