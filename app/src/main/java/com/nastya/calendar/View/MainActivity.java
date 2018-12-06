package com.nastya.calendar.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nastya.calendar.DBHelper.EventDBHelper;
import com.nastya.calendar.Domain.Event;
import com.nastya.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CalendarView calendarView;
    Resources resources;
    FloatingActionButton addButton;
    Calendar clickedDayCalendar;
    ListView listView;
    TextView textView;

    ArrayList<Event> eventArrayList;
    List<EventDay> eventDayList;
    EventsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        calendarView = findViewById(R.id.calendarView);
        addButton = findViewById(R.id.floatingActionButton);
        textView = findViewById(R.id.default_message);
        listView = findViewById(R.id.list_view);

        textView.setText(R.string.default_event_message);

       /* EventDBHelper dbHelper = new EventDBHelper(MainActivity.this, calendarView.getCurrentPageDate().getTimeInMillis());
        List<Event> monthEvents = new ArrayList<>();
        monthEvents=dbHelper.readAllEvents();
        eventDayList.addAll(monthEvents);
        calendarView.setEvents(eventDayList);*/


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_month: break;
                    case R.id.menu_week: break;
                    case R.id.menu_day: break;
                }
                return false;
            }
        });
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                textView.setText(R.string.default_event_message);

                clickedDayCalendar = eventDay.getCalendar();
                eventArrayList = new ArrayList<Event>();

                EventDBHelper dbHelper = new EventDBHelper(MainActivity.this, clickedDayCalendar.getTimeInMillis());

                eventArrayList = dbHelper.readByDay(clickedDayCalendar.getTimeInMillis());
                adapter = new EventsAdapter(MainActivity.this, eventArrayList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((adapterView, view, i, l) -> {
                    InfoDialog infoDialog = new InfoDialog();
                    Bundle data = new Bundle();
                    data.putString("name", ((Event) adapterView.getItemAtPosition(i)).getEventName());
                    data.putString("info", ((Event) adapterView.getItemAtPosition(i)).getEventInfo());
                    data.putLong("start", ((Event) adapterView.getItemAtPosition(i)).getEventStart());
                    data.putLong("end", ((Event) adapterView.getItemAtPosition(i)).getEventFinish());
                    data.putBoolean("notification",  ((Event) adapterView.getItemAtPosition(i)).isEnabledNotifications());
                    data.putLong("id", ((Event) adapterView.getItemAtPosition(i)).getEventId());

                    infoDialog.setArguments(data);
                    infoDialog.show(getSupportFragmentManager(), "Info");
                });

                if(eventArrayList.size()>0){
                    textView.setText("");
                }

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickedDayCalendar!=null){
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    intent.putExtra("event_date", clickedDayCalendar.getTimeInMillis()+"");
                    intent.putExtra("mode", "add");

                    startActivity(intent);
                }


            }
        });

        MainActivity.forceLocale(this);
        resources = MainActivity.getLocalizedResources(this);
    }

    public static void forceLocale(Context context) {
        String localeCode = getLocale(context);
        String localeCodeLowerCase = localeCode.toLowerCase();

        Resources resources = context.getApplicationContext().getResources();
        Configuration overrideConfiguration = resources.getConfiguration();
        Locale overrideLocale = new Locale(localeCodeLowerCase);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            overrideConfiguration.setLocale(overrideLocale);
        } else {
            overrideConfiguration.locale = overrideLocale;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.getApplicationContext().createConfigurationContext(overrideConfiguration);
        } else {
            resources.updateConfiguration(overrideConfiguration, null);
        }
    }

    public static String getLocale(Context c) {
        return "";
        //return PreferenceManager.getDefaultSharedPreferences(c).getString(MainActivity.LOCALE_KEY, LOCALE_ENGLISH);
    }

    @NonNull
    public static Resources getLocalizedResources(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration conf = context.getResources().getConfiguration();
            conf = new Configuration(conf);
            //conf.setLocale(new Locale(getLocale(context)));
            Context localizedContext = context.createConfigurationContext(conf);
            return localizedContext.getResources();
        } else return context.getResources();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}

