package com.example.coronavirus_updates.Acivities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.coronavirus_updates.R;

public class SendOTPActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        editTextMobile=(EditText) findViewById(R.id.editTextMobile);
        spinner=(Spinner) findViewById(R.id.countryCode);

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountyCode.countryName));
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=CountyCode.countryCode[spinner.getSelectedItemPosition()];
                String mobile=editTextMobile.getText().toString().trim();

                if (mobile.isEmpty()||mobile.length()<10){
                    editTextMobile.setError("please a Valid Mobile no");
                    editTextMobile.requestFocus();
                    return;
                }

                String phoneNumber="+" + code +mobile;

            Intent intent=new Intent(SendOTPActivity.this,VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber",phoneNumber);
                startActivity(intent);
            }
        });
    }
}
