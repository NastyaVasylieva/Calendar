package com.nastya.calendar.DBHelper;

import com.nastya.calendar.Domain.Event;

import java.util.ArrayList;
import java.util.List;

public interface IEventDBHelper {

    /*
    /@return if of created event
    */
    public long insertEvent(Event event);

    /*
    /@return event with current id
    */
    public Event getEvent(long id);

    /*
    /@return list of existing events
    */
    public List<Event> readAllEvents();

    /*
    /@return ??
    */
    public int updateEvent(Event event);

    /*
    / deleting current event
    */
    public void deleteEvent(long id);

}
