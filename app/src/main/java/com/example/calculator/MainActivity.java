package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private String operation;
    private double firstNumber;
    private boolean isResult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.textResult);
    }

    public void setNumber(View view) {
        String currentNumber = editText.getText().toString();
        Button button = findViewById(view.getId());

        if (isResult){
            return;
        }
        else{
            try {
                currentNumber = Calculator.setNumber(currentNumber, button.getText().toString());
            }
            catch (IllegalArgumentException e) {
                return;
            }
        }

        editText.setText(currentNumber);
    }

    public void getOperation(View view) {
        String number = editText.getText().toString();
        if (number.endsWith(".")) {
            return;
        }
        firstNumber = Double.parseDouble(number);
        Button button = findViewById(view.getId());
        operation = button.getText().toString();
        editText.setText("0");
        isResult = false;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void calculate(View view){
        if (isResult)
            return;
        String currentNumber = editText.getText().toString();
        if (currentNumber.endsWith(".")) {
            return;
        }
        double number = Double.parseDouble(currentNumber);
        try {
            if (operation == null){
                return;
            }
            double result = Calculator.calculate(firstNumber, number, operation);
            editText.setText(removeZero(String.format("%.5f",result)));
            isResult = true;
        }
        catch(Exception e){
            editText.setText("Error");
        }
    }

    public void clear(View view){
        editText.setText("0");
        isResult = false;
    }

    public void changeSign(View view) {
        String currentNumber = editText.getText().toString();
        try {
            editText.setText(Calculator.changeSign(currentNumber));
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void removeLastChar(View view) {
        String currentText = editText.getText().toString();
        if (currentText.length() != 1)
            editText.setText(currentText.substring(0, currentText.length() - 1));
        else
            editText.setText("0");
            isResult = false;
    }

    @SuppressLint("SetTextI18n")
    public void setDot(View view) {
        if (isResult)
            return;

        String currentText = editText.getText().toString();
        if (!currentText.contains(".")){
            editText.setText(currentText + ".");
        }
    }

    private String removeZero(String s){
        return !s.contains(".") ? s : s.replaceAll("0*$","").replaceAll("\\.$","");
    }
}