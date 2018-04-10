package mko.cs.stir.ac.uk.jdo;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


   Spinner spinner, spinner1, spinner2;
   String string, string1, string2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //helper = new DBUnits(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page2));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                //Grabs the text from the spinners to display the users selection on the next tab
                //Also used for accessing the database so the correct conversion number is used
                spinner = (Spinner) findViewById(R.id.UnitIDSpinner);
                spinner1 = (Spinner) findViewById(R.id.ConvertFromSpinner);
                spinner2 = (Spinner) findViewById(R.id.ConvertToSpinner);

                string = spinner.getSelectedItem().toString();

                string1 = spinner1.getSelectedItem().toString();
                string2 = spinner2.getSelectedItem().toString();

                TextView unitIDTitle = (TextView) findViewById(R.id.unitIDTitle);

                EditText convertFrom = (EditText) findViewById(R.id.convertFrom);
                EditText convertTo = (EditText) findViewById(R.id.convertTo);

                unitIDTitle.setText(string);
                convertFrom.setText(string1);
                convertTo.setText(string2);

                EditText convertFromText = (EditText) findViewById(R.id.convertFromText);
                EditText convertToText = (EditText) findViewById(R.id.convertToText);

                convertFromText.setText("");
                convertToText.setText("");



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }@Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

}


