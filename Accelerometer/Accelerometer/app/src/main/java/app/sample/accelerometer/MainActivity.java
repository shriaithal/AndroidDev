package app.sample.accelerometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RandomNumberGenerator randomNumberGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateRandomNum(View view) {
        EditText editText = findViewById(R.id.editTextSimCnt);
        String simulationCountStr = editText.getText().toString();
        Integer simulationCount = Integer.parseInt(simulationCountStr);

        TextView xValueText = findViewById(R.id.editTextXVal);
        TextView yValueText = findViewById(R.id.editTextYVal);
        TextView zValueText = findViewById(R.id.editTextZVal);
        TextView logData = findViewById(R.id.textView4);

        xValueText.setText("");
        yValueText.setText("");
        zValueText.setText("");
        logData.setText("");
        logData.setMovementMethod(new ScrollingMovementMethod());

        randomNumberGenerator = new RandomNumberGenerator(simulationCount, xValueText, yValueText, zValueText, logData);
        randomNumberGenerator.execute();
    }

    public void cancelTask(View view) {
        randomNumberGenerator.cancel(true);
    }
}
