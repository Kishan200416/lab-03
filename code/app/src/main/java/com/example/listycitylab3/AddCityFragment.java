package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private EditText editCity;
    private EditText editProvince;
    private OnFragmentInteractionListener listener;

    // We pass the city being edited so we can update it in MainActivity
    public interface OnFragmentInteractionListener {
        void onOkPressed(City newCity, City cityToEdit);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    // This is the factory method from your instructions
    public static AddCityFragment newInstance(City city) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        editCity = view.findViewById(R.id.edit_text_city_name);
        editProvince = view.findViewById(R.id.edit_text_province_name);

        final City cityToEdit = getArguments() != null ? (City) getArguments().getSerializable("city") : null;

        // If we are editing, pre-fill the EditText fields
        if (cityToEdit != null) {
            editCity.setText(cityToEdit.getCityName());
            editProvince.setText(cityToEdit.getProvinceName());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    String cityName = editCity.getText().toString();
                    String provinceName = editProvince.getText().toString();

                    if (!cityName.isEmpty() && !provinceName.isEmpty()) {
                        // Pass back the new city and the original city (or null if adding)
                        listener.onOkPressed(new City(cityName, provinceName), cityToEdit);
                    }
                })
                .create();
    }
}