package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    ListView cityList;
    CustomList cityAdapter;
    ArrayList<City> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>();

        dataList.add(new City("Edmonton", "AB"));
        dataList.add(new City("Vancouver", "BC"));
        dataList.add(new City("Toronto", "ON"));
        dataList.add(new City("Hamilton", "ON"));
        dataList.add(new City("Denver", "CO"));
        dataList.add(new City("Los Angeles", "CA"));

        cityAdapter = new CustomList(this, dataList);
        cityList.setAdapter(cityAdapter);



        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(v -> {

            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City cityToEdit = dataList.get(position);
            AddCityFragment.newInstance(cityToEdit).show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    // This is the method from our interface that the fragment calls
    @Override
    public void onOkPressed(City newCity, City cityToEdit) {
        if (cityToEdit != null) {
            int index = dataList.indexOf(cityToEdit);
            if (index != -1) {
                dataList.get(index).setCityName(newCity.getCityName());
                dataList.get(index).setProvinceName(newCity.getProvinceName());
            }
        } else {
            dataList.add(newCity);
        }
        cityAdapter.notifyDataSetChanged();
    }
}