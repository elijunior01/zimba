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

public class ModuloActivity extends FragmentActivity {

	Switch swModulo, swtFunAux;
	Auxiliar aux;
	EditText etModX, etModY;
	TextView tvResult;
	Button btMod, btLimpar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modulo);
		instanciarObjetos();
		iniciarListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.modulo, menu);
		return true;
	}

	private void iniciarListeners() {
		btMod.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				aux.resolverCalculoModulo(etModX, etModY, tvResult);
			}
		});
		swModulo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (!isChecked) {
					startActivity(aux.getZimbaIntent());
					finish();
				}
			}
		});
		btLimpar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etModX.setText(null);
				etModY.setText(null);
				tvResult.setText(null);
				etModX.requestFocus();
			}
		});
	}

	private void instanciarObjetos() {
		swModulo = (Switch) findViewById(R.id.swModulo);
		aux = Auxiliar.getInstance();
		btMod = (Button) findViewById(R.id.btMod2);
		btLimpar = (Button) findViewById(R.id.btLimparMod);
		etModX = (EditText) findViewById(R.id.etMod2X);
		etModY = (EditText) findViewById(R.id.etMod2Y);
		tvResult = (TextView) findViewById(R.id.tvMod2Result);
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
}
