package ua.tqs.airquality.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.airquality.model.City;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirQualityRestController.class)
class AirQualityControllerIT {

    @Autowired
    private MockMvc mvc;


    @Test
    void whenGetIndexPage_thenReturnTemplate() throws Exception {

        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("city", new City()))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    @Test
    public void whenPostCityName_thenReturnData() throws Exception {

        //TODO: Erro ao fazer post
        //Request:        name:	"Viseu"

        City testeCity = new City();
        testeCity.setName("\"Viseu\"");

        /*mvc.perform(get("/air-quality")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("city", testeCity))
                .andExpect(status().isOk())
                .andExpect(view().name("results"));

         */

        mvc.perform(post("/air-quality").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("name=Viseu"))
                .andExpect(status().isOk());

    }




}
