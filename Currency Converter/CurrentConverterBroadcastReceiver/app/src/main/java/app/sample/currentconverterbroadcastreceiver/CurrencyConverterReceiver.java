package app.sample.currentconverterbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author Anushri Srinath Aithal
 * Broadcast Receiver for converting currency
 */
public class CurrencyConverterReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        //Read values from sender app
        String convertTo = intent.getStringExtra("converTo");
        String amountStr = intent.getStringExtra("amount");
        //Double amount = Double.parseDouble(amountStr);

        Log.d("Receiver", convertTo);
        Log.d("Receiver", amountStr);

        //Start Activity and pass values for conversion
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.putExtra("amount", amountStr);
        activityIntent.putExtra("converTo", convertTo);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }
}
