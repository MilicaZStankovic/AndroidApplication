package com.example.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewActivity extends AppCompatActivity {

    static private final String TAG = "ToDoList";
    private EditText editText;
    private Button addButton;
    private RadioButton radioDone;
    private static TextView dateLabel;
    private static TextView timeLabel;
    private Button dateButton;
    private Button timeButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        editText = (EditText) findViewById(R.id.editTextAddItem);
        radioDone = (RadioButton) findViewById(R.id.radioDone);
        dateLabel = (TextView) findViewById(R.id.dateLabel);
        timeLabel = (TextView) findViewById(R.id.timeLabel);
        dateButton = (Button) findViewById(R.id.buttonDate);
        timeButton = (Button) findViewById(R.id.buttonTime);
        addButton = (Button) findViewById(R.id.buttonAdd);
        cancelButton = (Button) findViewById(R.id.buttonCancel);

        dateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered dateButton.onClickListener.onClick");
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i(TAG, "Entered timButton.onClickListener.onClick");
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
    }

    public void addButtonOnClick(View v){

        Log.i(TAG, "Entered addButton.onClickListener.onClick");
        Intent data = new Intent();
        data.putExtra("item", editText.getText().toString());
        data.putExtra("status",radioDone.isChecked());
        data.putExtra("date", dateLabel.getText());
        data.putExtra("time", timeLabel.getText());
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    public void cancelButtonOnClick(View v){

        Log.i(TAG, "Entered cancelButton.onClickListener.onClick");
        finish();
    }


    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
            dateLabel.setText(df.format(c.getTime()));
        }
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timeLabel.setText(hourOfDay+":"+minute);
        }
    }
}
