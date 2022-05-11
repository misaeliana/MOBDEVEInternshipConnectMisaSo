package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityRegisterInternBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.data.*


class RegisterIntern : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterInternBinding
    private lateinit var database : DatabaseReference
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterInternBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterIntern.setOnClickListener{

            val name = binding.etRegisterInternName.text.toString()
            val email = binding.etRegisterInternEmail.text.toString()
            val number = Integer.parseInt(binding.etRegisterInternNumber.text.toString())
            val password = binding.etRegisterInternPassword.text.toString()

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
                    email.trim{it <= ' '}
                    password.trim{it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //registers the user
                            var firebaseUser: FirebaseUser = task.result!!.user!!
                            val intern = Intern(name, email, number)

                            FirebaseDatabase.getInstance(dblink).getReference("Interns").child(
                                FirebaseAuth.getInstance().currentUser!!.uid).setValue(intern).addOnSuccessListener {
                                binding.etRegisterInternName.text.clear()
                                binding.etRegisterInternEmail.text.clear()
                                binding.etRegisterInternNumber.text.clear()
                                binding.etRegisterInternPassword.text.clear()
                            }

                            Toast.makeText(this, "You are registered", Toast.LENGTH_SHORT).show()
                            val intent = Intent (this, MainActivity::class.java)
                            startActivity (intent)
                            finish()
                        } else {
                            //not successfully registered
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


            //getInstance defines the link of the db
            /*database = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Interns")
            val user = Intern(name, email, number, password)
            database.child(email).setValue(user).addOnSuccessListener {
                binding.etRegisterInternName.text.clear()
                binding.etRegisterInternEmail.text.clear()
                binding.etRegisterInternNumber.text.clear()
                binding.etRegisterInternPassword.text.clear()



                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
            }*/
        }
    }
}