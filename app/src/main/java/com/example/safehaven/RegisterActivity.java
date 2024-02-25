package com.example.safehaven;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText emailInput, passwordInput;
    Button register;
    TextView signIn;
    FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        register = findViewById(R.id.register);
        signIn = findViewById(R.id.moveToSignIn);

        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            String email, password;
            email = String.valueOf(emailInput.getText());
            password = String.valueOf(passwordInput.getText());

            // String.valueOf(...) converts the input text (of type String) to a String object.

            if(TextUtils.isEmpty(email)){
                Toast.makeText(RegisterActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(RegisterActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
                return;
            }
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,"Registration failed. Try again.",Toast.LENGTH_SHORT).show();
                        }

                    });
        });


    }


}