package app.intent.sample.sampleintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchURL(View v) {
        EditText editText = findViewById(R.id.editText2);
        Uri webPageUrl = Uri.parse(editText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, webPageUrl);
        startActivity(intent);
    }

    public void ring(View v) {
        EditText editText = findViewById(R.id.editText3);
        Uri phoneNumber = Uri.parse("tel:"+editText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL, phoneNumber);
        startActivity(intent);
    }

    public void finishActivity(View v) {
        MainActivity.this.finish();
    }
}
