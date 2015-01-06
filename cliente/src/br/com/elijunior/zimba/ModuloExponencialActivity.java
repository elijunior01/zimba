package br.com.elijunior.zimba;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ModuloExponencialActivity extends FragmentActivity {

	Switch swModuloExpoente;
	Auxiliar aux;
	EditText etMod2X, etMod2Y, etMod2Exp;
	TextView tvMod2Result;
	Button btMod2, btLimpar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modulo_exponencial);
		instanciarObjetos();
		iniciarListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.modulo_exponencial, menu);
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
		btMod2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				aux.resolverCalculoModuloExponencial(etMod2X, etMod2Y,
						etMod2Exp, tvMod2Result);
			}
		});
		btLimpar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etMod2Exp.setText(null);
				etMod2X.setText(null);
				etMod2Y.setText(null);
				tvMod2Result.setText(null);
				etMod2X.requestFocus();
			}
		});
		swModuloExpoente
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (!isChecked) {
							startActivity(aux.getZimbaIntent());
							finish();
						}
					}
				});
	}

	private void instanciarObjetos() {
		swModuloExpoente = (Switch) findViewById(R.id.swModuloExpo);
		aux = Auxiliar.getInstance();
		btMod2 = (Button) findViewById(R.id.btMod2);
		btLimpar = (Button) findViewById(R.id.btLimparMod2);
		etMod2X = (EditText) findViewById(R.id.etMod2X);
		etMod2Y = (EditText) findViewById(R.id.etMod2Y);
		etMod2Exp = (EditText) findViewById(R.id.etMod2Exp);
		tvMod2Result = (TextView) findViewById(R.id.tvMod2Result);
	}
}