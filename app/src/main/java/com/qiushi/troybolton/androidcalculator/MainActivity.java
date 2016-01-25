package com.qiushi.troybolton.androidcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private TextView textViewScreen;
	private List<Item> items = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewScreen = (TextView) findViewById(R.id.textViewScreen);

		findViewById(R.id.btn0).setOnClickListener(this);
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);
		findViewById(R.id.btn3).setOnClickListener(this);
		findViewById(R.id.btn4).setOnClickListener(this);
		findViewById(R.id.btn5).setOnClickListener(this);
		findViewById(R.id.btn6).setOnClickListener(this);
		findViewById(R.id.btn7).setOnClickListener(this);
		findViewById(R.id.btn8).setOnClickListener(this);
		findViewById(R.id.btn9).setOnClickListener(this);
		findViewById(R.id.btnAddition).setOnClickListener(this);
		findViewById(R.id.btnDivide).setOnClickListener(this);
		findViewById(R.id.btnMultiply).setOnClickListener(this);
		findViewById(R.id.btnSubtract).setOnClickListener(this);
		findViewById(R.id.btnEqual).setOnClickListener(this);
		findViewById(R.id.btnClear).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn0:
				textViewScreen.append("0");
				break;
			case R.id.btn1:
				textViewScreen.append("1");
				break;
			case R.id.btn2:
				textViewScreen.append("2");
				break;
			case R.id.btn3:
				textViewScreen.append("3");
				break;
			case R.id.btn4:
				textViewScreen.append("4");
				break;
			case R.id.btn5:
				textViewScreen.append("5");
				break;
			case R.id.btn6:
				textViewScreen.append("6");
				break;
			case R.id.btn7:
				textViewScreen.append("7");
				break;
			case R.id.btn8:
				textViewScreen.append("8");
				break;
			case R.id.btn9:
				textViewScreen.append("9");
				break;
			case R.id.btnAddition:
				items.add(new Item
						(Double.parseDouble(textViewScreen.getText().toString()), Types.NUMBER));
				checkToCompute();
				items.add(new Item(0, Types.ADDITION));
				textViewScreen.setText("");
				break;
			case R.id.btnDivide:
				items.add(new Item
						(Double.parseDouble(textViewScreen.getText().toString()), Types.NUMBER));
				checkToCompute();
				items.add(new Item(0, Types.DIVISION));
				textViewScreen.setText("");
				break;
			case R.id.btnMultiply:
				items.add(new Item
						(Double.parseDouble(textViewScreen.getText().toString()), Types.NUMBER));
				checkToCompute();
				items.add(new Item(0, Types.MULTIPLICATION));
				textViewScreen.setText("");
				break;
			case R.id.btnSubtract:
				items.add(new Item
						(Double.parseDouble(textViewScreen.getText().toString()), Types.NUMBER));
				checkToCompute();
				items.add(new Item(0, Types.SUBTRACTION));
				textViewScreen.setText("");
				break;
			case R.id.btnEqual:
				items.add(new Item
						(Double.parseDouble(textViewScreen.getText().toString()), Types.NUMBER));
				checkToCompute();
				textViewScreen.setText(items.get(0).value + "");
				items.clear();
				break;
			case R.id.btnClear:
				textViewScreen.setText("");
				items.clear();
				break;
		}
	}

	public void checkToCompute() {
		if (items.size() >= 3) {

			double numLeft = items.get(0).value;
			double numRight = items.get(2).value;
			int operation = items.get(1).type;

			items.clear();

			switch (operation) {
				case Types.ADDITION:
					items.add(new Item(numLeft + numRight, Types.NUMBER));
					break;
				case Types.DIVISION:
					items.add(new Item(numLeft / numRight, Types.NUMBER));
					break;
				case Types.MULTIPLICATION:
					items.add(new Item(numLeft * numRight, Types.NUMBER));
					break;
				case Types.SUBTRACTION:
					items.add(new Item(numLeft - numRight, Types.NUMBER));
					break;
			}
		}
	}

	private long lastClickTime = 0;

	@Override
	public void onBackPressed() {
//		super.onBackPressed();

		if (lastClickTime <= 0) {
			Toast.makeText(this, "Press \"Back\" Again to Exit", Toast.LENGTH_SHORT).show();
			lastClickTime = System.currentTimeMillis();
		} else {
			long currentClickTime = System.currentTimeMillis();
			if (currentClickTime - lastClickTime < 1100) {
				finish();
			} else {
				Toast.makeText(this, "Press \"Back\" Again to Exit", Toast.LENGTH_SHORT).show();
				lastClickTime = currentClickTime;
			}
		}
	}
}
