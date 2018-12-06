package com.nastya.calendar.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.applandeo.materialcalendarview.EventDay;
import com.nastya.calendar.DBHelper.EventDBHelper;
import com.nastya.calendar.Domain.Event;
import com.nastya.calendar.R;

import petrov.kristiyan.colorpicker.ColorPicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button colorButton;
    EditText name;
    EditText info;
    Switch notification;
    EditText startDateText;
    EditText endDateText;
    Switch allDay;
    Spinner frequency;

    Button save;
    Button cancel;

    long date_millis;
    Calendar start;
    Calendar end;
    long eventFrequency;

    String mode;

    private List<EventDay> mEventDays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        toolbar = findViewById(R.id.toolbar);
        colorButton = findViewById(R.id.colorButton);
        startDateText = findViewById(R.id.date_add);
        endDateText = findViewById(R.id.date_end_add);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        name = findViewById(R.id.title_add);
        info = findViewById(R.id.info_add);
        notification = findViewById(R.id.notification_switch);
        allDay = findViewById(R.id.isAllDay);
        frequency = findViewById(R.id.selectFrequency);

        start = Calendar.getInstance();
        end = Calendar.getInstance();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date_millis = Long.parseLong(extras.getString("event_date"));

            start.setTimeInMillis(date_millis);
            end.setTimeInMillis(date_millis);

            /*startDateText.setText(calendar.get(calendar.DAY_OF_MONTH)+"/"+(calendar.get(calendar.MONTH)+1)+
                    "/"+calendar.get(calendar.YEAR)+" "+calendar.getTime());
            endDateText.setText(calendar.get(calendar.DAY_OF_MONTH)+"/"+(calendar.get(calendar.MONTH)+1)+
                    "/"+calendar.get(calendar.YEAR)+" "+calendar.getTime());
                    */
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            startDateText.setText(dateFormatter.format(start.getTime()));
            endDateText.setText(dateFormatter.format(end.getTime()));
        }

        final String[] eventColor = new String[1];
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ColorPicker colorPicker = new ColorPicker(AddActivity.this);

                ArrayList<String> colors = new ArrayList<>();
                colors.add("#82B926");
                colors.add("#a276eb");
                colors.add("#6a3ab2");
                colors.add("#666666");
                colors.add("#FFFF00");
                colors.add("#3C8D2F");
                colors.add("#FA9F00");
                colors.add("#FF0000");

                colorPicker
                        .setDefaultColorButton(Color.parseColor("#f84c44"))
                        .setColors(colors)
                        .setColumns(5)
                        .setRoundColorButton(true)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                eventColor[0] = Integer.toString(color);
                                colorButton.setBackgroundColor(color);
                            }
                            @Override
                            public void onCancel() {
                                colorButton.setBackgroundColor(Color.parseColor("#aae4e4e4"));
                            }
                        })
                        .show();
            }
        });
        allDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    end.add(Calendar.HOUR_OF_DAY, 23);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    startDateText.setText(dateFormatter.format(start.getTime()));
                    endDateText.setText(dateFormatter.format(end.getTime()));
                }else {
                    end.set(Calendar.HOUR_OF_DAY, 0);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    startDateText.setText(dateFormatter.format(start.getTime()));
                    endDateText.setText(dateFormatter.format(end.getTime()));
                }
            }
        });

        frequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        eventFrequency = 0;
                        break;
                    case 1:
                        eventFrequency = TimeUnit.DAYS.toMillis(1);
                        break;
                    case 2:
                        eventFrequency = TimeUnit.DAYS.toMillis(7);
                        break;
                    case 3:
                        eventFrequency = TimeUnit.DAYS.toMillis(30);
                        break;
                    case 4:
                        eventFrequency = TimeUnit.DAYS.toMillis(365);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                eventFrequency = 0;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDBHelper dbHelper = new EventDBHelper(AddActivity.this, date_millis);

                String eventName = name.getText().toString();
                String eventInfo = info.getText().toString();
                boolean eventNotification = notification.isChecked();
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    start.setTime(dateFormatter.parse(startDateText.getText().toString()));
                    end.setTime(dateFormatter.parse(endDateText.getText().toString()));
                } catch (ParseException e) {
                }

                if(extras!=null){
                    mode=extras.getString("mode");
                }
                if(eventFrequency==0){
                    if(mode.equals("add")){
                        Event event = new Event(eventName, eventInfo, start.getTimeInMillis(), end.getTimeInMillis(), eventColor[0],"d", eventNotification);
                        event.setEventId(dbHelper.insertEvent(event));
                    }
                    if(mode.equals("edit")){
                        long id = (extras.getLong("id"));
                        Event event = new Event(id, eventName, eventInfo, start.getTimeInMillis(), end.getTimeInMillis(), eventColor[0],"d", eventNotification);
                        dbHelper.updateEvent(event);
                    }
                }


                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
