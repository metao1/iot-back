package com.iot;

import com.iot.model.User;
import com.iot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext
@Transactional
public class DatabaseIntegrationTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUserObject_whenSave_thenCreateNewUser() {
        User user = new User();
        user.setEmail("mehrdad@gmail.com");
        user.setPassword("usuLTO8nwnrxbf0IUcEQT7SY1AGQZmC3QTpT4TL50A7859Z0bO9Q3G106tr3");
        user.setActivated(true);
        userRepository.save(user);
        assertThat(userRepository.findAll().size()).isGreaterThan(0);
    }


    @Override
    protected void extendSetup() {

    }
}
