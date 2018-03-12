package app.sample.currencyconverterbroadcaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ConvertedValueReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String convertedAmount = intent.getStringExtra("convertedAmount");
        //Double amount = Double.parseDouble(amountStr);

        Log.d("ConvertedValueReceiver", convertedAmount);

        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.putExtra("convertedAmount", convertedAmount);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }
}
