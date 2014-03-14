package com.example.conversormoedas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final double CONVERSAO_DOLAR = 2.45;
	private static final double CONVERSAO_EURO = 3.35;
	
	private EditText editTextValor;
	private TextView textViewDolar;
	private TextView textViewEuro;
	private Button buttonConverter;
	
	private OnClickListener onclick = new OnClickListener() {
	
		@Override
		public void onClick(View arg0) {

			double valor = Double.parseDouble(editTextValor.getText().toString());
			
			textViewDolar.setText( "US$: " + (valor / CONVERSAO_DOLAR) );
			textViewEuro.setText( "¢: " + (valor / CONVERSAO_EURO) );
			
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editTextValor = (EditText) findViewById( R.id.editTextValor );
        textViewDolar = (TextView) findViewById(R.id.textViewDolar);
        textViewEuro = (TextView) findViewById(R.id.textViewEuro);
        buttonConverter = (Button) findViewById(R.id.buttonConverter);
        
        buttonConverter.setOnClickListener(onclick);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
