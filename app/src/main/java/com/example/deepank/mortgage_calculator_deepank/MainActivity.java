package com.example.deepank.mortgage_calculator_deepank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnBegin;
    EditText txtUserName;
    EditText dash;
    static TextView seektext;
    SeekBar seek;
    RadioGroup radioG;
    RadioButton radioB;
    CheckBox c1;
    Double T;
Double MM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        seekbbar();
        T = 0.0;
    }

    public void calcClick(View calc) {
        //Edit text
        txtUserName = (EditText) findViewById(R.id.editText);
        if (!(txtUserName.getText().toString().equals(""))) {

            String userName = txtUserName.getText().toString();

            Float amt = Float.parseFloat(userName);
            //Toast.makeText(getApplicationContext(),amt + " entered",Toast.LENGTH_LONG).show();

            //Seek Bar
            seek = (SeekBar) findViewById(R.id.seekBar);
            Double seekProgress = Double.valueOf(seek.getProgress());
//        String x = Integer.toString(seekProgress);
            Double seekFinal = seekProgress / 10;
            ((TextView) findViewById(R.id.seekBarValue)).setText(seekFinal.toString());
            //Toast.makeText(getApplicationContext(),seekProgress + " entered",Toast.LENGTH_LONG).show();

//        //Radio Button
            radioG = (RadioGroup) findViewById(R.id.radioGroup);
            int radioID = radioG.getCheckedRadioButtonId();
            radioB = (RadioButton) findViewById(radioID);
            String s = radioB.getText().toString();
            String[] input = s.split(" ");
            Float f = Float.parseFloat(input[0]);
            f = f *12;
            //Float yrs = .parseInt(radioB.getText().toString());
            //Toast.makeText(getApplicationContext(), Integer.valueOf(radioB.getText().toString()),Toast.LENGTH_SHORT).show();
            Log.v("this is ", "" + f);

//f
//        //Check box
            c1 = (CheckBox) findViewById(R.id.checkBox);
            StringBuffer result = new StringBuffer();
            result.append("Taxes and Insurance apply : ").append(c1.isChecked());


//        Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
//
            //Monthly payment
            double monthInterest = (seekProgress / 1200);
            Log.v("amount ", amt.toString());
            Log.v("month interest ", "" + monthInterest);
            Log.v("f ", f.toString());
            Log.v("T ", T.toString());
            if (seekFinal == 0) {
                double M = (amt / f) + T;
                Toast.makeText(MainActivity.this, M + " dollars", Toast.LENGTH_SHORT).show();
                MM=M;
                dash=(EditText) findViewById(R.id.textView6);
                dash.setText(MM.toString());
            } else {
               double  M = (amt * (monthInterest / (1 - (Math.pow((1 + monthInterest), -f))))) + T;
                Toast.makeText(MainActivity.this, M + " dollars", Toast.LENGTH_SHORT).show();
                MM=M;
                dash=(EditText) findViewById(R.id.textView6);
                dash.setText(MM.toString());

            }

        } else {
            txtUserName.setError("Give the Mortgage amount");
        }
//

//        Monthly payment display


    }



    public void onCheckBoxClick(View v) {
        c1 = (CheckBox) findViewById(R.id.checkBox);
        EditText principalText = (EditText) findViewById(R.id.editText);
        if (!(principalText.getText().toString().equals(""))) {
            if (c1.isChecked()) {
                double principal = Double.parseDouble(principalText.getText().toString());
                Log.v("principal = ", "" + principal);
                T = principal * (0.1/100);
            }
            else {
                T = 0.0;
             }
        }
    }

    public void seekbbar() {
        SeekBar seek = (SeekBar) findViewById(R.id.seekBar);

        final EditText seekValue = (EditText) findViewById(R.id.seekBarValue);


        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    double progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress / 10.0;
                        seekValue.setText("" + progress_value);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
//                        seektext.setText(progress_value);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
