package com.nastya.calendar.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.nastya.calendar.DBHelper.EventDBHelper;
import com.nastya.calendar.Domain.Event;
import com.nastya.calendar.R;

import java.util.Calendar;

public class InfoDialog extends DialogFragment {

    private TextView name;
    private TextView info;
    private TextView start;
    private TextView end;
    private TextView repeat;
    private TextView isNotification;

    private ImageView delete;
    private ImageView edit;
    private ImageView cancel;

    private long id;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View eventView = inflater.inflate(R.layout.event_info_dialog, null);
        builder.setView(eventView);

        name = eventView.findViewById(R.id.name_view);
        info = eventView.findViewById(R.id.info_view);
        start = eventView.findViewById(R.id.start_view);
        end = eventView.findViewById(R.id.end_view);
        repeat = eventView.findViewById(R.id.repeat_view);
        isNotification = eventView.findViewById(R.id.notification_view);

        delete = eventView.findViewById(R.id.deleteButton);
        edit = eventView.findViewById(R.id.editButton);
        cancel = eventView.findViewById(R.id.cancelButton);


        Calendar startTime = Calendar.getInstance();
        startTime.setTimeInMillis(getArguments().getLong("start"));

        Calendar endTime = Calendar.getInstance();
        endTime.setTimeInMillis(getArguments().getLong("end"));

        name.setText(getArguments().getString("name"));
        info.setText(getArguments().getString("info"));
        start.setText(startTime.get(startTime.DAY_OF_MONTH)+"/"+(startTime.get(startTime.MONTH)+1)+"/"+startTime.get(startTime.YEAR));
        end.setText(endTime.get(endTime.DAY_OF_MONTH)+"/"+(endTime.get(endTime.MONTH)+1)+"/"+endTime.get(endTime.YEAR));
        if(getArguments().getBoolean("notification")==true){
            isNotification.setText("on");
        }else {
            isNotification.setText("off");
        }

        id=getArguments().getLong("id");
        repeat.setText(id+"");

        System.out.print(id);

        delete.setOnClickListener(view -> {
            EventDBHelper dbHelper = new EventDBHelper(InfoDialog.super.getContext(), startTime.getTimeInMillis());
            dbHelper.deleteEvent(id);
            getDialog().dismiss();
            Intent intent = new Intent(InfoDialog.super.getContext(), MainActivity.class);
            startActivity(intent);


        });

        cancel.setOnClickListener(view ->{
            getDialog().dismiss();
        });

        edit.setOnClickListener(view ->{
            Intent intent = new Intent(InfoDialog.super.getContext(), AddActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("mode", "edit");
            intent.putExtra("event_date", startTime.getTimeInMillis()+"");

            startActivity(intent);
        });

        return builder.create();
    }
}

