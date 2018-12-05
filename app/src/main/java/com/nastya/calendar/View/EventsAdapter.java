package com.nastya.calendar.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nastya.calendar.Domain.Event;
import com.nastya.calendar.R;

import java.util.ArrayList;

public class EventsAdapter extends ArrayAdapter<Event> {
    public EventsAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.title_adapter);
        TextView info = (TextView) convertView.findViewById(R.id.info_adapter);

        // Populate the data into the template view using the data object
        title.setText(event.getEventName());
        info.setText(event.getEventInfo());
        // Return the completed view to render on screen
        return convertView;
    }


}
