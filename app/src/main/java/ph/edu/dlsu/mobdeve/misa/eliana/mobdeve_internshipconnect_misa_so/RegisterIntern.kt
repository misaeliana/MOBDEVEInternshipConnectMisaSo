package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityRegisterInternBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.data.*

class RegisterIntern : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterInternBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterInternBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterIntern.setOnClickListener{
            val name = binding.etRegisterInternName.text.toString()
            val email = binding.etRegisterInternEmail.text.toString()
            val number = Integer.parseInt(binding.etRegisterInternNumber.text.toString())
            val password = binding.etRegisterInternPassword.text.toString()

            //getInstance defines the link of the db
            database = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Interns")
            val user = Intern(name, email, number, password)
            database.child(email).setValue(user).addOnSuccessListener {
                binding.etRegisterInternName.text.clear()
                binding.etRegisterInternEmail.text.clear()
                binding.etRegisterInternNumber.text.clear()
                binding.etRegisterInternPassword.text.clear()

                val intent = Intent (this, MainActivity::class.java)
                startActivity (intent)

                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}