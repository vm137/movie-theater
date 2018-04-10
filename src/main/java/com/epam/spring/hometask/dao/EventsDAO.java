package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

public class EventsDAO implements EventService {

    private ArrayList<Event> eventList = new ArrayList<>();

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        for (Event event : eventList) {
            if (event.getName().equals(name))
                return event;
        }

        return null;
    }

    @Override
    public Event save(@Nonnull Event object) {
        eventList.add(object);
        return object;
    }

    @Override
    public void remove(@Nonnull Event object) {

    }

    @Override
    public Event getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventList;
    }
}
