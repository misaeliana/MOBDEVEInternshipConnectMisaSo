package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyMenu
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityLoginBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternMenu

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var dblink: String =
        "https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()

            //checks if email or password is blank, add for other fields also
            when {
                TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    email.trim { it <= ' ' }
                    password.trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val currentUser: String = FirebaseAuth.getInstance().currentUser!!.uid
                                val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
                                    companyDB.child(currentUser).get().addOnSuccessListener {
                                    if (it.exists()) {
                                        val intent = Intent(this, CompanyMenu::class.java)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        val currentUser: String = FirebaseAuth.getInstance().currentUser!!.uid
                                        val internDB = FirebaseDatabase.getInstance(dblink).getReference("Interns")
                                        internDB.child(currentUser).get().addOnSuccessListener {
                                            if (it.exists()) {
                                                val intent = Intent(this, InternMenu::class.java)
                                                startActivity(intent)
                                                finish()
                                            } else
                                                Toast.makeText(
                                                    this,
                                                    "User does not exist",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                        }
                                    }
                                }
                            }
                            //save other info later
                        }
                }
            }
        }
    }
}
