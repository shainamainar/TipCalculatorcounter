package csci490.cofc.edu.tipcalculator_counter;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    //private RadioButton fifteenClicked, eighteenClicked, twentyClicked, otherClicked;
    private Button calculateButton, resetButton;
    private TextView finalTip, finalTotal, finalPerPerson;
    private double total;
    private double tipVal;
    private double tipPer;
    private double allTotal;
    private double totalPerPerson;
    private int numPeople;
    private boolean totSub = false, peopSub = false, otherSub = false;
    final DecimalFormat dollar = new DecimalFormat("#.00");

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("total", total);
        savedInstanceState.putDouble("tipPer", tipPer);
        savedInstanceState.putDouble("tipVal", tipVal);
        savedInstanceState.putDouble("allTotal", allTotal);
        savedInstanceState.putDouble("totalPerPerson", totalPerPerson);
        savedInstanceState.putInt("numPeople", numPeople);
        savedInstanceState.putString("inputTotal", String.valueOf(inputTotal));
        savedInstanceState.putString("inputPeople", String.valueOf(inputPeople));
        savedInstanceState.putString("inputOther", String.valueOf(inputOther));
        savedInstanceState.putInt("tipGroup", tipGroup.getCheckedRadioButtonId());
        savedInstanceState.putString("finalTip", String.valueOf(finalTip.getText()));
        savedInstanceState.putString("finalTotal", String.valueOf(finalTotal.getText()));
        savedInstanceState.putString("finalPerPerson", String.valueOf(finalPerPerson.getText()));
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState!=null){
            super.onRestoreInstanceState(savedInstanceState);
            numPeople = savedInstanceState.getInt("numPeople");
            total = savedInstanceState.getDouble("total");
            tipPer = savedInstanceState.getDouble("tipPer");
            tipVal = savedInstanceState.getDouble("tipVal");
            allTotal = savedInstanceState.getDouble("allTotal");
            totalPerPerson = savedInstanceState.getDouble("totalPerPerson");
            inputTotal.setText(savedInstanceState.getString("inputTotal"));
            inputPeople.setText(savedInstanceState.getString("inputPeople"));
            inputOther.setText(savedInstanceState.getString("inputOther"));
            tipGroup.check(savedInstanceState.getInt("tipGroup"));
            finalTip.setText(savedInstanceState.getString("finalTip"));
            finalTotal.setText(savedInstanceState.getString("finalTotal"));
            finalPerPerson.setText(savedInstanceState.getString("finalPerPerson"));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTotal = this.findViewById(R.id.input_total);
        inputPeople = this.findViewById(R.id.input_people);
        inputOther = this.findViewById(R.id.input_other);
        tipGroup = this.findViewById(R.id.tipGroup);
        calculateButton = this.findViewById(R.id.calculateButton);
        resetButton = this.findViewById(R.id.resetButton);
        finalTip = this.findViewById(R.id.finalTip);
        finalTotal = this.findViewById(R.id.finalTotal);
        finalPerPerson = this.findViewById(R.id.finalPerPerson);

        inputTotal.setOnKeyListener(keyListener);
        inputPeople.setOnKeyListener(keyListener);
        inputOther.setOnKeyListener(keyListener);

        calculateButton.setOnClickListener(clickListener);
        resetButton.setOnClickListener(clickListener);

    }
    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.calculateButton:
                        if (!totSub || !peopSub) {
                            Toast.makeText(getApplicationContext(), R.string.invalid_input, Toast.LENGTH_SHORT);
                        } else if (inputOther.getVisibility() == View.VISIBLE && !otherSub) {
                            Toast.makeText(getApplicationContext(), R.string.other_invalid, Toast.LENGTH_SHORT);
                        } else {
                            if (tipPer < 0.01) {
                                showErrorAlert(String.valueOf(R.string.invalid_tip), 1);
                            } else if (total < 1) {
                                showErrorAlert(String.valueOf(R.string.invalid_total), 2);
                            } else if (numPeople < 1) {
                                showErrorAlert(String.valueOf(R.string.invalid_people), 3);
                            } else {
                                tipVal = tipPer * total;
                                allTotal = total + tipVal;
                                totalPerPerson = allTotal / numPeople;
                                finalTip.setText(String.valueOf(dollar.format(tipVal)));
                                finalPerPerson.setText(String.valueOf(dollar.format(totalPerPerson)));
                                finalTotal.setText(String.valueOf(dollar.format(allTotal)));

                            }
                        }
                        break;
                    case R.id.resetButton:
                        total = 0;
                        tipPer = 0;
                        numPeople = 0;
                        tipVal = 0;
                        tipGroup.clearCheck();
                        finalTotal.setText(null);
                        finalTip.setText(null);
                        finalPerPerson.setText(null);
                        inputOther.setText(null);
                        inputPeople.setText(null);
                        inputTotal.setText(null);
                        break;

                }
            }

        };
    private View.OnKeyListener keyListener;{
        keyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (view.getId()) {
                    case R.id.input_total:
                        if(inputTotal.getText().toString().length()>0){
                            totSub = true;
                            total = Double.parseDouble(inputTotal.getText().toString());
                        }
                        if(totSub && peopSub && otherSub){
                            calculateButton.setEnabled(true);
                        }
                        break;
                    case R.id.input_people:
                        if(inputPeople.getText().toString().length()>0){
                            peopSub = true;
                            numPeople = Integer.parseInt(inputPeople.getText().toString());
                        }
                        if(totSub && peopSub && otherSub){
                            calculateButton.setEnabled(true);
                        }
                        break;
                    case R.id.input_other:
                        if(inputOther.getText().toString().length()>0){
                            otherSub = true;
                            tipPer = Double.parseDouble(inputOther.getText().toString());
                        }
                        if(totSub && peopSub && otherSub){
                            calculateButton.setEnabled(true);
                        }
                        break;
                }
                return false;
            }
        };
    }

    public void onRadioButtonClicked(View view){
        boolean isClicked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.fifteenClicked:
                if(isClicked){
                    tipPer = 0.15;
                    inputOther.setVisibility(View.INVISIBLE);
                    otherSub = false;
                }
                break;
            case R.id.eighteenClicked:
                if(isClicked){
                    tipPer = 0.18;
                    inputOther.setVisibility(View.INVISIBLE);
                    otherSub = false;

                }
                break;
            case R.id.twentyClicked:
                if(isClicked){
                    tipPer = 0.20;
                    inputOther.setVisibility(View.INVISIBLE);
                    otherSub = false;
                }
                break;
            case R.id.otherClicked:
                if (isClicked){
                    otherSub = false;
                    inputOther.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}

