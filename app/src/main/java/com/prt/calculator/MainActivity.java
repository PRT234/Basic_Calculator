package com.prt.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView PROBLEM, RESULT;
    MaterialButton b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8, b_9, b_0;
    MaterialButton b_AC, b_C, b_DOT, b_ADD, b_SUB, b_MUL, b_EQUAL, b_PERC, b_DIVIDE ;
    double result;
    double no_1;
    boolean operatorClicked;
    String Operator;
    String DataToCalculate;
    boolean equalClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PROBLEM = findViewById(R.id.PROBLEM);
        RESULT = findViewById(R.id.RESULT);

        assign_id(b_0,R.id.b_0);
        assign_id(b_1,R.id.b_1);
        assign_id(b_2,R.id.b_2);
        assign_id(b_3,R.id.b_3);
        assign_id(b_4,R.id.b_4);
        assign_id(b_5,R.id.b_5);
        assign_id(b_6,R.id.b_6);
        assign_id(b_7,R.id.b_7);
        assign_id(b_8,R.id.b_8);
        assign_id(b_9,R.id.b_9);

        assign_id(b_DOT,R.id.b_DOT);
        assign_id(b_EQUAL,R.id.b_EQUAL);
        assign_id(b_AC,R.id.b_AC);
        assign_id(b_C,R.id.b_C);

        assign_id(b_ADD,R.id.b_ADD);
        assign_id(b_SUB,R.id.b_SUB);
        assign_id(b_MUL,R.id.b_MUL);
        assign_id(b_DIVIDE,R.id.b_DIVIDE);
        b_PERC = findViewById(R.id.b_PERC);
        b_PERC.setOnClickListener(v -> OnClickPercentage());
    }

    void assign_id(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String text = button.getText().toString();
        DataToCalculate = PROBLEM.getText().toString();

        if (text.equals("AC")){
            PROBLEM.setText("");
            RESULT.setText("0");
            no_1 = 0;
            DataToCalculate = "";
            operatorClicked = false;
            Operator = "";
            return;
        }
        if (text.equals("=")){
            calculate();
            DataToCalculate = Double.toString(result);
            equalClicked = true;
            return;
        }
        if (text.equals("+")){
            if (!DataToCalculate.isEmpty()){
                if (equalClicked){
                    DataToCalculate = RESULT.getText().toString();
                    result = Double.parseDouble(DataToCalculate);
                    PROBLEM.setText(DataToCalculate);
                    equalClicked = false;
                }else {
                    result = Double.parseDouble(DataToCalculate);
                }
                Operator = "+";
                operatorClicked = true;
                DataToCalculate = DataToCalculate + text;
                PROBLEM.setText(DataToCalculate);
                return;
            }
        }
        if (text.equals("-")){
            if (!DataToCalculate.isEmpty()){
                if (equalClicked){
                    DataToCalculate = RESULT.getText().toString();
                    result = Double.parseDouble(DataToCalculate);
                    PROBLEM.setText(DataToCalculate);
                    equalClicked = false;
                }else {
                    result = Double.parseDouble(DataToCalculate);
                }
                Operator = "-";
                operatorClicked = true;
                DataToCalculate = DataToCalculate + text;
                PROBLEM.setText(DataToCalculate);
                return;
            }
        }
        if (text.equals("×")){
            if (!DataToCalculate.isEmpty()){
                if (equalClicked){
                    DataToCalculate = RESULT.getText().toString();
                    result = Double.parseDouble(DataToCalculate);
                    PROBLEM.setText(DataToCalculate);
                    equalClicked = false;
                }else {
                    result = Double.parseDouble(DataToCalculate);
                }
                Operator = "*";
                operatorClicked = true;
                DataToCalculate = DataToCalculate + text;
                PROBLEM.setText(DataToCalculate);
                return;
            }
        }
        if (text.equals("÷")){
            if (!DataToCalculate.isEmpty()){
                if (equalClicked){
                    DataToCalculate = RESULT.getText().toString();
                    result = Double.parseDouble(DataToCalculate);
                    PROBLEM.setText(DataToCalculate);
                    equalClicked = false;
                }else {
                    result = Double.parseDouble(DataToCalculate);
                }
                Operator = "/";
                operatorClicked = true;
                DataToCalculate = DataToCalculate + text;
                PROBLEM.setText(DataToCalculate);
                return;
            }
        }
        if(text.equals("C")){
            DataToCalculate = DataToCalculate.substring(0,DataToCalculate.length()-1);
        }
        else {
            if (equalClicked){
                DataToCalculate = "";
                equalClicked = false;
            }
            DataToCalculate = DataToCalculate + text;
        }
        PROBLEM.setText(DataToCalculate);
    }

    public void OnClickPercentage(){
        if (equalClicked){
            DataToCalculate = RESULT.getText().toString();
        }else {
            DataToCalculate = PROBLEM.getText().toString();
        }
        DataToCalculate = Double.toString(Double.parseDouble(DataToCalculate) / 100);
        RESULT.setText(DataToCalculate);

    }
    public void calculate(){
        if(!Operator.isEmpty() && !DataToCalculate.isEmpty()){
            no_1 = Double.parseDouble(subString());
            switch (Operator){
                case "+":
                    result = result + no_1;
                    break;
                case "-":
                    result = result - no_1;
                    break;
                case "*":
                    result = result * no_1;
                    break;
                case "/":
                    if(no_1 != 0){
                        result = result / no_1;}
                    else {
                        result = Double.NaN;
                    }
                    break;
                case "%":
                    result = result / 100;
                    break;
            }
            String res = String.valueOf(result);
            res = res.replace(".0","");
            RESULT.setText(res);
            Operator = "";
        }

    }
    public String subString(){
        int A;
        if (DataToCalculate.contains("+")) {
            A = DataToCalculate.indexOf("+");
            DataToCalculate = DataToCalculate.substring(A+1);
        } else if (DataToCalculate.contains("-")) {
            A = DataToCalculate.indexOf("-");
            DataToCalculate = DataToCalculate.substring(A+1);
        } else if (DataToCalculate.contains("×")) {
            A = DataToCalculate.indexOf("×");
            DataToCalculate = DataToCalculate.substring(A+1);
        } else {
            A = DataToCalculate.indexOf("÷");
            DataToCalculate = DataToCalculate.substring(A+1);
        }
        return DataToCalculate;
    }
}