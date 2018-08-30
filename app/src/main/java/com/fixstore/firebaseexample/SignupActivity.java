package com.fixstore.firebaseexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText etRegEmail;
    EditText etRegPassword;
    Button btnRegister;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etRegEmail = (EditText) findViewById(R.id.et_reg_email);
        etRegPassword = (EditText) findViewById(R.id.et_reg_password);
        Button btnRegister = (Button) findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etRegEmail.getText().toString().trim();
                String pass = etRegPassword.getText().toString().trim();

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignupActivity.this, "Sign Up Succssfully", Toast.LENGTH_SHORT).show();
                            finish();

                        } else
                            Toast.makeText(SignupActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
