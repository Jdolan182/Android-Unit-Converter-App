package mko.cs.stir.ac.uk.jdo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by jdo on 05/03/2018.
 */

public class Page2Fragment extends Fragment {


    EditText convertFromText, convertToText;

    EditText convertFromT, convertToT;

    TextView unitID;

    View view;

    DBConversions helper;

    Button btndot, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.page2_fragment, container, false);


        helper = new DBConversions(this.getContext());

        //accesses these elements so the text in them can be used to complete the query
        convertFromText = (EditText) view.findViewById(R.id.convertFromText);
        convertToText = (EditText) view.findViewById(R.id.convertToText);

        convertFromT = (EditText) view.findViewById(R.id.convertFrom);
        convertToT = (EditText) view.findViewById(R.id.convertTo);

        unitID = (TextView) view.findViewById(R.id.unitIDTitle);

        btndot = (Button) view.findViewById(R.id.dotBtn);
        btn0 = (Button) view.findViewById(R.id.zeroBtn);
        btn1 = (Button) view.findViewById(R.id.oneBtn);
        btn2 = (Button) view.findViewById(R.id.twoBtn);
        btn3 = (Button) view.findViewById(R.id.threeBtn);
        btn4 = (Button) view.findViewById(R.id.fourBtn);
        btn5 = (Button) view.findViewById(R.id.fiveBtn);
        btn6 = (Button) view.findViewById(R.id.sixBtn);
        btn7 = (Button) view.findViewById(R.id.sevenBtn);
        btn8 = (Button) view.findViewById(R.id.eightBtn);
        btn9 = (Button) view.findViewById(R.id.nineBtn);

        //initializes the calculator
        setupCalculator();

        Button clearButton = (Button) view.findViewById(R.id.clearBtn);
        clearButton.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText("");
                convertToText.setText("");
            }
        });

        //sends the units to be converted from and to
        Button convertButton = (Button) view.findViewById(R.id.convertBtn);
        convertButton.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                String string = unitID.getText().toString();
                String string1 = convertFromT.getText().toString();
                String string2 = convertToT.getText().toString();
                convert(string, string1, string2);
            }
        });


        //Makes sure users inputs are saved
        if(savedInstanceState != null){
            final Bundle savedState = savedInstanceState;

            convertFromText.post(new Runnable() {
                public void run() {
                    convertFromText.setText(savedState.getString("convertFrom"));
                }
            });
            convertToText.post(new Runnable() {
                public void run() {
                    convertToText.setText(savedState.getString("convertTo"));
                }
            });

        }


        return view;
    }

    //Conversion method
    //Takes the unit being converted from and the unit to be converted to
    //Uses these value to create a query that will return the conversion number
    //Calculates the conversion using the conversion number and then displays it to the user
    private void convert(String title, String convertFrom, String convertTo){

        String conversion = helper.getConversion(convertFrom, convertTo);

        float con = Float.parseFloat(conversion);

        String fromString = convertFromText.getText().toString();
        float from = Float.parseFloat(fromString);

        float to = (from * con * 100) / 100f;

        //Use of bigdecimal here to avoid outputting the result in scientific notation
        BigDecimal to1 = BigDecimal.valueOf(to);

        convertToText.setText(String.valueOf(String.format("%.6f", to1)));
    }


    //Initializes the listeners for each button on the calculator
    private void setupCalculator() {

        btndot.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + ".");
            }
        });
        btn0.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "0");
            }
        });
        btn1.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "1");
            }
        });
        btn2.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "2");
            }
        });
        btn3.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "3");
            }
        });
        btn4.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "4");
            }
        });
        btn5.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "5");
            }
        });
        btn6.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "6");
            }
        });
        btn7.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "7");
            }
        });
        btn8.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "8");
            }
        });
        btn9.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                convertFromText.setText(convertFromText.getText() + "9");
            }
        });
    }

    //Saves instance
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        convertFromText = (EditText) view.findViewById(R.id.convertFromText);
        convertToText = (EditText) view.findViewById(R.id.convertToText);

        outState.putString("convertFrom", convertFromText.getText().toString());
        outState.putString("convertTo", convertToText.getText().toString());
    }
}