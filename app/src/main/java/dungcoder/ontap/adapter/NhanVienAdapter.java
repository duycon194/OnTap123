package dungcoder.ontap.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dungcoder.ontap.R;
import dungcoder.ontap.StaffInfomationActivity;
import dungcoder.ontap.model.NhanVien;

/**
 * Created by DungHigh on 3/6/2018.
 */

public class NhanVienAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<NhanVien> arrNhanVien;
    public static final String STAFF_ID = "staff_id";
    public static final String STAFF_NAME = "staff_name";
    public static final String GENDER = "gender";
    public static final String BUNDLE = "bunle";
    private Boolean isMale = true;
    public NhanVienAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrNhanVien = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_item,parent,false);
            viewHolder.img_avatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.cb_select = (CheckBox) convertView.findViewById(R.id.cb_select);
            viewHolder.imgbtn_edit = (ImageButton) convertView.findViewById(R.id.imgbtn_edit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhanVien nhanvien = arrNhanVien.get(position);
        if (nhanvien.isMisMale()) {
            viewHolder.img_avatar.setImageResource(R.drawable.icon_male);
        } else {
            viewHolder.img_avatar.setImageResource(R.drawable.icon_female);
        }
        viewHolder.tv_id.setText(nhanvien.getmID());
        viewHolder.tv_name.setText(nhanvien.getmName());

        viewHolder.imgbtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editStaffInfomation(position);
            }
        });

        return convertView;
    }

    private void editStaffInfomation(int position) {
        NhanVien nhanVien = arrNhanVien.get(position);
        String staff_id = nhanVien.getmID().toString();
        String staff_name = nhanVien.getmName().toString();
        if (nhanVien.isMisMale()) {
            isMale = true;
        } else {
            isMale = false;
        }
        Intent intent = new Intent(this.context, StaffInfomationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(STAFF_ID,staff_id);
        bundle.putString(STAFF_NAME,staff_name);
        bundle.putBoolean(GENDER,isMale);
        intent.putExtra(BUNDLE,bundle);
        context.startActivity(intent);
    }

    public class ViewHolder{
        ImageView img_avatar;
        TextView tv_id;
        TextView tv_name;
        ImageButton imgbtn_edit;
        CheckBox cb_select;
    }
}
