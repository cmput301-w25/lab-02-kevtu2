package com.example.listycity;

import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityButton;
    Button deleteCityButton;
    int selectedCityPos = -1;
    TextInputEditText cityTextInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find all visual elements in layout
        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city);
        deleteCityButton = findViewById(R.id.delete_city);
        cityTextInput = findViewById(R.id.city_input);

        String[] cities = {"Edmonton", "Vancouver", "Calgary", "London", "Shanghai"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // We're tying the dataList to the ArrayAdapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        // Attach ArrayAdapter to ListView -- ListView then displays the contents of the ArrayList
        cityList.setAdapter(cityAdapter);

        // City element on-click listener
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < dataList.size()) {
                    selectedCityPos = position;
                }
            }
        });

        // Attach logic to buttons
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = String.valueOf(cityTextInput.getText());
                dataList.add(userInput);
                cityAdapter.notifyDataSetChanged();
}
        });

        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCityPos >= 0) {
                    dataList.remove(selectedCityPos);
                    // Prevent further deletion if user hasn't selected city
                    selectedCityPos = -1;
                }
                cityAdapter.notifyDataSetChanged();
            }
        });


    }
}