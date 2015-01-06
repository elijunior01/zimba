package com.example.zimba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class OpcoesAuxiliaresFragmentActivity extends Fragment {

	Button btSomarAux, btSubtrairAux, btMultiplicarAux, btDividirAux,
			btPotenciarAux;
	Switch swtFunAux;
	Auxiliar aux;
	EditText etEspAux, etNum1Aux, etNum2Aux;
	TextView tvResultAux;
	LinearLayout llFunAux;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.activity_opcoes_auxiliares_fragment, container);
		instanciarObjetos(view);
		iniciarListeners();
		return view;
	}

	private void instanciarObjetos(View view) {
		aux = Auxiliar.getInstance();
		swtFunAux = (Switch) view.findViewById(R.id.swtFunAux);
		llFunAux = (LinearLayout) view.findViewById(R.id.llFunAux);
		llFunAux.setVisibility(LinearLayout.GONE);
		etEspAux = (EditText) view.findViewById(R.id.etEspAux);
		etEspAux.setFilters(new InputFilter[] { new InputFilterMinMax(
				"-100000", "500000") });
		etNum1Aux = (EditText) view.findViewById(R.id.etNum1Aux);
		etNum2Aux = (EditText) view.findViewById(R.id.etNum2Aux);
		tvResultAux = (TextView) view.findViewById(R.id.tvResultAux);
		btDividirAux = (Button) view.findViewById(R.id.btDividirAux);
		btMultiplicarAux = (Button) view.findViewById(R.id.btMultiplicarAux);
		btPotenciarAux = (Button) view.findViewById(R.id.btPotenciarAux);
		btSomarAux = (Button) view.findViewById(R.id.btSomarAux);
		btSubtrairAux = (Button) view.findViewById(R.id.btSubtrairAux);
	}

	private void iniciarListeners() {
		swtFunAux.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					llFunAux.setVisibility(LinearLayout.VISIBLE);
					limparAux();
				} else {
					llFunAux.setVisibility(LinearLayout.GONE);
				}
			}
		});
		etEspAux.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					etNum2Aux.setText(null);
				}
			}
		});
		etNum2Aux.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					etEspAux.setText(null);
				}
			}
		});
		btSomarAux.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					aux.resolverCalculoOpcoesAuxiliares(aux.SOMAR, etNum1Aux, etNum2Aux,
							etEspAux, tvResultAux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btSubtrairAux.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					aux.resolverCalculoOpcoesAuxiliares(aux.SUBTRAIR, etNum1Aux, etNum2Aux,
							etEspAux, tvResultAux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btMultiplicarAux.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					aux.resolverCalculoOpcoesAuxiliares(aux.MULTIPLICAR, etNum1Aux, etNum2Aux,
							etEspAux, tvResultAux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btDividirAux.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					aux.resolverCalculoOpcoesAuxiliares(aux.DIVIDIR, etNum1Aux, etNum2Aux,
							etEspAux, tvResultAux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btPotenciarAux.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					aux.resolverCalculoOpcoesAuxiliares(aux.POTENCIAR, etNum1Aux, etNum2Aux,
							etEspAux, tvResultAux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limparAux() {
		etEspAux.setText(null);
		etNum1Aux.setText(null);
		etNum2Aux.setText(null);
		tvResultAux.setText(null);
		etNum1Aux.requestFocus();
	}

}
