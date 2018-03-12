package app.sample.currentconverterbroadcastreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private String convertedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (null != intent) {
            renderUI(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (null != intent) {
            renderUI(intent);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        if (null != intent) {
            renderUI(intent);
        }
    }

    public void renderUI(Intent intent) {

        String convertTo = intent.getStringExtra("converTo");
        String amountStr = intent.getStringExtra("amount");

        TextView textView = findViewById(R.id.textView);
        textView.setText("Dollar Amount : $" + amountStr);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("Convert To : " + convertTo);

        convert(convertTo, amountStr);
    }

    public void convert(String convertTo, String amountStr) {

        if (null != convertTo && null != amountStr) {
            Log.d("MainActivity", convertTo);
            Log.d("MainActivity", amountStr);

            BigDecimal amount = new BigDecimal(amountStr);
            BigDecimal convertedAmount = null;

            switch (convertTo) {
                case "British Pound":
                    convertedAmount = amount.multiply(new BigDecimal(0.72));
                    break;
                case "Euro":
                    convertedAmount = amount.multiply(new BigDecimal(0.82));
                    break;
                case "Indian Rupee":
                    convertedAmount = amount.multiply(new BigDecimal(65.17));
                    break;
                default:
                    break;
            }

            convertedAmount = convertedAmount.setScale(2, BigDecimal.ROUND_DOWN);
            convertedValue = convertedAmount.toString();
        }
    }

    public void currencyConvert(View view) {

        String actionString = "app.sample.currentconverterbroadcastreceiver.sender";
        Intent broadcastIntent = new Intent(actionString);

        broadcastIntent.addFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);
        broadcastIntent.setComponent(new ComponentName("app.sample.currencyconverterbroadcaster", "app.sample.currencyconverterbroadcaster.ConvertedValueReceiver"));
        broadcastIntent.putExtra("convertedAmount", convertedValue);

        MainActivity.this.finish();
        sendBroadcast(broadcastIntent);
    }

    public void closeApp(View view) {
        MainActivity.this.finish();
    }

}
