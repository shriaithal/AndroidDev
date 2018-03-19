package storage.data.app.datastorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InsertItemActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);

        dbHelper = new DBHelper(this);
    }

    public void addItem(View view) {
        EditText itemName = findViewById(R.id.itemNameInput);
        EditText itemDesc = findViewById(R.id.itemDescInput);
        EditText itemReview = findViewById(R.id.itemReviewInput);
        EditText itemPrice = findViewById(R.id.itemPriceInput);

        Item item = new Item();
        item.setItemName(itemName.getText().toString());
        item.setItemDescription(itemDesc.getText().toString());
        item.setItemReview(itemReview.getText().toString());
        item.setItemPrice(Double.parseDouble(itemPrice.getText().toString()));
        try {
            dbHelper.addItem(item);
            Toast.makeText(getApplicationContext(), "Item inserted successfully!!", Toast.LENGTH_LONG).show();
        }catch(Exception exception){
            Toast.makeText(getApplicationContext(), "Item already exists!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cancel(View view) {
        InsertItemActivity.this.finish();
    }
}
