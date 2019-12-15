package com.example.project_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mResultField;
    private EditText mNumberField;
    private TextView mOperationField;
    private Double mOperationNumber = null;
    private String mLastOperations = "=";
    private double mCalculatorMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultField = findViewById(R.id.resultField);
        mNumberField = findViewById(R.id.numberField);
        mOperationField = findViewById(R.id.operationField);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", mLastOperations);
        if(mOperationNumber != null)
            outState.putDouble("OPERATION_NUMBER", mOperationNumber);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLastOperations = savedInstanceState.getString("OPERATION");
        mOperationNumber = savedInstanceState.getDouble("OPERATION_NUMBER");
        mResultField.setText(mOperationNumber.toString());
        if (mLastOperations != "=") {
            mOperationField.setText(mLastOperations);
        }
    }

    public void onNumberClick(View view){
        Button button = (Button)view;
        mNumberField.append(button.getText());

        if(mLastOperations.equals("=") && mOperationNumber != null){
            mOperationNumber = null;
        }
    }

    public void onOperationClick(View view){

        Button button = (Button)view;
        String operation = button.getText().toString();
        String number = mNumberField.getText().toString();
        if(number.length()>0){
            number = number.replace(',', '.');
            performOperation(Double.valueOf(number), operation);
        }
        mLastOperations = operation;
        if(operation != "=") {
            mOperationField.setText(mLastOperations);
        }
    }

    public void onMemoryClick(View view){
        Button button = (Button)view;
        String memoryOperation = button.getText().toString();

        String number = mResultField.getText().toString();
        double number_double;
        if (mResultField.getText().toString() != "") {
            number = number.replace(',', '.');
            number_double = Double.valueOf(number);
        }
        else number_double = 0;

        switch (memoryOperation) {
            case "MC":
                mCalculatorMemory = 0;
                mResultField.setText("");
                mNumberField.setText("");
                mOperationField.setText("");
                break;
            case "M+":
                if (!number.equals("")) {
                    mCalculatorMemory = mCalculatorMemory + number_double;
                }
                break;
            case "M-":
                if (!number.equals("")) {
                    mCalculatorMemory = mCalculatorMemory - number_double;
                }
                break;
            case "MR":
                if(mCalculatorMemory != 0) {
                    mResultField.setText(Double.toString(mCalculatorMemory));
                }
                break;
        }
    }

    private void performOperation(Double number, String operation){
        if(mOperationNumber == null){
            mOperationNumber = number;
        }
        else{
            if(mLastOperations.equals("=")){
                mLastOperations = operation;
            }
            switch(mLastOperations){
                case "=":
                    mOperationNumber = number;
                    break;

                case "+":
                    mOperationNumber += number;
                    break;

                case "-":
                    mOperationNumber -= number;
                    break;

                case "/":
                    if(number == 0){
                        mOperationNumber = 0.0;
                    }
                    else{
                        mOperationNumber /= number;
                    }
                    break;

                case "*":
                    mOperationNumber *= number;
                    break;

            }
        }
        mResultField.setText(mOperationNumber.toString().replace('.', ','));
        mNumberField.setText("");
    }
}
