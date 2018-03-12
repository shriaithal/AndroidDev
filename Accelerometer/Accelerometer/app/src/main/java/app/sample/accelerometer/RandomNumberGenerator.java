package app.sample.accelerometer;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Anushri Srinath Aithal on 3/2/2018.
 */

public class RandomNumberGenerator extends AsyncTask<String, List<Integer>, String> {

    private Integer simulationCount;
    private List<Integer> displayValues;
    private int index = 0;
    private TextView xValueText;
    private TextView yValueText;
    private TextView zValueText;
    private TextView logData;

    public RandomNumberGenerator(Integer simulationCount, TextView xValueText, TextView yValueText, TextView zValueText, TextView logData) {
        this.simulationCount = simulationCount;
        this.xValueText = xValueText;
        this.yValueText = yValueText;
        this.zValueText = zValueText;
        this.logData = logData;
        this.displayValues = new ArrayList<Integer>();
    }

    @Override
    protected String doInBackground(String... params) {
        if (simulationCount <= 0) {
            return null;
        }

        Random random = new Random();
        Integer xValue = null;
        Integer yValue = null;
        Integer zValue = null;

        for (index = 0; index < simulationCount; index++) {
            if (isCancelled()) {
                break;
            }
            xValue = random.nextInt(256);
            yValue = random.nextInt(256);
            zValue = random.nextInt(256);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }

            displayValues.add(0, xValue);
            displayValues.add(1, yValue);
            displayValues.add(2, zValue);

            publishProgress(displayValues);
        }
        publishProgress(displayValues);
        return null;
    }

    @Override
    protected void onProgressUpdate(List<Integer>... progress) {
        xValueText.setText(displayValues.get(0).toString());
        yValueText.setText(displayValues.get(1).toString());
        zValueText.setText(displayValues.get(2).toString());

        if (index >= 1) {
            String text = logData.getText().toString();
            text = text + "Simulation Count :" + index + "\n";
            text = text + "X:" + xValueText.getText().toString() + "Y:" + yValueText.getText().toString() + "Z:" + zValueText.getText().toString() + "\n";
            logData.setText(text);
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
