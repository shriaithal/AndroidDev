package app.sample.currencyconverterbroadcaster;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String convertTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> categories = new ArrayList<String>();
        categories.add("British Pound");
        categories.add("Euro");
        categories.add("Indian Rupee");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        convertTo = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        if (null != intent) {
            String convertTo = intent.getStringExtra("converTo");
            String amountStr = intent.getStringExtra("amount");
            String convertAmount = intent.getStringExtra("convertedAmount");

            if (null != convertAmount) {
                TextView textView = findViewById(R.id.convertedText);
                textView.setText("Dollar converted price is :" + convertAmount);
            }
        }
    }

    public void convertCurrency(View view) {
        String actionString = "app.sample.currencyconverterbroadcaster.sender";
        Intent broadcastIntent = new Intent(actionString);

        EditText editText = findViewById(R.id.editText2);
        String amount = editText.getText().toString();

        broadcastIntent.addFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);
        broadcastIntent.setComponent(new ComponentName("app.sample.currentconverterbroadcastreceiver", "app.sample.currentconverterbroadcastreceiver.CurrencyConverterReceiver"));
        broadcastIntent.putExtra("amount", amount);
        broadcastIntent.putExtra("converTo", convertTo);

        sendBroadcast(broadcastIntent);
    }

    public void closeApp(View view) {
        MainActivity.this.finish();
    }
}
