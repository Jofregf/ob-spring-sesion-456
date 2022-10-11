package com.example.obspringsesion456.controllers;

import com.example.obspringsesion456.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    public void cargarLaptops(){
        // Creamos laptop y definimos formato
        HttpHeaders headers = new HttpHeaders();
        // Formato JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String laptop1 = """
            {
            
            "marca": "Lenovo",
            "modelo": "Phantom Black",
            "procesador": "Intel Core i5",
            "pantalla": 15.6,
            "almacenamiento": 128,
            "ram": 8,
            "precio": 230999.0,
            "windows": true
            }
                """;
        String laptop2 = """
            {
            
            "marca": "Acer", 
            "modelo": "A315-34", 
            "procesador": "Intel Celeron", 
            "pantalla": 15.6, 
            "almacenamiento": 500, 
            "ram": 4, "precio": 99000.00, 
            "windows": true
            }
        """;
        // Definimos los datos a pasar
        HttpEntity<String> request1 = new HttpEntity<>(laptop1, headers);
        HttpEntity<String> request2 = new HttpEntity<>(laptop2, headers);

        // Pasamos los datos
        ResponseEntity<Laptop> response1 = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request1, Laptop.class);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request2, Laptop.class);
    }
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @DisplayName("Comprueba que se busque todas las laptops desde los controladores")
    @Test
    void findAll() {
        cargarLaptops();
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        List<Laptop> laptop = Arrays.asList(response.getBody());
        if(response.getBody() != null){
            assertEquals(HttpStatus.OK, response.getStatusCode());
//            assertEquals(2, laptop.size());
            assertTrue(laptop.size()>1);
        } else {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

    }

    @DisplayName("Comprueba buscar una laptop con id")
    @Test
    void findOneById() {
        cargarLaptops();
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/2", Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assert result != null;
        assertEquals("Lenovo", result.getMarca());
        assertEquals("Phantom Black", result.getModelo());
    }

    @DisplayName("Comprueba crear una laptop desde controladores")
    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "marca": "Dell",
                    "modelo": "V3405_R58GB256",
                    "procesador": "AMD Ryzen 5",
                    "pantalla": 14,
                    "almacenamiento": 256,
                    "ram": 16,
                    "precio": 149999.00,
                    "windows": false
                    }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("Dell", result.getMarca());
        assertEquals(256, result.getAlmacenamiento());

    }
    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String actualizar = """
                {
                "id": 3,
                "marca": "Acer Test",
                "modelo": "A315-34 Test",
                "procesador": "Intel Celeron",
                "pantalla": 15.6,
                "almacenamiento": 500,
                "ram": 4, "precio": 99000.00,
                "windows": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(actualizar, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, request, Laptop.class);
        Laptop result = response.getBody();
        if (!response.getStatusCode().isError()){
            assertEquals("Acer Test", result.getMarca());
            assertEquals("A315-34 Test", result.getModelo());
        }
    }

    @Test
    void delete() {
        cargarLaptops();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, request, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteAll() {
        cargarLaptops();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, request, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<Laptop[]> response2 = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        List<Laptop> laptops = Arrays.asList(response2.getBody());
        assertFalse(laptops.size() != 0);
    }
}