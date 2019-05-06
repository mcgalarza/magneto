package com.meli.magneto.controllers

import com.meli.magneto.model.Stats
import com.meli.magneto.services.StatsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class StatsControllerTest extends Specification {
    def "GetStats"() {
        given:
        def statsService = Mock(StatsService) {
            getStats() >> new Stats(40, 100)
        }
        def statsController = new StatsController(statsService)

        when:
        def response = statsController.getStats()

        then:
        assert response.statusCode == HttpStatus.OK
    }
}
