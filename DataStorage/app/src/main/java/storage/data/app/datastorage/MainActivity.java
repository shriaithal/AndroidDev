package storage.data.app.datastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchItem(View view) {
        Intent intent = new Intent(MainActivity.this, SearchItemActivity.class);
        startActivity(intent);
    }

    public void insertItem(View view) {
        Intent intent = new Intent(MainActivity.this, InsertItemActivity.class);
        startActivity(intent);
    }
}
