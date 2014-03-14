package br.aeso.android.calcalcoolgasolina;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculadora extends Activity {

	// private static final double PRECO_GASOL 
	static final double VAR = 2;
	
	private EditText editTextAlcool;
	private EditText editTextGasolina;
	private TextView textViewResult;
	private Button buttonCalcular;
	
	private OnClickListener onclick = new OnClickListener(){
		
		@Override
		public void onClick(View Arg0){
			double valor = Double.parseDouble(editTextAlcool.getText().toString());
			double valor2 = Double.parseDouble(editTextGasolina.getText().toString());
			valor2 = valor2*0.7; 

			if (valor < valor2){
				textViewResult.setText( "VANTAGEM: GASOLINA");
			}
			else if (valor2 < valor){
				textViewResult.setText( "VANTAGEM: ALCOOL");
			}
		}
			
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        
        editTextAlcool = (EditText) findViewById(R.id.editTextAlcool);
        editTextGasolina = (EditText) findViewById(R.id.editTextGasolina);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
        
        buttonCalcular.setOnClickListener(onclick);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculadora, menu);
        return true;
    }
   
 } 
