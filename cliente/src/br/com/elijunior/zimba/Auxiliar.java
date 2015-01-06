package br.com.elijunior.zimba;

import java.math.BigInteger;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Auxiliar extends Activity {

	private static Auxiliar _instance = null;
	private NotificationManager mManager;
	private Intent moduloIntent, zimbaIntent, modulo2Intent, fermatIntent,
			euclidesIntent, euclidesEstendidoIntent, sobreIntent;

	public Intent getSobreIntent() {
		return sobreIntent;
	}

	public void setSobreIntent(Intent sobreIntent) {
		this.sobreIntent = sobreIntent;
	}

	public Intent getEuclidesEstendidoIntent() {
		return euclidesEstendidoIntent;
	}

	public void setEuclidesEstendidoIntent(Intent euclidesEstendidoIntent) {
		this.euclidesEstendidoIntent = euclidesEstendidoIntent;
	}

	public Intent getEuclidesIntent() {
		return euclidesIntent;
	}

	public void setEuclidesIntent(Intent euclidesIntent) {
		this.euclidesIntent = euclidesIntent;
	}

	private Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Intent getFermatIntent() {
		return fermatIntent;
	}

	public void setFermatIntent(Intent fermatIntent) {
		this.fermatIntent = fermatIntent;
	}

	public final int SOMAR = 1, SUBTRAIR = 2, MULTIPLICAR = 3, DIVIDIR = 4,
			POTENCIAR = 5;

	public Intent getModulo2Intent() {
		return modulo2Intent;
	}

	public void setModulo2Intent(Intent modulo2Intent) {
		this.modulo2Intent = modulo2Intent;
	}

	public Intent getZimbaIntent() {
		return zimbaIntent;
	}

	public void setZimbaIntent(Intent zimbaIntent) {
		this.zimbaIntent = zimbaIntent;
	}

	public Intent getModuloIntent() {
		return moduloIntent;
	}

	public void setModuloIntent(Intent moduloIntent) {
		this.moduloIntent = moduloIntent;
	}

	public static void clear() {
		_instance = null;
	}

	private Auxiliar() {

	}

	public static Auxiliar getInstance() {
		if (_instance == null) {
			_instance = new Auxiliar();
		}
		return _instance;
	}

	public void torradinha(String msg, boolean longo) {
		if (longo) {
			Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else {
			Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}

	@SuppressWarnings("deprecation")
	void notificar(String mensagem) {
		mManager = (NotificationManager) getContext().getSystemService(
				Context.NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(getContext(), ZimbaActivity.class);

		Notification notification = new Notification(R.drawable.iconeprincipal,
				mensagem, System.currentTimeMillis());

		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
				getContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(getContext(), "WakeUnlike", mensagem,
				pendingNotificationIntent);

		mManager.notify(0, notification);
	}

	void copiarResultadoParaAreaDeTransferencia(String txt) {
		ClipboardManager clipboard = (ClipboardManager) getContext()
				.getSystemService(CLIPBOARD_SERVICE);
		android.content.ClipData clip = android.content.ClipData.newPlainText(
				"Resultado Copiado", txt);
		clipboard.setPrimaryClip(clip);
		torradinha("Resultado copiado para área de transferência.", false);
	}

	public void resolverCalculoModulo(EditText etModX, EditText etModY,
			TextView tvResult) {
		String a = etModX.getText().toString();
		String b = etModY.getText().toString();
		if (a.isEmpty() || b.isEmpty()) {
			torradinha("Insira os numeros corretamente.", false);
			return;
		}
		BigInteger x = new BigInteger(a);
		BigInteger y = new BigInteger(b);

		if (y.signum() < 0 || y.signum() > 0) {
			if (y.signum() < 0) {
				torradinha("Mod com Y negativo ainda não implementado.", true);
			}
			if (y.signum() > 0) {
				tvResult.setText(String.valueOf(x.mod(y)));
				copiarResultadoParaAreaDeTransferencia(tvResult.getText()
						.toString());
			}
		} else {
			torradinha("Y não pode ser 0", false);
			return;
		}
	}

	public void resolverCalculoEuclides(EditText etNum1Euclides,
			EditText etNum2Euclides, TextView tvResultEuclides) {
		if (etNum1Euclides.getText().toString().isEmpty()
				|| etNum2Euclides.getText().toString().isEmpty()) {
			torradinha("Insira os numeros corretamente.", false);
			return;
		}
		BigInteger num1 = new BigInteger(etNum1Euclides.getText().toString());
		BigInteger num2 = new BigInteger(etNum2Euclides.getText().toString());
		String result = String.valueOf(num1.gcd(num2));
		tvResultEuclides.setText(String.valueOf("MDC = " + result));
		copiarResultadoParaAreaDeTransferencia(result);
	}

	public void resolverCalculoModuloExponencial(EditText etMod2X,
			EditText etMod2Y, EditText etMod2Exp, TextView tvMod2Result) {
		String a = etMod2X.getText().toString();
		String b = etMod2Y.getText().toString();
		String c = etMod2Exp.getText().toString();
		if (a.isEmpty() || b.isEmpty() || c.isEmpty()) {
			torradinha("Insira os numeros corretamente.", false);
			return;
		}
		BigInteger x = new BigInteger(a);
		BigInteger y = new BigInteger(b);
		BigInteger z = new BigInteger(c);

		if (y.signum() < 0 || y.signum() > 0) {
			if (y.signum() < 0) {
				torradinha("Mod com Y negativo ainda não implementado.", false);
			}
			if (y.signum() > 0) {
				tvMod2Result.setText(String.valueOf(x.modPow(z, y)));
				copiarResultadoParaAreaDeTransferencia(tvMod2Result.getText()
						.toString());
			}
		} else {
			torradinha("Y não pode ser 0", false);
		}
	}

	public void resolverCalculoOpcoesAuxiliares(final int tipo,
			EditText etNum1Aux, EditText etNum2Aux, EditText etEspAux,
			TextView tvResultAux) throws Exception {
		String a = etNum1Aux.getText().toString();
		String b = etNum2Aux.getText().toString();
		int c = 0;
		BigInteger num2Aux = null;
		BigInteger num1Aux = null;
		if (tipo != POTENCIAR) {
			if (!a.isEmpty() && !b.isEmpty()) {
				num1Aux = new BigInteger(a);
				num2Aux = new BigInteger(b);
			} else {
				torradinha("Insira os numeros corretamente.", false);
				return;
			}
		} else {
			if (!a.isEmpty() && !etEspAux.getText().toString().isEmpty()) {
				num1Aux = new BigInteger(a);
				c = Integer.parseInt(etEspAux.getText().toString());
			} else {
				torradinha("Insira os numeros corretamente.", false);
				return;
			}
		}
		switch (tipo) {
		case SOMAR:
			tvResultAux.setText(String.valueOf(num1Aux.add(num2Aux)));
			break;
		case SUBTRAIR:
			tvResultAux.setText(String.valueOf(num1Aux.subtract(num2Aux)));
			break;
		case MULTIPLICAR:
			tvResultAux.setText(String.valueOf(num1Aux.multiply(num2Aux)));
			break;
		case DIVIDIR:
			tvResultAux.setText(String.valueOf(num1Aux.divide(num2Aux)));
			break;
		case POTENCIAR:
			try {
				tvResultAux.setText(String.valueOf(num1Aux.pow(c)));
			} catch (ArithmeticException ex) {
				torradinha("Insira o expoente corretamente.", false);
			}
			break;
		default:
			break;
		}
		copiarResultadoParaAreaDeTransferencia(tvResultAux.getText().toString());
	}

}
