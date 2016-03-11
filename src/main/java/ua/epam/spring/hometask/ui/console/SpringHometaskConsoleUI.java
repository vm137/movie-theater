package ua.epam.spring.hometask.ui.console;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.context.ApplicationContext;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.ui.console.state.MainState;

/**
 * Simple console UI application for the hometask code. UI provides different
 * action to input and output data. In order for the application to work, the
 * Spring context initialization code should be placed into
 * {@link #initContext()} method.
 * 
 * @author Yuriy_Tkach
 */
public class SpringHometaskConsoleUI {

    private ApplicationContext context;

    public static void main(String[] args) {
        SpringHometaskConsoleUI ui = new SpringHometaskConsoleUI();
        ui.initContext();
        ui.run();
    }

    private void initContext() {
        throw new IllegalStateException("Please, add Spring context initialization logic here");
    }

    private void run() {
        System.out.println("Welcome to movie theater console service");
        
        fillInitialData();

        MainState state = new MainState(context);

        state.run();

        System.out.println("Exiting.. Thank you.");
    }

    private void fillInitialData() {
        UserService userService = context.getBean(UserService.class);
        EventService eventService = context.getBean(EventService.class);
        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        BookingService bookingService = context.getBean(BookingService.class);
        
        Auditorium auditorium = auditoriumService.getAll().iterator().next();
        if (auditorium == null) {
            throw new IllegalStateException("Failed to fill initial data - no auditoriums returned from AuditoriumService");
        }
        if (auditorium.getNumberOfSeats() <= 0) {
            throw new IllegalStateException("Failed to fill initial data - no seats in the auditorium " + auditorium.getName());
        }
        
        User user = new User();
        user.setEmail("my@email.com");
        user.setFirstName("Foo");
        user.setLastName("Bar");
        
        user = userService.save(user);
        
        Event event = new Event();
        event.setName("Grand concert");
        event.setRating(EventRating.MID);
        event.setBasePrice(10);
        LocalDateTime airDate = LocalDateTime.of(2020, 6, 15, 19, 30);
        event.addAirDateTime(airDate, auditorium);
        
        event = eventService.save(event);
        
        Ticket ticket1 = new Ticket(user, event, airDate, 1);
        bookingService.bookTickets(Collections.singleton(ticket1));
        
        if (auditorium.getNumberOfSeats() > 1) {
            User userNotRegistered = new User();
            userNotRegistered.setEmail("somebody@a.b");
            userNotRegistered.setFirstName("A");
            userNotRegistered.setLastName("Somebody");
            Ticket ticket2 = new Ticket(userNotRegistered, event, airDate, 2);
            bookingService.bookTickets(Collections.singleton(ticket2));
        }
    }
}
