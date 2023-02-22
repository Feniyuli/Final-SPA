package de.dhbw.dinnerfortwo

import com.fasterxml.jackson.databind.ObjectMapper
import de.dhbw.dinnerfortwo.api.PersonController
import de.dhbw.dinnerfortwo.impl.person.Person
import de.dhbw.dinnerfortwo.impl.person.PersonRepository
import de.dhbw.dinnerfortwo.impl.person.Type
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest(@Autowired val repository: PersonRepository) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach()
    fun setup() {
        // nothing yet
    }

    @Test
    // with back ticks you can quote prosa text as function name
    fun `test rest end point to create a new Owners`() {
        // prepare test
        val Owner = newOwner()
        val request = post(PersonController.URI_OWNER_BASE)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(Owner))

        // execute function under test
        val result = mockMvc
            .perform(request)
            .andExpect(status().isCreated)
            .andReturn()

        // evaluate result
        val createdOwner = objectMapper.readValue(result.response.contentAsString, Owner::class.java)
        createdOwner.name `should be equal to` "Bella Italia"
        createdOwner.email `should be equal to` "info@bella.com"
    }

    @Test
    fun `test endpoint to read a Owner by id`() {
        // prepare database
        val storedOwner = newOwner()
        val entity = repository.save(storedOwner)

        // execute function under test
        val request = get("${PersonController.URI_OWNER_BASE}/${entity.id}")
            .contentType("application/json")
        val result = mockMvc.perform(request)
            .andExpect(status().isOk)
            .andReturn()

        // evaluate result
        val resultPerson = objectMapper.readValue(result.response.contentAsString, Person::class.java)
        resultPerson.name `should be equal to` "Bella Italia"
        resultPerson.email `should be equal to` "info@bella.com"
    }

    @Test
    fun `test update Owner`() {
        // prepare database
        val storedOwner = newOwner()
        val entity = repository.save(storedOwner)

        val update = PersonController.PersonDTO(
            storedOwner.name,
            storedOwner.address,
            storedOwner.email,
            storedOwner.type
        )
        val request = put("${PersonController.URI_OWNER_BASE}/${entity.id}")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(update))
        mockMvc
            .perform(request)
//            .andDo { print(it) } // just for debugging
            .andExpect(status().isOk) // validate return value
            .andReturn()

        // validate result
        val updatedOwner = repository.findById(entity.id).get()
        updatedOwner.name `should be equal to` "Bella Italia"
    }

    @Test
    fun `test delete Owner`() {
        // prepare database
        val storedOwner = newOwner()
        val entity = repository.save(storedOwner)

        //execute function under test
        val request = delete("${PersonController.URI_OWNER_BASE}/${entity.id}")
        mockMvc
            .perform(request)
            .andExpect(status().isOk) // validate response

        // validate entity has been deleted
        val result = repository.findById(entity.id)
        result.isEmpty `should be equal to` true
    }

    private fun newOwner(): Person {
        return Person(
            "Bella Italia",
            "Italy",
            "info@bella.com",
            Type.Guest
        )
    }
}