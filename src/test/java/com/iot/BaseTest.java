package com.iot;

import com.iot.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureMockMvc
public abstract class BaseTest {

    protected MockMvc mvc;
    protected static User testUser1;

    @Autowired
    protected WebApplicationContext wac;
    private static HttpHeaders headersUser1;

    protected int port = 8080;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @BeforeClass
    public static void initializeHeaders() {
        testUser1 = new User();
        testUser1.setEmail("admin@gmail.com");
        testUser1.setPassword("mehrdad");
        headersUser1 = new HttpHeaders();
        /*headersUser1.set("Authorization", tokenAuthenticationService.getExternalTokenWithPrefix(testUser1.getId().toString()
                , System.currentTimeMillis() + 3600000L));*/
    }

    @Before
    public void setUp() {
        extendSetup();
    }

    protected abstract void extendSetup();

    protected void initializeMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).defaultRequest(get(createURLWithPort("/"))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).headers(headersUser1))
                .build();
    }

}
