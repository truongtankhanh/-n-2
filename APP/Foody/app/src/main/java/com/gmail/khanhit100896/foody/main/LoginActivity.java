package com.gmail.khanhit100896.foody.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.BlurBuilder;
import com.gmail.khanhit100896.foody.config.Config;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private String context;
    private ConstraintLayout loginLayout;
    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnLoginGoogle;
    private LoginButton btnLoginFacebook;
    private ImageView imageView;
    private CheckBox chkRemember;
    private TextView txtForgotPassword;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;

    private CallbackManager callbackManager;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        loginLayout =  findViewById(R.id.loginLayout);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blur_background_1);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        loginLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        init();

        sharedPreferences = getSharedPreferences("DataLoginWithEmail",MODE_PRIVATE);

        edtEmail.setText(sharedPreferences.getString("email",""));
        edtPassword.setText(sharedPreferences.getString("password",""));
        chkRemember.setChecked(sharedPreferences.getBoolean("checked",false));


        /*
         * Chức năng đăng nhập với email
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickLoginWithEmail();
            }
        });

        /*
         * Chức năng đăng nhập với google
         */
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getApplicationContext(),Class.forName(context).getClass()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        /*
         * Chức năng đăng nhập với facebook
         */
        this.btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Toast toast = Toast.makeText(getApplicationContext(),"Đăng nhập facebook thành công",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }

            @Override
            public void onCancel() {
                //Toast.makeText(getApplicationContext(),"Đăng nhập facebook thành công",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast toast = Toast.makeText(getApplicationContext(),"Đăng nhập facebook không thành công",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });

        /*
         * Chức năng quên mật khẩu
         */
        this.txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForgot();
            }
        });
    }

    private void DialogForgot() {
        Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_forgot_password);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtEmailForgot   = dialog.findViewById(R.id.edtEmailForgot);
        Button btnXacNhan               = dialog.findViewById(R.id.btnXacNhan);

        edtEmailForgot.setText(edtEmail.getText().toString());

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmailForgot.getText().toString().equals("")){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertBuilder.setTitle("Thông báo");
                    alertBuilder.setMessage("Email không hợp lệ, vui lòng nhập lại.");

                    alertBuilder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertBuilder.show();
                }else{
                    resetPassword(edtEmailForgot.getText().toString().trim());
                }
            }
        });

        dialog.show();
    }

    /*
     * Chức năng reset mật khẩu
     */
    private void resetPassword(final String emailAddress) {
        Config.getConfig().getmAuth().sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
                            alertBuilder.setTitle("Thông báo");
                            alertBuilder.setMessage("Mời vào hộp thư email và nhập mật khẩu mới.");

                            alertBuilder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertBuilder.show();
                            //Toast.makeText(getApplicationContext(),"Đã gửi tới email, vui lòng kiểm tra email.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
                            alertBuilder.setTitle("Thông báo");
                            alertBuilder.setMessage("Email " + emailAddress + " chưa được đăng ký.\nBạn có muốn đăng ký tài khoản mới?");

                            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(LoginActivity.this,
                                            RegisterActivity.class).putExtra("email",emailAddress));
                                }
                            });
                            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edtEmail.setText("");
                                    edtPassword.setText("");
                                }
                            });
                            alertBuilder.show();
                        }
                    }
                });
    }

    private void init() {
        edtEmail            = findViewById(R.id.edtEmail);
        edtPassword         = findViewById(R.id.edtPassword);
        btnLogin            = findViewById(R.id.btnLogin);
        btnLoginFacebook    = findViewById(R.id.btnFacebook);
        btnLoginGoogle      = findViewById(R.id.btnGoogle);
        imageView           = findViewById(R.id.imageView);
        this.chkRemember    = findViewById(R.id.chk_remember);
        txtForgotPassword   = findViewById(R.id.txt_forgot_password);

        Intent intent = getIntent();
        this.context = intent.getExtras().getString("context");

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        this.callbackManager = CallbackManager.Factory.create();
        this.btnLoginFacebook.setReadPermissions("email", "public_profile");
    }

    private void setClickLoginWithEmail() {
        if(edtEmail.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
            alertBuilder.setTitle("Thông báo");
            alertBuilder.setMessage("Vui lòng nhập đầy đủ thông tin.\nNếu bạn chưa có tài khoản, hãy đăng ký tài khoản mới.");

            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }
            });
            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    edtEmail.setText("");
                    edtPassword.setText("");
                }
            });
            alertBuilder.show();
        }else{
            loginWithEmail(edtEmail.getText().toString(), edtPassword.getText().toString());
        }
    }

    /*
     * Chức năng đăng nhập với email
     */
    private void loginWithEmail(final String email, String password){
        Config.getConfig().getmAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(chkRemember.isChecked()){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email",edtEmail.getText().toString());
                                editor.putString("password",edtPassword.getText().toString());
                                editor.putBoolean("checked",true);
                                editor.commit();
                            }
                            else{
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("email");
                                editor.remove("password");
                                editor.remove("checked");
                                editor.commit();
                            }
                            finish();
                        }else{
                            //Toast.makeText(LoginActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
                            alertBuilder.setTitle("Thông báo");
                            alertBuilder.setMessage("Tài khoản không tồn tại.\nBạn có muốn đăng ký tài khoản mới với email " + email + " không?");

                            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(LoginActivity.this,
                                            RegisterActivity.class).putExtra("email",email));
                                }
                            });
                            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edtEmail.setText("");
                                    edtPassword.setText("");
                                }
                            });
                            alertBuilder.show();
                            edtEmail.setText("");
                            edtPassword.setText("");
                        }
                    }
                });
    }

    /*
     * Chức năng đăng nhập với google
     */
    public void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast toast = Toast.makeText(LoginActivity.this,"Google sign in failed",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = Config.getConfig().getmAuth().getCurrentUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Config.getConfig().getmAuth().removeAuthStateListener(Config.getConfig().getAuthStateListener());
    }

    /*
     * Hàm đăng nhập bằng tài khoản google
     */
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        Config.getConfig().getmAuth().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                        }else{
                            Toast toast = Toast.makeText(LoginActivity.this,"Google sign in failed",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }
                });
    }

    /*
     * Hàm đăng nhập bằng tài khoản facebook
     */
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Config.getConfig().getmAuth().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast toast = Toast.makeText(LoginActivity.this,"Facebook sign in failed",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }
                });
    }

}
