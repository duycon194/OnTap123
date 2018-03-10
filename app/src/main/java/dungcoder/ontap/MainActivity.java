package dungcoder.ontap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import dungcoder.ontap.adapter.NhanVienAdapter;
import dungcoder.ontap.database.DBStaff;
import dungcoder.ontap.model.NhanVien;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtID;
    private EditText edtName;
    private RadioButton rbtnMale,rbtnFemale;
    private Button btnAdd;
    private ImageButton imgbtnDelete;
    private ListView lvNhanVien;
    private ArrayList<NhanVien> arrNhanVien;
    private NhanVienAdapter adapterNhanVien;
    public static final String STAFF_ID = "staff_id";
    public static final String STAFF_NAME = "staff_name";
    public static final String GENDER = "gender";
    public static final String BUNDLE = "bunle";
    private Boolean isMale = true;
    private DBStaff dbStaff;
    private List<NhanVien> listNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NhanVien fisrtnv = fisrtStaff();
        dbStaff = new DBStaff(this);
        dbStaff.addStaffs(fisrtnv);
        listNhanVien = dbStaff.getAllNhanVien();
        setAdapter();
        setWidget();
        setEvents();
    }

    private NhanVien fisrtStaff() {
        String name = "Dung";
        String id = "001";
        Boolean male = true;
        NhanVien nhanVien = new NhanVien(id,male,name);
        return nhanVien;
    }
    private void setAdapter() {
        if (adapterNhanVien == null) {
            adapterNhanVien = new NhanVienAdapter(this, R.layout.custom_item, listNhanVien);
            lvNhanVien.setAdapter(adapterNhanVien);
        } else {
            adapterNhanVien.notifyDataSetChanged();
        }

    }

    public boolean isMale() {
        if (rbtnMale.isChecked()) {
            isMale = true;
        } else {
            isMale = false;
        }
        return isMale;
    }
    public void setWidget() {
        edtID = (EditText) findViewById(R.id.edt_id);
        edtName = (EditText) findViewById(R.id.edt_name);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
        btnAdd = (Button) findViewById(R.id.btn_add);
        imgbtnDelete = (ImageButton) findViewById(R.id.imgbtn_delete);
        lvNhanVien = (ListView) findViewById(R.id.lv_nhan_vien);
    }

    public NhanVien createNhanVien() {
        String id = edtID.getText().toString();
        String name = edtName.getText().toString();
        Boolean male = isMale();
        NhanVien nhanVien = new NhanVien(id,male,name);
        return nhanVien;
    }

    public void setEvents() {
        btnAdd.setOnClickListener(this);
        imgbtnDelete.setOnClickListener(this);
//        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                showInfomation(i);
//            }
//        });
//    }
//
//    private void showInfomation(int position) {
//        setIntentByBundle(position);
//    }
//
//    private void setIntentByBundle(int position) {
//        NhanVien nhanVien = arrNhanVien.get(position);
//        isMale = isMale();
//        Intent intent = new Intent(MainActivity.this,StaffInfomationActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(STAFF_ID,nhanVien.getmID().toString());
//        bundle.putString(STAFF_NAME,nhanVien.getmName().toString());
//        bundle.putBoolean(GENDER,nhanVien.isMisMale());
//        intent.putExtra(BUNDLE,bundle);
//        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                addNhanVien();
                break;
            case R.id.imgbtn_delete:
                //TO DO
                break;
            default:
                break;
        }
    }

    private void addNhanVien() {
        NhanVien nhanVien = createNhanVien();
        if (nhanVien != null) {
            dbStaff.addStaffs(nhanVien);
        }
        listNhanVien.clear();
        listNhanVien.addAll(dbStaff.getAllNhanVien());
        setAdapter();
    }
}
