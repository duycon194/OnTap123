package dungcoder.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import dungcoder.ontap.adapter.NhanVienAdapter;

public class StaffInfomationActivity extends AppCompatActivity {
    private EditText edtName,edtID;
    private RadioButton rbtnMale,rbtnFemale;
    private Button btnOK,btnCancel;
    private Boolean isMale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_infomation);
        setWidget();
        getDataByIntent();

    }
    private void setWidget() {
        edtID = (EditText) findViewById(R.id.edt_id_SI);
        edtName = (EditText) findViewById(R.id.edt_name_SI);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male_SI);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female_SI);
        btnOK = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
    }

    private void getDataByIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(NhanVienAdapter.BUNDLE);
        edtID.setText(bundle.getString(NhanVienAdapter.STAFF_ID));
        edtName.setText(bundle.getString(NhanVienAdapter.STAFF_NAME));
        isMale = bundle.getBoolean(NhanVienAdapter.GENDER);
        if (isMale){
            rbtnMale.setChecked(true);
        } else{
            rbtnFemale.setChecked(true);
        }
        //edtID.setText();
    }
}
