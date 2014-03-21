package br.aeso.android.quizgame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends Activity {

	Context context = getApplicationContext();
	CharSequence text = "Testando 1, 2, 3. Testando!";
	int duration = Toast.LENGTH_SHORT;

	//Toast toast = Toast.makeText(context, text, duration);
	//toast.show();
	//Toast.makeText(context, text, duration).show();
	
	//public void show();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
