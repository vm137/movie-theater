package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestEventDao {

    @Autowired
    private EventsDAO eventsDAO;

    @Test
    public void testAddEvent() {

        eventsDAO.save(new Event());

        assertTrue(!eventsDAO.getAll().isEmpty());

    }

}
