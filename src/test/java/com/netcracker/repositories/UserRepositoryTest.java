package com.netcracker.repositories;

import com.netcracker.Application;
import com.netcracker.entities.City;
import com.netcracker.entities.User;
//import org.junit.Assert;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import com.netcracker.services.UsersService;

import java.util.Optional;

import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.*;

import com.netcracker.services.UsersService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

//@DirtiesContext
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestDataBaseConfig.class)
//@WebAppConfiguration

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class UserRepositoryTest {

    /*@Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private UsersService usersService;
    @Resource
    private UserRepository userRepository;
*/
    @Autowired
    Flyway flyway;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void buildDataClean(){
        flyway.clean();
        flyway.migrate();
    }


    /*@Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }*/

    @Test
    public void findUserByEmail() throws Exception {
        User user1= userRepository.findUserByEmail("oskar2016@gmail.com");
        String name = "Leonardo DiCaprio";
       // Assert.assertEquals(name,user1.getFio());


    }


















}
