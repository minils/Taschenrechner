package me.nils.taschenrechner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TaschenrechnerActivity extends Activity implements OnClickListener {
	
	// Lege Instanzvariablen fest:
	
	private Button plusButton;
	private Button minusButton;
	private Button malButton;
	private Button geteiltButton;
	private Button ergebnisButton;
	
	private EditText zahlInput1;
	private EditText zahlInput2;
	
	private TextView rechenartAnzeige;
	private TextView ergebnisAnzeige;
	
	private String rechenart = null;
	
	private boolean zahlenGesetzt = false;
	
	private float zahl1;
	private float zahl2;
	private float ergebnis;
	
    @Override
    public void onCreate (Bundle savedInstanceState ) {
    	
    	// Rufe Eltern-Klasse auf:
        super.onCreate( savedInstanceState );
        
        // Setzte main.xml als Layout:
        setContentView( R.layout.main );
        
        // Registriere Buttons, TextViews, EditText und Listener
        rechenartAnzeige = (TextView) findViewById( R.id.rechenart );
        ergebnisAnzeige = (TextView) findViewById( R.id.ergebnis );      
        
        plusButton = (Button) findViewById( R.id.plus );
        plusButton.setOnClickListener( this );
        
        minusButton = (Button) findViewById( R.id.minus );
        minusButton.setOnClickListener( this );
        
        malButton = (Button) findViewById( R.id.mal );
        malButton.setOnClickListener( this );
        
        geteiltButton = (Button) findViewById( R.id.geteilt );
        geteiltButton.setOnClickListener( this );
        
        ergebnisButton = (Button) findViewById( R.id.gleich );
        ergebnisButton.setOnClickListener( this );
        
        zahlInput1 = (EditText) findViewById( R.id.zahl1 );
        zahlInput2 = (EditText) findViewById( R.id.zahl2 );
        
    }

	protected void ausrechnen() {
		
		try {
			
			String zahl1String = zahlInput1.getText().toString();
			String zahl2String = zahlInput2.getText().toString();
			
			if ( zahl1String != "" && zahl2String != "" ) {
				zahl1 = Float.valueOf( zahl1String ).floatValue();
				zahl2 = Float.valueOf( zahl2String ).floatValue();
				zahlenGesetzt = true;
			}
			
		} catch (Exception e) {
			Log.d( "FEHLER", e.getMessage() );
		}
		
		// wenn Rechenart feststeht und zwei Zahlen eingegeben wurden:
		if ( rechenart != null && zahlenGesetzt ) {
			if ( rechenart == "+" ) {
				ergebnis = zahl1 + zahl2;
			} else if ( rechenart == "-" ) {
				ergebnis = zahl1 - zahl2;
			} else if ( rechenart == "*" ) {
				ergebnis = zahl1 * zahl2;
			} else if ( rechenart == "/" ) {
				ergebnis = zahl1 / zahl2;
			}
			String ergebnisString = Float.toString( ergebnis );
			ergebnisAnzeige.setText(  ergebnisString );
		} else {
			Toast t = new Toast( this );
			t = Toast.makeText(this, "Bitte alles ausfüllen!", Toast.LENGTH_SHORT);
			t.show();
		}
	}

	// Wenn Button geklickt wurde:
	@Override
	public void onClick(View v) {
		// rechenart festlegen:
		if (v == plusButton) {
			rechenart = "+";
		} else if(v == minusButton) {
			rechenart = "-";
		} else if (v == malButton) {
			rechenart = "*";
		} else if (v == geteiltButton) {
			rechenart ="/";
		} else if (v == ergebnisButton) {
			ausrechnen();
		}
		// wenn Rechenart gesetzt wurde, in TextView anzeigen:
		if (rechenart != null) {
			rechenartAnzeige.setText(rechenart);
		}
	}
    
}