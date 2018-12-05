package com.nastya.calendar.DBHelper;

import com.nastya.calendar.Domain.RegularEvent;

import java.util.List;

public interface IRegularEventDBHelper {

    /**
     *@return if of created regular event
    */
     long insertEvent(RegularEvent regularEvent);

    /**
     *@return regular event with current id
    */
     RegularEvent getEvent(long id);

    /**
     *@return list of existing regular events
    */
     List<RegularEvent> readAllEvents();

    /**
     *@return ??
    */
     int updateEvent(RegularEvent regularEvent);

    /*
     *deleting current regular event
    */
     void deleteEvent(RegularEvent regularEvent);

}
