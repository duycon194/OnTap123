package dungcoder.ontap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dungcoder.ontap.model.NhanVien;

/**
 * Created by DungHigh on 3/7/2018.
 */

public class DBStaff extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "staffs_list";
    private static final String TABLE_NAME = "staffs_table";
    private static final String STAFF_ID = "staff_id";
    private static final String STAFF_NAME = "staff_name";
    private static final String GENDER = "gender";
    private static final int version = 1;
    private static final String SQL_CreateTable_Query = "CREATE TABLE "+TABLE_NAME+" ( "
            +STAFF_ID+ " TEXT PRIMARY KEY , "
            +STAFF_NAME+ " TEXT , "
            +GENDER+ " integer )";

    public DBStaff(Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CreateTable_Query);
        Toast.makeText(context, "Create Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addStaffs(NhanVien nhanVien) {
        int gender;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STAFF_ID,nhanVien.getmID());
        contentValues.put(STAFF_NAME,nhanVien.getmName());
        if (nhanVien.isMisMale()) {
            gender = 0;
        } else {
            gender = 1;
        }
        contentValues.put(GENDER,gender);

        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }

    public List<NhanVien> getAllNhanVien() {
        String Query = "SELECT * FROM "+ TABLE_NAME;
        List<NhanVien> listNhanVien = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()){
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setmID(cursor.getString(0));
                nhanVien.setmName(cursor.getString(1));
//                String Male = cursor.getString(2);
//                if (Male == "true") {
                    nhanVien.setMisMale(true);
//                } else {
//                    nhanVien.setMisMale(false);
//                }
            } while (cursor.moveToNext());
        }
        database.close();
        return listNhanVien;
    }
}
