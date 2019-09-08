package com.iot;

import com.iot.model.User;
import com.iot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext
public class DatabaseIntegrationTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUserObject_whenSave_thenCreateNewUser() {
        User user = new User();
        user.setEmail("admin@gmail.com  ");
        user.setActivated(true);
        user.setName("Mehrdad");
        user.setId(1L);
        user.setPassword("password");
        userRepository.save(user);
        assertThat(userRepository.findAll().size()).isGreaterThan(0);
    }


    @Override
    protected void extendSetup() {

    }
}
