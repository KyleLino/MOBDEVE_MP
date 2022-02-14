package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityGoogleLoginBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityMainBinding

class GoogleLoginActivity : AppCompatActivity() {

    var TAG = "LOGINGOOGLEACTIVITY"
    private val rc_google = 1000

    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityGoogleLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)

        auth = Firebase.auth

        binding = ActivityGoogleLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id1))//created a string in string.xml because default in valuesxml does not pass
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(this, gso)

        //LOGIN FB
        binding.buttongoogle.setOnClickListener{
            Toast.makeText(this, "DITO", Toast.LENGTH_SHORT).show()
            Log.i(TAG,"pressed login facebook")

            val signInIntent = client.signInIntent
            startActivityForResult(signInIntent, rc_google)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == rc_google) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null){
            Toast.makeText(this, "null email/user", Toast.LENGTH_SHORT).show()
            return
        }
        Log.i(TAG,"${user.displayName}-${user.uid}")
        startActivity(Intent(this, ProfileActivity::class.java))
        val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)

        
        var bundle = Bundle()
        bundle.putString("username", user.displayName)
        bundle.putString("name", user.displayName)
        bundle.putString("password", user.uid)
        gotoProfileActivity.putExtras(bundle)
        startActivity(gotoProfileActivity)
        finish()
    }
}