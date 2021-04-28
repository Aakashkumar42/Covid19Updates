package com.example.coronavirus_updates.Acivities;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirus_updates.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    Button signBtn;
    EditText emailtext, passwordtext;
    TextView textViewloginlink;
    Context mCtx;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailtext = (EditText) findViewById(R.id.editTextEmail);
        passwordtext = (EditText) findViewById(R.id.editTextPassword);
        signBtn = (Button) findViewById(R.id.signupBtn);
        textViewloginlink = (TextView) findViewById(R.id.loginlink);

        auth = FirebaseAuth.getInstance();

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailtext.getText().toString().trim();
                String password = passwordtext.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(mCtx, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(mCtx, "please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), SendOTPActivity.class));
                                    Toast.makeText(mCtx, "Registration are success", Toast.LENGTH_SHORT).show();


                                } else {
                                }
                                Toast.makeText(mCtx, "Something Went wrong..", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


        textViewloginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });


    }


}