package com.meli.magneto.controllers

import com.meli.magneto.model.DNA
import com.meli.magneto.model.DNARequest
import com.meli.magneto.repository.DNARepository
import com.meli.magneto.services.MutantService
import com.meli.magneto.validations.DNAValidator
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

class MutantControllerTest extends Specification {
    def "IsMutant"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["ATGCGA", "CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
        }
        def dnaValidator = Mock(DNAValidator) {
            validate(_ as DNARequest) >> {}
        }
        def dnaRepository = Mock(DNARepository) {
            save(_ as DNA) >> new DNA()
        }

        def rabbitTemplate = Mock(RabbitTemplate) {
            convertAndSend(_ as String, _ as DNA) >> {}
        }

        def mutantService = new MutantService(dnaRepository, rabbitTemplate)
        def mutantController = new MutantController(mutantService, dnaValidator)

        when:
        def response = mutantController.isMutant(dnaRequest)

        then:
        assert response.statusCode == HttpStatus.OK
    }
}
