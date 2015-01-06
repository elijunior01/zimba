package br.com.elijunior.zimba;

import java.math.BigInteger;
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

public class EuclidesEstendidoActivity extends FragmentActivity {

	Auxiliar aux;
	ExtendedEuclid euclidEst;
	Button btEuclidesEst, btEuclidesEstLimpar;
	Switch swEuclidesEst;
	EditText etEuclidesEstNum1, etEuclidesEstNum2;
	TextView tvEuclidesEstResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_euclides_estendido);
		instanciarObjetos();
		iniciarListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.euclides_estendido, menu);
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
		swEuclidesEst.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (!isChecked) {
					startActivity(aux.getZimbaIntent());
					finish();
				}

			}
		});
		btEuclidesEst.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!etEuclidesEstNum1.getText().toString().isEmpty()
						&& !etEuclidesEstNum2.getText().toString().isEmpty()) {
					BigInteger[] result;
					try {
						result = euclidEst.extendedEuclid(new BigInteger(
								etEuclidesEstNum1.getText().toString()),
								new BigInteger(etEuclidesEstNum2.getText()
										.toString()));
						tvEuclidesEstResult.setText(String.valueOf("x = "
								+ result[1] + "\ny = " + result[2]));
					} catch (Exception e) {
						aux.torradinha("Erro ao realizar o calculo.", false);
						e.printStackTrace();
					}
				} else {
					aux.torradinha("Digite os números corretamente", false);
				}
			}
		});
		btEuclidesEstLimpar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etEuclidesEstNum1.setText(null);
				etEuclidesEstNum2.setText(null);
				tvEuclidesEstResult.setText(null);
				etEuclidesEstNum1.requestFocus();
			}
		});
	}

	private void instanciarObjetos() {
		btEuclidesEst = (Button) findViewById(R.id.btEuclidesEst);
		btEuclidesEstLimpar = (Button) findViewById(R.id.btEuclidesEstLimpar);
		swEuclidesEst = (Switch) findViewById(R.id.swEuclidesEst2);
		tvEuclidesEstResult = (TextView) findViewById(R.id.tvEuclidesEstResult);
		euclidEst = new ExtendedEuclid();
		aux = Auxiliar.getInstance();
		etEuclidesEstNum1 = (EditText) findViewById(R.id.etEuclidesEstNum1);
		etEuclidesEstNum2 = (EditText) findViewById(R.id.etEuclidesEstNum2);
	}
}
