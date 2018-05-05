package com.github.aantoniadis.delivery.tests

import com.github.aantoniadis.delivery.App
import com.github.aantoniadis.delivery.rest.api.ErrorCodeDto
import com.github.aantoniadis.delivery.rest.api.ErrorDto
import com.github.aantoniadis.delivery.rest.api.ProductDto
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(App::class)], webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductTest {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun shouldReturn201_whenCreatingANewProduct() {
        val productCode = UUID.randomUUID().toString()
        val price = "1.23"
        val description = "foo"

        val response = createProduct<Unit>(productCode, price, description)

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun shouldReturn200AndTheProduct_whenRetrievingAProductThatExists() {
        val productCode = UUID.randomUUID().toString()
        val price = "2.34"
        val description = "bar"

        createProduct<Unit>(productCode, price, description)

        val response = retrieveProduct<ProductDto>(productCode)
        val responseDto = response.body!!
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(productCode, responseDto.code)
        assertEquals(description, responseDto.description)
        assertEquals(price, responseDto.price)
    }

    @Test
    fun shouldReturn404AndErrorResponse_whenRetrievingAProductThatDoesNotExist() {
        val response = retrieveProduct<ErrorDto>("anUnknownProductCode")

        val responseDto = response.body!!
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertEquals(ErrorCodeDto.NOT_FOUND, responseDto.errorCode)
        assertEquals("Resource not found", responseDto.message)
    }

    @Test
    fun shouldReturn400AndErrorResponse_whenCreatingAProductWithInvalidData() {
        val productCode = UUID.randomUUID().toString()
        val price = "-0.01"
        val description = "bar"

        val createResponse = createProduct<ErrorDto>(productCode, price, description)

        val responseDto = createResponse.body!!
        assertEquals(HttpStatus.BAD_REQUEST, createResponse.statusCode)
        assertEquals("Produce price should not be negative", responseDto.message)
    }

    private inline fun <reified T> retrieveProduct(productCode: String): ResponseEntity<T> {
        return restTemplate.getForEntity("/products/$productCode/", T::class.java)
    }

    private inline fun <reified T> createProduct(
        productCode: String,
        price: String,
        description: String
    ): ResponseEntity<T> {
        val entity = HttpEntity(ProductDto(code = productCode, price = price, description = description))
        return restTemplate.exchange("/products/", HttpMethod.POST, entity, T::class.java)
    }

}
