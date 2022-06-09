package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyEditProfile
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityRegisterBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternEditProfile


class Register : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    private var firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, arrayListOf("Intern", "Company"))
                binding?.spAccountType?.setAdapter(adapter)

        binding.btnRegisterIntern.setOnClickListener{
            val type = binding.spAccountType.selectedItem.toString()
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

                            var path = ""
                            if (type == "Intern")
                                path = "Interns"
                            else if (type == "Company")
                                path = "Companies"

                            var user = hashMapOf(
                                "name" to name,
                                "email" to email,
                                "number" to number
                                )

                            var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
                            firestore.collection(path).document(currentUser).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "You are registered", Toast.LENGTH_SHORT).show()
                                //Log.d(TAG, "Added document with ID ${it.id}")
                                }
                                .addOnFailureListener { exception ->
                                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                                    //Log.w(TAG, "Error adding document $exception")
                                }

                            if (type == "Intern") {
                                val intent = Intent (this, InternEditProfile::class.java)
                                startActivity (intent)
                                finish()
                            }

                            else if (type == "Company") {
                                val intent = Intent (this, CompanyEditProfile::class.java)
                                startActivity (intent)
                                finish()
                            }
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