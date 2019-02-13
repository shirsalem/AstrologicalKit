package com.company.shir.myastrologicalkit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "AnonymousAuth";
    //Initialize fireBase Auth
    private FirebaseAuth mAuth;
    private ImageView imgMain;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMain = findViewById(R.id.imgMain);
        btnStart = findViewById(R.id.btnStart);

        FirebaseApp.initializeApp(MainActivity.this);
        mAuth = FirebaseAuth.getInstance();
        //check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);

        //Animation
        YoYo.with(Techniques.Pulse).duration(2000).repeat(5).playOn(findViewById(R.id.btnStart));
    }

//    FireBase connection:
    private void signInAnonymously(){
//        showProgressDialog();
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInAnonymously:success");
                    FirebaseUser user = mAuth.getCurrentUser();
//                    updateUI(user);
                }else{
                    //If sign in fails, display a message to the user
                    Log.w(TAG,"signInAnonymously:Failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication faild.", Toast.LENGTH_SHORT).show();
//                    updateUI(null);
                }
//                hideProgressDialog();
            }
        });
    }

    public void btnStart(View view) {
        signInAnonymously();

        //getting user UID (for future use)
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        Toast.makeText(this, currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        Log.d("shir", "User UID: " + currentFirebaseUser.getUid());

        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        finish();
    }
}