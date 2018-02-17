package app.sample.sampleapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Activity A class
 * @author Anushri Srinath Aithal
 */
public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Integer threadCounter = PauseCounter.getInstance().getCounter();
        TextView tv = (TextView)findViewById(R.id.threadCounter);
        tv.setText("Thread Counter : "+ threadCounter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PauseCounter.getInstance().addCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PauseCounter.getInstance().resetCounter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Integer threadCounter = PauseCounter.getInstance().getCounter();
        TextView tv = (TextView)findViewById(R.id.threadCounter);
        tv.setText("Thread Counter : "+ threadCounter);
    }

    public void startActivityB(View view){
        startActivity(new Intent(ActivityA.this, ActivityB.class));
    }

    public void  viewDialog(View view){
        startActivity(new Intent(ActivityA.this, DialogActivity.class));
    }

    public void killActivityA(View view) {
        finish();
       // System.exit(0);
    }
}

