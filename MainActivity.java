package com.example.myapplicationcalculator20;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private String currentOperator = null;
    private Double operand1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                result.append(b.getText().toString());
            }
        };

        int[] numericButtonIds = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_dot};
        for (int id : numericButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                currentOperator = null;
                operand1 = null;
            }
        });

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("+");
            }
        });

        findViewById(R.id.button_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("-");
            }
        });

        findViewById(R.id.button_multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("*");
            }
        });

        findViewById(R.id.button_divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("/");
            }
        });

        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentOperator != null && !TextUtils.isEmpty(result.getText().toString())) {
                    double operand2 = Double.parseDouble(result.getText().toString());
                    double finalResult = 0;
                    if (currentOperator.equals("+")) {
                        finalResult = operand1 + operand2;
                    } else if (currentOperator.equals("-")) {
                        finalResult = operand1 - operand2;
                    } else if (currentOperator.equals("*")) {
                        finalResult = operand1 * operand2;
                    } else if (currentOperator.equals("/")) {
                        if (operand2 != 0) {
                            finalResult = operand1 / operand2;
                        } else {
                            result.setText("Error");
                            return;
                        }
                    }
                    result.setText(String.valueOf(finalResult));
                    operand1 = null;
                    currentOperator = null;
                }
            }
        });
    }

    private void handleOperator(String operator) {
        if (!TextUtils.isEmpty(result.getText().toString())) {
            operand1 = Double.parseDouble(result.getText().toString());
            currentOperator = operator;
            result.setText("");
        }
    }
}