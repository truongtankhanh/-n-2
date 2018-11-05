package com.gmail.khanhit100896.foody.main;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.khanhit100896.foody.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmailReg, edtPassReg, edtComfirmPassReg;
    Button btnReg;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        this.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnclickButtonReg();
            }
        });
    }

    public void setOnclickButtonReg(){
        String email = this.edtEmailReg.getText().toString();
        String password = this.edtPassReg.getText().toString();
        String confirmPassword = this.edtComfirmPassReg.getText().toString();

        if(email.equals("") || password.equals("") || confirmPassword.equals("")){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegisterActivity.this);
            alertBuilder.setTitle("Notification");
            alertBuilder.setMessage("Please enter full information.");
            alertBuilder.setNegativeButton("OK", null);
            alertBuilder.show();
        }else if(!password.equals(confirmPassword)){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegisterActivity.this);
            alertBuilder.setTitle("Notification");
            alertBuilder.setMessage("Passwords do not match.");
            alertBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    edtPassReg.setText("");
                    edtComfirmPassReg.setText("");
                }
            });
            alertBuilder.show();
        }
        else{
            register(email,password);
        }
    }


    private void register(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Sign up success",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void init() {
        this.edtEmailReg        = findViewById(R.id.edtEmailReg);
        this.edtPassReg         = findViewById(R.id.edtPasswordReg);
        this.edtComfirmPassReg  = findViewById(R.id.edtConfirmPasswordReg);
        this.btnReg             = findViewById(R.id.btnRegister);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }
}
