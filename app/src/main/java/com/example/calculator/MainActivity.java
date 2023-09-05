package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, solution;
    MaterialButton buttonC, button_openBrac, button_closeBrac;
    MaterialButton button_plus, button_minus, button_divide, button_multiply, button_equal;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton button_AC, button_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(buttonC, R.id.button_c);
        assignId(button_openBrac, R.id.button_openBrac);
        assignId(button_closeBrac, R.id.button_closeBrac);
        assignId(button_plus, R.id.button_plus);
        assignId(button_minus, R.id.button_minus);
        assignId(button_divide, R.id.button_divide);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_equal, R.id.button_equal);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(button_AC, R.id.button_ac);
        assignId(button_point, R.id.button_point);


    }

    void assignId(MaterialButton btn, int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String data = solution.getText().toString();

        if(buttonText.equals("AC"))
        {
            solution.setText("");
            result.setText("");
            return;
        }
        if(buttonText.equals("="))
        {
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            data = data.substring(0, data.length()-1);
        } else
        {
            data = data + buttonText;
        }

        solution.setText(data);
        String finalResult = getResult(data);

        if(!finalResult.equals("Error"))
        {
            result.setText(finalResult);
        }

    }
    String getResult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e)
        {
            return "Error";
        }
    }
}