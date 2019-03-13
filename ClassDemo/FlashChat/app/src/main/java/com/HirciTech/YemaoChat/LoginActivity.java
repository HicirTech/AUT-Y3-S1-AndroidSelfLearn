package com.HirciTech.YemaoChat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    //firebase references
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth

        auth = FirebaseAuth.getInstance();


    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        // TODO: Call attemptLogin() here
        this.attemptLogin();

    }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.HirciTech.YemaoChat.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // TODO: Complete the attemptLogin() method
    private void attemptLogin() {


        // TODO: Use FirebaseAuth to sign in with email & password

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if(email.length()>0&&password.length()>0)
        {
            Toast.makeText(LoginActivity.this,"Logining in",Toast.LENGTH_SHORT).show();
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        System.out.println("Login successful");
                        Intent intent = new Intent(LoginActivity.this,MainChatActivity.class);
                        finish();
                        startActivity(intent);
                    }
                    else {
                        System.out.println("Login not success"+task.getException());
                        showErrorDialog("login Error");
                    }
                }
            });
        }
        else {
            return;
        }



    }

    // TODO: Show error on screen with an alert dialog

    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Hey login Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(R.drawable.ic_launcher_background).show();

}
}