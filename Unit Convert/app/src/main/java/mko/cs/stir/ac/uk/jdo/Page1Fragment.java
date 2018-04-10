package mko.cs.stir.ac.uk.jdo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by jdo on 05/03/2018.
 */

public class Page1Fragment extends Fragment {

    // Spinner element
    Spinner spinner, spinner2, spinner3;


    private Bundle savedState = null;

    DBUnits helper;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page1_fragment, container, false);


        // Spinner element
            spinner = (Spinner) view.findViewById(R.id.UnitIDSpinner);
            spinner2 = (Spinner) view.findViewById(R.id.ConvertFromSpinner);
            spinner3 = (Spinner) view.findViewById(R.id.ConvertToSpinner);


            loadSpinnerData(savedInstanceState);

        return view;
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData(Bundle state) {
        // database handler
        helper = new DBUnits(this.getContext());

        // Spinner Drop down elements
        ArrayList<String> UnitID = helper.getUnitIDs();
        String[] Units1 = helper.getData(UnitID.get(0).toString());
        String[] Units2 = helper.getData(UnitID.get(0).toString());


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, UnitID);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Units1);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Units2);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter2);
        spinner3.setAdapter(dataAdapter3);

        //If there is a saved state this changes to selection of each spinner to what it was in the last state
        if(state != null) {

            final Bundle savedState = state;


            spinner.post(new Runnable() {
                public void run() {
                    spinner.setSelection(savedState.getInt("spinnerID"));
                }
            });
            spinner2.post(new Runnable() {
                public void run() {
                    spinner2.setSelection(savedState.getInt("spinner1"));
                }
            });
            spinner3.post(new Runnable() {
                public void run() {
                    spinner3.setSelection(savedState.getInt("spinner2"));
                }
            });

        }
        //Used when a user selects a new category to repopulate the spinner with the new values
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = spinner.getSelectedItem().toString();
                changeSpinnerData(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void changeSpinnerData(String selectedItem) {
        // database handler
        helper = new DBUnits(this.getContext());

        // Spinner Drop down elements
        String[] Units1 = helper.getData(selectedItem);
        String[] Units2 = helper.getData(selectedItem);


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Units1);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Units2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
        spinner3.setAdapter(dataAdapter3);
    }


    //Saves the instance
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        Spinner spinner = (Spinner) view.findViewById(R.id.UnitIDSpinner);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.ConvertFromSpinner);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.ConvertToSpinner);

        outState.putInt("spinnerID", spinner.getSelectedItemPosition());
        outState.putInt("spinner1", spinner1.getSelectedItemPosition());
        outState.putInt("spinner2", spinner2.getSelectedItemPosition());

    }


}