package me.nils.taschenrechner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaschenrechnerActivity extends Activity {
	
	// Lege Instanzvariablen fest:
	
	private Button plusButton;
	private Button minusButton;
	private Button malButton;
	private Button geteiltButton;
	
	private TextView rechenartAnzeige;
	private TextView ergebnisAnzeige;
	
	private String rechenart = null;
	
	private boolean zahlenGesetzt = false;
	
	private float zahl1;
	private float zahl2;
	private float ergebnis;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	// Rufe Eltern-Klasse auf:
        super.onCreate(savedInstanceState);
        
        // Setzte main.xml als Layout:
        setContentView(R.layout.main);
        
        // Registriere Buttons, TextViews und Listener
        rechenartAnzeige = (TextView) findViewById(R.id.rechenart);
        ergebnisAnzeige = (TextView) findViewById(R.id.ergebnis);
        
        OnClickListener listener = new OnClickListener() {
        	public void onClick(View v) {
        		// Id des Gedrückten Buttons:
        		int buttonId = v.getId();
        		// testen, welcher Button gedrückt wurde:
        		switch (buttonId) {
        			case R.id.plus: rechenart = "+";
        			case R.id.minus: rechenart = "-";
        			case R.id.mal: rechenart = "*";
        			case R.id.geteilt: rechenart = "/";
        			case R.id.gleich: ausrechnen();
        		}
        		// wenn Rechenart gesetzt wurde, in TextView anzeigen:
        		if (rechenart != null) {
        			rechenartAnzeige.setText(rechenart);
        		}
        	}
        };        
        
        plusButton = (Button) findViewById(R.id.plus);
        plusButton.setOnClickListener( listener );
        
        minusButton = (Button) findViewById(R.id.minus);
        minusButton.setOnClickListener( listener );
        
        malButton = (Button) findViewById(R.id.mal);
        malButton.setOnClickListener( listener );
        
        geteiltButton = (Button) findViewById(R.id.geteilt);
        geteiltButton.setOnClickListener( listener );
        
    }

	protected void ausrechnen() {
		if (rechenart != null && zahlenGesetzt) {
			if (rechenart == "+") {
				ergebnis = zahl1 + zahl2;
			} else if (rechenart == "-") {
				ergebnis = zahl1 - zahl2;
			} else if (rechenart == "*") {
				ergebnis = zahl1 * zahl2;
			} else if (rechenart == "/") {
				ergebnis = zahl1 / zahl2;
			}
			String ergebnisString = Integer.toString( (int) ergebnis );
			ergebnisAnzeige.setText(  ergebnisString );
		} else {
			Toast t = new Toast( this );
			t = Toast.makeText(this, "Bitte alles ausfüllen!", Toast.LENGTH_SHORT);
			t.show();
		}
	}
    
}