package com.dicoding.picodiploma.zulfikarakbar.volumebalok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText panjang,lebar,tinggi;
    private Button hitung;
    private TextView hasil;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panjang = findViewById(R.id.edit_panjang);
        lebar = findViewById(R.id.edit_lebar);
        tinggi=findViewById(R.id.edit_tinggi);
        hitung=findViewById(R.id.btn_calculate);
        hasil=findViewById(R.id.tv_result);

        hitung.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            hasil.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_calculate){
            String input_panjang,input_lebar,input_tinggi;

            boolean isInvalidDouble=false;
            boolean isEmptyFields = false;

            input_panjang=panjang.getText().toString();
            input_lebar=lebar.getText().toString().trim();
            input_tinggi=tinggi.getText().toString().trim();

            if(TextUtils.isEmpty(input_panjang)){
                isEmptyFields=true;
                panjang.setError("Field ini tidak boleh kosong");
            }
            if(TextUtils.isEmpty(input_lebar)){
                isEmptyFields=true;
                lebar.setError("Field ini tidak boleh kosong");
            }
            if(TextUtils.isEmpty(input_tinggi)){
                isEmptyFields=true;
                tinggi.setError("Field ini tidak boleh kosong");
            }

            Double length,width,height;
            length = toDouble(input_panjang);
            width = toDouble(input_lebar);
            height = toDouble(input_tinggi);

            if (length == null) {
                isInvalidDouble = true;
                panjang.setError("Field ini harus berupa nomer yang valid");
            }
            if (width == null) {
                isInvalidDouble = true;
                lebar.setError("Field ini harus berupa nomer yang valid");
            }
            if (height == null) {
                isInvalidDouble = true;
                tinggi.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;
                hasil.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    @Override
    //bundles merupakan objek yang bisa disimpan di dalam onSaveInstanceState.
    // Bundles bisa di isi dengan beberapa objek Parcelable di dalamnya seperti
    // String, int, float, dll.
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, hasil.getText().toString());
    }
}
