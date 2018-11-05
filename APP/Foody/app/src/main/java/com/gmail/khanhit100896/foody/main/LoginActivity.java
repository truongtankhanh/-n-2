package com.gmail.khanhit100896.foody.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.khanhit100896.foody.R;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    CheckBox chkRememberPass;
    Button btnLogin, btnLoginFacebook, btnLoginGoogle;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickLoginWithEmail();
            }
        });

        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void init() {
        edtEmail            = findViewById(R.id.edtEmailReg);
        edtPassword         = findViewById(R.id.edtPasswordReg);
        chkRememberPass     = findViewById(R.id.chkRememberPass);
        btnLogin            = findViewById(R.id.btnRegister);
        btnLoginFacebook    = findViewById(R.id.btnFacebook);
        btnLoginGoogle      = findViewById(R.id.btnGoogle);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
    }

    private void setClickLoginWithEmail() {
        if(edtEmail.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
            alertBuilder.setTitle("Notification");
            alertBuilder.setMessage("Please enter full information or account does not exist. Would you like to sign up for a new account ?");

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

    private void loginWithEmail(String email, String password){
        Config.getConfig().getmAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
                            edtEmail.setText("");
                            edtPassword.setText("");
                        }
                    }
                });
    }

    public void signIn() {
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
                Toast.makeText(LoginActivity.this,"Google sign in failed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = Config.getConfig().getmAuth().getCurrentUser();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        Config.getConfig().getmAuth().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = Config.getConfig().getmAuth().getCurrentUser();
                            assert user != null;
                            Intent intent = new Intent();
                            intent.putExtra("username",user.getDisplayName());
                            intent.putExtra("photoUrl",Objects.requireNonNull(user.getPhotoUrl()).toString());
                            intent.putExtra("email",user.getEmail());
                            setResult(RESULT_OK,intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Google sign in failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
