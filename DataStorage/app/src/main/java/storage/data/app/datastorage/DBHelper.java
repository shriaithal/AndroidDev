package storage.data.app.datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Shriaithal on 3/11/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "itemManager";
    private static final String TABLE_ITEM = "item";
    private static final String ITEM_NAME = "itemName";
    private static final String ITEM_DESC = "itemDescription";
    private static final String ITEM_REVIEW = "itemReview";
    private static final String ITEM_PRICE = "itemPrice";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
                + ITEM_NAME + " TEXT PRIMARY KEY," + ITEM_DESC + " TEXT,"
                + ITEM_REVIEW + " TEXT, " + ITEM_PRICE + " REAL" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(sqLiteDatabase);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getItemName());
        values.put(ITEM_DESC, item.getItemDescription());
        values.put(ITEM_REVIEW, item.getItemReview());
        values.put(ITEM_PRICE, item.getItemPrice());

        db.insertOrThrow(TABLE_ITEM, null, values);
        db.close();
    }

    public Item getItemByItemName(String itemName) {
        Item item = new Item();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_ITEM + " where " + ITEM_NAME + " =\'" + itemName + "\'", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        item.setItemName(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
        item.setItemDescription(cursor.getString(cursor.getColumnIndex(ITEM_DESC)));
        item.setItemReview(cursor.getString(cursor.getColumnIndex(ITEM_REVIEW)));
        item.setItemPrice(cursor.getDouble(cursor.getColumnIndex(ITEM_PRICE)));
        return item;
    }
}
