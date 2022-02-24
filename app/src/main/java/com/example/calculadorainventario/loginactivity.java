package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class loginactivity extends AppCompatActivity {
    EditText edtphonenumber,edtcompany,edtsmscode;
    private CallbackManager callbackManager;

    Button btsmscode,btcreate;
    String stphone, stsms,mVerificationId, code,stcompany;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    DatabaseReference mDatabase;
    LoginButton Fblogin;
   private SignInButton GsignIn;
   private int Rc_SignIn=1;
   private String TAG="loginactivity";
   GoogleSignInClient MGoogleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        GsignIn=findViewById(R.id.login_button_g);
        edtphonenumber= findViewById(R.id.edtphonenumber);
        edtcompany= findViewById(R.id.edtcompany);
        edtsmscode= findViewById(R.id.edtsmscode);
        btsmscode= findViewById(R.id.btsmscode);
        Fblogin=findViewById(R.id.login_button);
        Fblogin.setReadPermissions("email","public_profile");
        btcreate= findViewById(R.id.btcreate);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        MGoogleClient= GoogleSignIn.getClient(this,gso);
        GsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        Fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(loginactivity.this,"succesful"+loginResult,Toast.LENGTH_SHORT).show();
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null)
                mAuth.signOut();
            }
        };
        btcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifycode(code);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(loginactivity.this, homeinvoice2.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
           // Log.d(TAG, "onAuthStateChanged:signed_out");
        }

        btsmscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stphone=edtphonenumber.getText().toString();
                if (stphone.length()==10){
                    sendsms(stphone);
                }else{
                    edtphonenumber.setError("Ingrese un número de teléfono válido para Colombia");
                }
        // OnVerificationStateChangedCallbacks
            }
        });


    }
    private void verifycode(String code){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(mVerificationId,code);
        signInWithPhoneAuthCredential(credential);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            stcompany=edtcompany.getText().toString();
                            Map<String,Object> map= new HashMap<>();
                            map.put("Compañia",stcompany);
                            map.put("método",stphone);
                            String id= mAuth.getCurrentUser().getUid();


                            mDatabase.child("USUARIOS").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent= new Intent(loginactivity.this,homeinvoice2.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        // Sign in success, update UI with the signed-in user's information
                                        //  Log.d(TAG, "signInWithCredential:success");
                                        startActivity(intent);
                                       // FirebaseUser user = task.getResult().getUser();
                                        finish();
                                    }
                                }
                            });



                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                          //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            String code=credential.getSmsCode();
            if(code!=null){
                edtsmscode.setText(code);
                verifycode(code);

            }
           // Log.d(TAG, "onVerificationCompleted:" + credential);

            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
         //   Log.w(TAG, "onVerificationFailed", e);

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
            }

            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
           // Log.d(TAG, "onCodeSent:" + verificationId);

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
           // mResendToken = token;

            // ...
        }
    };
public void sendsms(String stphone){

    PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+57"+stphone,        // Phone number to verify
            60,                 // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            this,               // Activity (for callback binding)
            mCallbacks);
}
    private void handleFacebookToken(AccessToken token){
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=mAuth.getCurrentUser();
                    Intent intent= new Intent(loginactivity.this,homeinvoice2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithCredential:success");
                    startActivity(intent);
                    // FirebaseUser user = task.getResult().getUser();
                    finish();
                    //updateUI(user);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user){
    if(user!=null){

    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    callbackManager.onActivityResult(requestCode, resultCode, data);
    if (requestCode==Rc_SignIn){
        Task<GoogleSignInAccount>task=GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task);
    }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
    if(authStateListener!=null)
        mAuth.removeAuthStateListener(authStateListener);
        super.onStop();
    }
    private void signIn(){
    Intent SignInIntent= MGoogleClient.getSignInIntent();
    startActivityForResult(SignInIntent,Rc_SignIn);
    }
private void handleSignInResult(Task<GoogleSignInAccount>completedTask){
    try {
        GoogleSignInAccount acc=completedTask.getResult(ApiException.class);
        Toast.makeText(loginactivity.this,"Success",Toast.LENGTH_SHORT).show();
        FirebaseGoogleAuth(acc);
    } catch (ApiException e) {
        Toast.makeText(loginactivity.this,"NOT",Toast.LENGTH_SHORT).show();
        FirebaseGoogleAuth(null);
    }



}
    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
    AuthCredential credential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
    mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Toast.makeText(loginactivity.this,"Inicio de sesión con Google exitoso",Toast.LENGTH_SHORT).show();
                FirebaseUser user=mAuth.getCurrentUser();
                Intent intent= new Intent(loginactivity.this,home1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // Sign in success, update UI with the signed-in user's information
                //  Log.d(TAG, "signInWithCredential:success");
                startActivity(intent);
                // FirebaseUser user = task.getResult().getUser();
                finish();


            }else{
                Toast.makeText(loginactivity.this,"falló",Toast.LENGTH_SHORT).show();
            }
        }
    });

    }
}
