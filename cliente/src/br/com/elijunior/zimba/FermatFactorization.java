package br.com.elijunior.zimba;

import android.widget.TextView;

public class FermatFactorization {
	Auxiliar aux = Auxiliar.getInstance();
	private long maximoIteracoes;
	private TextView tvResultFermat;

	public void setTvResultFermat(TextView tvResultFermat) {
		this.tvResultFermat = tvResultFermat;
	}

	public void setMaximoIteracoes(long maximoIteracoes) {
		this.maximoIteracoes = maximoIteracoes;
	}

	public void FermatFactor(long N) throws Exception {
		long iteracao = 0;
		long a = (long) Math.ceil(Math.sqrt(N));
		long b2 = a * a - N;
		while (!isSquare(b2)) {
			a++;
			b2 = a * a - N;
			if (iteracao == this.maximoIteracoes) {
				mostrarResultadoLimitedeIteracoes();
				return;
			}
			iteracao++;
		}
		long r1 = a - (long) Math.sqrt(b2);
		long r2 = N / r1;
		long[] longs = { r1, r2 };
		mostrarResultado(longs);
	}

	private void mostrarResultado(long[] longs) {
		tvResultFermat.setText(String.valueOf(longs[0]) + " e "
				+ String.valueOf(longs[1]));
		aux.copiarResultadoParaAreaDeTransferencia(tvResultFermat.getText()
				.toString());
	}

	private void mostrarResultadoLimitedeIteracoes() {
		this.tvResultFermat.setText("Limite de iterações atingido.");
	}

	private boolean isSquare(long N) {
		long sqr = (long) Math.sqrt(N);
		if (sqr * sqr == N || (sqr + 1) * (sqr + 1) == N)
			return true;
		return false;
	}

}