package com.sherlockhomes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FlujoApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String login(String username, String password) throws Exception {
        String cuerpo = mockMvc.perform(post("/api/auth/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(cuerpo).get("token").asText();
    }

    @Test
    void sinTokenSeResponde401() throws Exception {
        mockMvc.perform(get("/api/admin/socios/listar"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginConClaveIncorrectaResponde401() throws Exception {
        mockMvc.perform(post("/api/auth/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"incorrecta\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminRegistraSocioYElSocioSoloAccedeASusEndpoints() throws Exception {
        String tokenAdmin = login("admin", "Admin1234");

        mockMvc.perform(post("/api/admin/socios/registrar")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Ana Perez\",\"dni\":30111222,\"direccion\":\"Calle 1\","
                                + "\"telefono\":\"1155550000\",\"username\":\"ana\",\"password\":\"clave123\"}"))
                .andExpect(status().isCreated());

        // el mismo DNI dos veces es un conflicto
        mockMvc.perform(post("/api/admin/socios/registrar")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Otra\",\"dni\":30111222,\"username\":\"otra\",\"password\":\"clave123\"}"))
                .andExpect(status().isConflict());

        String tokenSocio = login("ana", "clave123");

        mockMvc.perform(get("/api/socio/listarVehiculos")
                        .header("Authorization", "Bearer " + tokenSocio))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/admin/socios/listar")
                        .header("Authorization", "Bearer " + tokenSocio))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/admin/socios/listar")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }
}
