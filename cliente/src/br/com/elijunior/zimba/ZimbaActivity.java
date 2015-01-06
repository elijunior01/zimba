package br.com.elijunior.zimba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ZimbaActivity extends Activity {

	Switch swModulo, swModuloExpo, swFermat, swEuclides, swEuclidesEst,
			swMatrizInMod;
	Auxiliar aux;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zimba);
		instanciarObjetos();
		iniciarListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cripto_zimba, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_sobre) {
			startActivity(aux.getSobreIntent());
			return true;
		}
		if (id == R.id.action_sair) {
			confirmarSair();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		confirmarSair();
	}

	private void confirmarSair() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Deseja Sair?");
		alertDialogBuilder
				.setMessage(null)
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								moveTaskToBack(true);
								android.os.Process
										.killProcess(android.os.Process.myPid());
								System.exit(1);
							}
						})

				.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	private void instanciarObjetos() {
		swModulo = (Switch) findViewById(R.id.swModulo);
		swModuloExpo = (Switch) findViewById(R.id.swModuloExpo);
		swFermat = (Switch) findViewById(R.id.swFermat);
		swEuclides = (Switch) findViewById(R.id.swEuclides);
		swEuclidesEst = (Switch) findViewById(R.id.swEuclidesEst);
		// swMatrizInMod = (Switch) findViewById(R.id.swMatrizInMod);
		aux = Auxiliar.getInstance();
		aux.setContext(getApplicationContext());
		aux.setModuloIntent(new Intent(getApplicationContext(),
				ModuloActivity.class));
		aux.setZimbaIntent(new Intent(getApplicationContext(),
				ZimbaActivity.class));
		aux.setModulo2Intent(new Intent(getApplicationContext(),
				ModuloExponencialActivity.class));
		aux.setFermatIntent(new Intent(getApplicationContext(),
				FermatActivity.class));
		aux.setEuclidesIntent(new Intent(getApplicationContext(),
				EuclidesActivity.class));
		aux.setEuclidesEstendidoIntent(new Intent(getApplicationContext(),
				EuclidesEstendidoActivity.class));
		aux.setSobreIntent(new Intent(getApplicationContext(),
				SobreActivity.class));
	}

	private void iniciarListeners() {
		swModulo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					startActivity(aux.getModuloIntent());
					finish();
				}
			}
		});
		swModuloExpo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					startActivity(aux.getModulo2Intent());
					finish();
				}
			}
		});
		swFermat.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					startActivity(aux.getFermatIntent());
					finish();
				}
			}
		});
		swEuclides.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				startActivity(aux.getEuclidesIntent());
				finish();
			}
		});
		swEuclidesEst.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				startActivity(aux.getEuclidesEstendidoIntent());
				finish();
			}
		});
		// swMatrizInMod.setOnCheckedChangeListener(new
		// OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView,
		// boolean isChecked) {
		//
		// }
		// });

	}
}
