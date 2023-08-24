package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sqliteproject.databinding.ActivityShowBinding;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ArrayList<Groceries>list;
    ActivityShowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyDBHelper helper = new MyDBHelper(this);

        list = helper.getList();

        binding.title.setText(list.get(0).getTitle());
        binding.descTitle.setText(list.get(0).getDescTitle());
        binding.desc.setText(list.get(0).getDescription());
        binding.validFrom.setText(list.get(0).getValidFrom());
        binding.validTill.setText(list.get(0).getValidTill());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyDBHelper helper = new MyDBHelper(this);
        ArrayList<Groceries> list = helper.getList();
        if(!list.isEmpty()){
            helper.deleteGroceries(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyDBHelper helper = new MyDBHelper(this);
        ArrayList<Groceries> list = helper.getList();
        if(!list.isEmpty()){
            helper.deleteGroceries(1);
        }
    }
}