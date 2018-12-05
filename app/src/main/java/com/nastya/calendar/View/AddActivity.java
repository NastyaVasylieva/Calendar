package com.nastya.calendar.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.nastya.calendar.DBHelper.EventDBHelper;
import com.nastya.calendar.Domain.Event;
import com.nastya.calendar.R;
import petrov.kristiyan.colorpicker.ColorPicker;

import java.util.ArrayList;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button colorButton;
    EditText name;
    EditText info;
    Switch notification;
    EditText dateText;
    Button save;

    long date_millis;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        toolbar = findViewById(R.id.toolbar);
        colorButton = findViewById(R.id.colorButton);
        dateText = findViewById(R.id.date_add);
        save = findViewById(R.id.save);
        name = findViewById(R.id.title_add);
        info = findViewById(R.id.info_add);
        notification = findViewById(R.id.notification_switch);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date_millis = Long.parseLong(extras.getString("event_date"));
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date_millis);
            dateText.setText(calendar.get(calendar.DAY_OF_MONTH)+"/"+(calendar.get(calendar.MONTH)+1)+"/"+calendar.get(calendar.YEAR));
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


       save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //adding to database
                EventDBHelper dbHelper = new EventDBHelper(AddActivity.this, date_millis);

                String eventName = name.getText().toString();
                String eventInfo = info.getText().toString();
                boolean eventNotification = notification.isChecked();

                Event event = new Event(eventName, eventInfo, date_millis, date_millis, eventColor[0],"d", eventNotification);

                dbHelper.insertEvent(event);

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
