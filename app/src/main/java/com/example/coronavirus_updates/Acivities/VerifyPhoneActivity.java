package com.example.coronavirus_updates.Acivities;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coronavirus_updates.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    private EditText editTextCode;

    private String mVerificationID;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        editTextCode=(EditText) findViewById(R.id.editTextCode);
        mAuth=FirebaseAuth.getInstance();

        Intent intent=new Intent();
        String phoneNumber=getIntent().getStringExtra("phoneNumber");
        sendVerificationCode(phoneNumber);

    findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String code=editTextCode.getText().toString().trim();
            if (code.isEmpty() || code.length()<6){
                editTextCode.setError("please valid Code");
                editTextCode.requestFocus();
                return;
            }
            verifyVerficationCode(code);
        }
    });


    }

    private void verifyVerficationCode(String code) {

                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationID,code);

                signInWITHPhoneCredentail(credential);

    }

    private void signInWITHPhoneCredentail(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Intent intent=new Intent(VerifyPhoneActivity.this,DashBroadActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }else{

                            String message="Something went wrong...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                message="Invalid Code Enterd";
                            }
                            Snackbar snackbar=Snackbar.make(findViewById(R.id.parent),message,Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            snackbar.show();
                        }

                    }
                });


    }

    private void sendVerificationCode(String Number) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,mCallbacks);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code=phoneAuthCredential.getSmsCode();

            if (code !=null){

                editTextCode.setText(code);

                verifyVerficationCode(code);
            }
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            mVerificationID=s;
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };
}
