package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteproject.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyDBHelper helper = new MyDBHelper(this);

        binding.validFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(binding.validFrom);
            }
        });
        binding.validTill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(binding.validTill);
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.etTitle != null || binding.etDescTitle!= null || binding.etDescription != null ||
                        binding.validFrom != null || binding.validTill!= null){
                    String title = binding.etTitle.getText().toString();
                    String descTitle = binding.etDescTitle.getText().toString();
                    String description = binding.etDescription.getText().toString();
                    String valid_from = binding.validFrom.getText().toString();
                    String valid_till = binding.validTill.getText().toString();

                    Groceries groceries = new Groceries(title,descTitle,description,valid_from,valid_till);
                    helper.addGroceries(groceries);

                    Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void showDatePickerDialog(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity .this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String formattedDate = dateFormat.format(selectedDate.getTime());
                        editText.setText(formattedDate);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
}