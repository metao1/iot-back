package com.gro;

import com.gro.security.model.User;
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
        user.setEnabled(true);
        user.setFirstname("Mehrdad");
        user.setId(1);
        user.setPassword("password");
        userRepository.save(user);
        assertThat(userRepository.findAll().size()).isGreaterThan(0);
    }


    @Override
    protected void extendSetup() {

    }
}
