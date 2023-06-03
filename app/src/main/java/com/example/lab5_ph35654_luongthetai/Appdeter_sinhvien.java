package com.example.lab5_ph35654_luongthetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Appdeter_sinhvien  extends AppCompatActivity {
    ArrayList<item_DSsinhvien> ds = new ArrayList<>();
    ListView lv_sv;
    ADTNN adtnn;
    String ten = "1";
    String diaChi = "1";
    String coSo = "1";
    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 2) {
                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();
                        ten = bundle.getString("ten");
                        diaChi = bundle.getString("location");
                        coSo = bundle.getString("coSo");

                        ds.add(new item_DSsinhvien("Họ Tên: " + ten, "Địa chỉ: " + diaChi, "Fpoly: " + coSo));
                        adtnn = new ADTNN(Appdeter_sinhvien.this, ds);
                        lv_sv.setAdapter(adtnn);


                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2);

        lv_sv = findViewById(R.id.lv_list);
        Button add = findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Appdeter_sinhvien.this, MainActivity.class);
                getData.launch(intent);
            }
        });
    }

    private class ADTNN extends BaseAdapter {
        Activity activity;
        ArrayList<item_DSsinhvien> sv;

        public ADTNN(Activity activity, ArrayList<item_DSsinhvien> sv) {
            this.activity = activity;
            this.sv = sv;
        }

        @Override
        public int getCount() {
            return sv.size();
        }

        @Override
        public Object getItem(int position) {
            return sv.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_danhsach, parent, false);

            item_DSsinhvien svModle = sv.get(position);
            TextView tvName = convertView.findViewById(R.id.tv_hoTen);
            TextView tvdiachi = convertView.findViewById(R.id.tv_diaChi);
            TextView tvCoSo = convertView.findViewById(R.id.tv_coSo);
            Button xoa = convertView.findViewById(R.id.btn_delete);
            Button update = convertView.findViewById(R.id.btn_update);

            tvName.setText(svModle.getName());
            tvdiachi.setText(svModle.getLocation());
            tvCoSo.setText(svModle.getCoSo());

            xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sv.remove(position);
                    adtnn.notifyDataSetChanged();
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Appdeter_sinhvien.this, MainActivity.class);

                    getData.launch(intent);
                }
            });
            return convertView;

        }
    }
}

