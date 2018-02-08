package csci490.cofc.edu.tipcalculator_counter;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //instantiations
    private EditText inputTotal, inputPeople, inputOther;
    private RadioGroup tipGroup;
    private RadioButton fifteenClicked, eighteenClicked, twentyClicked, otherCliked;
    private Button calculateButton, resetButton;
    private TextView finalTip, finalTotal, finalPerPerson;
    private double total, tipVal;
    private int numPeople;
    private boolean totSub = false, peopSub = false, otherSub = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTotal = findViewById(R.id.input_total);
        inputPeople = findViewById(R.id.input_people);
        inputOther = findViewById(R.id.input_other);
        tipGroup = findViewById(R.id.tipGroup);
        calculateButton = findViewById(R.id.calculateButton);
        resetButton = findViewById(R.id.resetButton);
        finalTip = findViewById(R.id.finalTip);
        finalTotal = findViewById(R.id.finalTotal);
        finalPerPerson = findViewById(R.id.finalPerPerson);

        inputTotal.setOnKeyListener(keyListener);
        inputPeople.setOnKeyListener(keyListener);
        inputOther.setOnKeyListener(keyListener);

        calculateButton.setOnClickListener(clickListener);
        resetButton.setOnClickListener(clickListener);

    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.calculateButton:
                    if (!totSub || !peopSub){
                        Toast.makeText(getApplicationContext(), R.string.invalid_input, Toast.LENGTH_SHORT );
                    }
                    else if(inputOther.getVisibility() == View.VISIBLE && !otherSub){
                        Toast.makeText(getApplicationContext(), R.string.other_invalid, Toast.LENGTH_SHORT);
                    }
                    else{
                        if(tipVal <= 0 || total < 1 || numPeople < 1){

                        }
                    }
            }
        }
    }
}
