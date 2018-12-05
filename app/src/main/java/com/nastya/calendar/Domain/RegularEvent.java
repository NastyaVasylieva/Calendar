package com.nastya.calendar.Domain;

import java.util.Objects;

public class RegularEvent extends Event {
    private static final String quote = "//~//";

    private long eventFrequency;
    private long eventDuration;

    public RegularEvent() {
        super();
        this.eventFrequency = eventFrequency;
        this.eventDuration = eventDuration;

    }
    public RegularEvent(String eventName,
                        String eventInfo, long eventStart,
                        long eventFinish,
                        String eventColor, String eventIcon,
                        boolean isEnabledNotifications, long eventFrequency,
                        long eventDuration
    ) {

        super(eventName, eventInfo, eventStart, eventFinish, eventColor, eventIcon, isEnabledNotifications);
        this.eventFrequency = eventFrequency;
        this.eventDuration = eventDuration;

    }

    public long getEventFrequency() {
        return eventFrequency;
    }

    public long getEventDuration() {
        return eventDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RegularEvent that = (RegularEvent) o;
        return eventFrequency == that.eventFrequency &&
                eventDuration == that.eventDuration;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), eventFrequency, eventDuration);
    }
}