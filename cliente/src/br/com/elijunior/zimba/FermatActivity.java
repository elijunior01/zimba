package br.com.elijunior.zimba;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class FermatActivity extends FragmentActivity {

	Button btFermat;
	Switch swFermat;
	Auxiliar aux;
	TextView tvResultFermat;
	EditText etNumFermat, etMaxIteracoes;
	FermatFactorization fermat;
	ListView lvInteracoesFermat;
	CheckBox cbMaxIteracoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fermat);
		iniciarInstancias();
		iniciarListeners();
	}

	private void iniciarListeners() {
		btFermat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!etNumFermat.getText().toString().isEmpty()
						&& !etMaxIteracoes.getText().toString().isEmpty()) {

					fermat.setTvResultFermat(tvResultFermat);
					fermat.setMaximoIteracoes(Long.parseLong(etMaxIteracoes
							.getText().toString()));
					try {
						fermat.FermatFactor(Long.parseLong(etNumFermat
								.getText().toString()));
					} catch (Exception e) {
						aux.torradinha("Erro ao realzar o calculo.", false);
					}
				} else {
					aux.torradinha("Digite o número.", false);
				}
			}
		});
		swFermat.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (!isChecked) {
					startActivity(aux.getZimbaIntent());
				}
			}
		});
		cbMaxIteracoes
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							etMaxIteracoes.setEnabled(true);
						} else {
							etMaxIteracoes.setEnabled(false);
						}
					}
				});
		etNumFermat.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				etMaxIteracoes.setText(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.fermat, menu);
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

	private void iniciarInstancias() {
		btFermat = (Button) findViewById(R.id.btFermat);
		swFermat = (Switch) findViewById(R.id.swFermat2);
		tvResultFermat = (TextView) findViewById(R.id.tvResultFermat);
		etNumFermat = (EditText) findViewById(R.id.etNumFermat);
		etNumFermat.setFilters(new InputFilter[] { new InputFilterMinMax(
				"-9223372036854775808", "9223372036854775807") });
		etMaxIteracoes = (EditText) findViewById(R.id.etMaxIteracao);
		etMaxIteracoes.setText("50000000");
		etMaxIteracoes.setEnabled(false);
		cbMaxIteracoes = (CheckBox) findViewById(R.id.cbMudarMaxIteracoes);
		aux = Auxiliar.getInstance();
		fermat = new FermatFactorization();

	}
}
