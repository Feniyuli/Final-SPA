package de.dhbw.dinnerfortwo

import com.fasterxml.jackson.databind.ObjectMapper
import de.dhbw.dinnerfortwo.api.RestaurantController
import de.dhbw.dinnerfortwo.impl.Restaurant
import de.dhbw.dinnerfortwo.impl.RestaurantRepository
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
import org.springframework.web.context.WebApplicationContext
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RestaurantControllerTest(@Autowired val repository: RestaurantRepository) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @BeforeEach()
    fun setup() {
        // nothing yet
    }

    @Test
    // with back ticks you can quote prosa text as function name
    fun `test rest end point to create a new restaurants`() {
        // prepare test
        val restaurant = newRestaurant()
        val request = post(RestaurantController.URI_RESTAURANT_BASE)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(restaurant))

        // execute function under test
        val result = mockMvc
            .perform(request)
            .andExpect(status().isCreated)
            .andReturn()

        // evaluate result
        val createdRestaurant = objectMapper.readValue(result.response.contentAsString, Restaurant::class.java)
        createdRestaurant.name `should be equal to` "Bella Italia"
        createdRestaurant.email `should be equal to` "info@bella.com"
    }

    @Test
    fun `test endpoint to read a restaurant by id`() {
        // prepare database
        val storedRestaurant = newRestaurant()
        val entity = repository.save(storedRestaurant)

        // execute function under test
        val request = get("${RestaurantController.URI_RESTAURANT_BASE}/${entity.id}")
            .contentType("application/json")
        val result = mockMvc.perform(request)
            .andExpect(status().isOk)
            .andReturn()

        // evaluate result
        val resultRestaurant = objectMapper.readValue(result.response.contentAsString, Restaurant::class.java)
        resultRestaurant.name `should be equal to` "Bella Italia"
        resultRestaurant.email `should be equal to` "info@bella.com"
    }

    @Test
    fun `test update restaurant`() {
        // prepare database
        val storedRestaurant = newRestaurant()
        val entity = repository.save(storedRestaurant)

        val update = storedRestaurant.copy(rating = 4.5)
        val request = put("${RestaurantController.URI_RESTAURANT_BASE}/${entity.id}")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(update))
        mockMvc
            .perform(request)
//            .andDo { print(it) } // just for debugging
            .andExpect(status().isOk) // validate return value
            .andReturn()

        // validate result
        val updatedRestaurant = repository.findById(entity.id).get()
        updatedRestaurant.name `should be equal to` "Bella Italia"
        updatedRestaurant.rating `should be equal to` 4.5
    }

    @Test
    fun `test delete restaurant`() {
        // prepare database
        val storedRestaurant = newRestaurant()
        val entity = repository.save(storedRestaurant)

        //execute function under test
        val request = delete("${RestaurantController.URI_RESTAURANT_BASE}/${entity.id}")
        mockMvc
            .perform(request)
            .andExpect(status().isOk) // validate response

        // validate entity has been deleted
        val result = repository.findById(entity.id)
        result.isEmpty `should be equal to` true
    }

    private fun newRestaurant(): Restaurant {
        return Restaurant(name = "Bella Italia", cuisine = "Italy", email = "info@bella.com", rating = 3.0)
    }
}