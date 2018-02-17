package app.sample.sampleapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Dialog as an Activity Class
 * @author Anushri Srinath Aithal
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setTheme(R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PauseCounter.getInstance().addCount();
    }

    public void killDialog(View view) {
       DialogActivity.this.finish();
       //startActivity(new Intent(DialogActivity.this, ActivityA.class));
    }
}
