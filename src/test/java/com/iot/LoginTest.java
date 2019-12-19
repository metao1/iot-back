package com.iot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.model.LoginModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author karamim
 */
@TestPropertySource(locations = "classpath:application-test.yml")

public class LoginTest extends BaseTest {

    private AccountCredential accountCredential = new AccountCredential();
    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    protected void extendSetup() {
        accountCredential.setUsername("admin");
        accountCredential.setPassword("admin");
        initializeMvc();
    }

    @Test
    public void testLoginIsOK() throws Exception {
        MockHttpServletResponse result = mvc.perform(post(createURLWithPort("/auth"))
            .content(objectMapper.writeValueAsString(accountCredential)))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        assertEquals(result.getStatus(), HttpServletResponse.SC_OK);
        final LoginModel user = objectMapper.readValue(result.getContentAsString(), LoginModel.class);
        assertTrue(result.getContentAsString().contains("token"));
        assertNotNull(user);
        assertTrue(user.token.length() > 0);
        assertTrue(user.user.getEmail().length() > 0);
        System.out.println(user.toString());

    }

}
