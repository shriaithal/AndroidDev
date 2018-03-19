package storage.data.app.datastorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchItemActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        dbHelper = new DBHelper(this);
    }

    public void searchItem(View view) {
        EditText itemName = findViewById(R.id.itemNameInput);

        try {
            Item item = dbHelper.getItemByItemName(itemName.getText().toString());

            TextView itemDesc = findViewById(R.id.itemDescInput);
            itemDesc.setText(item.getItemDescription());

            TextView itemReview = findViewById(R.id.itemReviewInput);
            itemReview.setText(item.getItemReview());

            TextView itemPrice = findViewById(R.id.itemPriceInput);
            itemPrice.setText(item.getItemPrice().toString());
        }catch(Exception exception) {
            Toast.makeText(getApplicationContext(), "Item not found!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cancel(View view) {
        SearchItemActivity.this.finish();
    }
}
