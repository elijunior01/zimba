package com.example.zimba;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class EuclidesActivity extends FragmentActivity {

	Auxiliar aux;
	Button btcalcularEuclides, btLimparEuclides;
	EditText etNum1Euclides, etNum2Euclides;
	TextView tvResultEuclides;
	Switch swEuclides;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_euclides);
		instanciarObjetos();
		iniciarListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.euclides, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_sobre) {
			startActivity(aux.getSobreIntent());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void iniciarListeners() {
		swEuclides.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (!isChecked) {
					startActivity(aux.getZimbaIntent());
					finish();
				}
			}
		});
		btcalcularEuclides.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				aux.resolverCalculoEuclides(etNum1Euclides, etNum2Euclides,
						tvResultEuclides);
			}
		});
		btLimparEuclides.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etNum1Euclides.setText(null);
				etNum2Euclides.setText(null);
				tvResultEuclides.setText(null);
				etNum1Euclides.requestFocus();
			}
		});
	}

	private void instanciarObjetos() {
		aux = Auxiliar.getInstance();
		btcalcularEuclides = (Button) findViewById(R.id.btEuclides);
		btLimparEuclides = (Button) findViewById(R.id.btLimparEuclides);
		etNum1Euclides = (EditText) findViewById(R.id.etNum1Euclides);
		etNum2Euclides = (EditText) findViewById(R.id.etNum2Euclides);
		tvResultEuclides = (TextView) findViewById(R.id.tvResultEuclides);
		swEuclides = (Switch) findViewById(R.id.swEuclides2);
	}
}
