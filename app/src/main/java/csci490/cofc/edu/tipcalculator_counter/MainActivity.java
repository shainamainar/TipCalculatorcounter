package csci490.cofc.edu.tipcalculator_counter;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //instantiations
    private EditText amountInput;
    private EditText numPeople;
    private EditText otherPer;
    private RadioGroup tipGroup;
    private RadioButton fifteenClicked;
    private RadioButton eighteenClicked;
    private RadioButton twentyClicked;
    private RadioButton otherCliked;
    private Button calculateButton;
    private TextView tipAmt;
    private TextView totalAmt;
    private TextView perPerson;
    private TextView finalTip;
    private TextView finalTotal;
    private TextView finalPerPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DecimalFormat dollars = new DecimalFormat("#.00");
        //initialize variables
        amountInput = findViewById(R.id.amountInput);
        numPeople = findViewById(R.id.amountInput);
        otherPer = findViewById(R.id.otherPer);
        tipGroup = findViewById(R.id.tipGroup);
        fifteenClicked = findViewById(R.id.fifteenClicked);
        eighteenClicked = findViewById(R.id.eighteenClicked);
        twentyClicked = findViewById((R.id.twentyClicked);
        otherCliked = findViewById(R.id.otherClicked);
        calculateButton = findViewById(R.id.calculateButton);
        tipAmt = findViewById(R.id.tipAmt);
        totalAmt = findViewById(R.id.totalAmt);
        perPerson = findViewById(R.id.perPerson);
        finalTip = findViewById(R.id.finalTip);
        finalTotal = findViewById(R.id.finalTotal);
        finalPerPerson = findViewById(R.id.finalPerPerson);

        fifteenClicked.setChecked(true);

    }


}
