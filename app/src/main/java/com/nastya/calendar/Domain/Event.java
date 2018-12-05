package com.nastya.calendar.Domain;

import java.util.Calendar;
import java.util.Objects;

public class Event {

    private static final String quote = "//~//";



    public Event() {
    }

    public Event(String eventName,
                 String eventInfo, long eventStart,
                 long eventFinish,
                 String eventColor, String eventIcon,
                 boolean isEnabledNotifications) {

        this.eventName = eventName;
        this.eventInfo = eventInfo;
        this.eventStart = eventStart;
        this.eventFinish = eventFinish;
        this.eventColor = eventColor;
        this.eventIcon = eventIcon;
        this.isEnabledNotifications = isEnabledNotifications;
    }

    private long eventId;
    private String eventName;
    private String eventInfo;
    private long eventStart; //milliseconds
    private long eventFinish; //milliseconds
    private String eventColor;
    private String eventIcon;
    private boolean isEnabledNotifications;

    public long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName.replaceAll(quote,"'");
    }

    public String getEventInfo() {
        return eventInfo.replaceAll(quote,"'");
    }
    public long getEventStart() {
        return eventStart;
    }

    public String getEventColor() {
        return eventColor;
    }

    public String getEventIcon() {
        return eventIcon;
    }

    public boolean isEnabledNotifications() {
        return isEnabledNotifications;
    }

    public long getEventFinish() {
        return eventFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId &&
                eventStart == event.eventStart &&
                eventFinish == event.eventFinish &&
                isEnabledNotifications == event.isEnabledNotifications &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(eventInfo, event.eventInfo) &&
                Objects.equals(eventColor, event.eventColor) &&
                Objects.equals(eventIcon, event.eventIcon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventInfo, eventStart, eventFinish, eventColor, eventIcon, isEnabledNotifications);
    }
}
