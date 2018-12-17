package com.gmail.khanhit100896.foody.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.BlurBuilder;
import com.gmail.khanhit100896.foody.config.Config;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    ConstraintLayout registerLayout;
    EditText edtEmailReg, edtPassReg, edtComfirmPassReg;
    Button btnReg;
    ImageView imageView;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        registerLayout =  findViewById(R.id.registerLayout);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blur_background_1);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        registerLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));


        init();

        this.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnclickButtonReg();
            }
        });

        this.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
    }

    public void setOnclickButtonReg(){
        String email = this.edtEmailReg.getText().toString();
        String password = this.edtPassReg.getText().toString();
        String confirmPassword = this.edtComfirmPassReg.getText().toString();

        if(email.equals("") || password.equals("") || confirmPassword.equals("")){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegisterActivity.this);
            alertBuilder.setTitle("Thông báo");
            alertBuilder.setMessage("Vui lòng nhập đầy đủ thông tin.");
            alertBuilder.setNegativeButton("OK", null);
            alertBuilder.show();
        }else if(!password.equals(confirmPassword)){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegisterActivity.this);
            alertBuilder.setTitle("Thông báo");
            alertBuilder.setMessage("Mật khẩu không trùng khớp.");
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

    /*
     * Hàm đăng ký tài khoản với email password
     */
    private void register(final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loginWithEmail(email,password);
                        }else{
                            Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản không thành công.\nVui lòng kiểm tra lại.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*
     * Chức năng đăng nhập với email
     */
    private void loginWithEmail(final String email, String password){
        Config.getConfig().getmAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                        }
                    }
                });
    }

    private void init() {
        this.edtEmailReg        = findViewById(R.id.edtEmailReg);
        this.edtPassReg         = findViewById(R.id.edtPasswordReg);
        this.edtComfirmPassReg  = findViewById(R.id.edtConfirmPasswordReg);
        this.btnReg             = findViewById(R.id.btnRegister);
        this.imageView          = findViewById(R.id.imageView);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        Intent intent = getIntent();
        assert intent != null;
        this.edtEmailReg.setText(intent.getExtras().getString("email"));
    }
}
