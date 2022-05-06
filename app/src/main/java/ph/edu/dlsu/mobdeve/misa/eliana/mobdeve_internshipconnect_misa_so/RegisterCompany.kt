package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.data.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityRegisterCompanyBinding

class RegisterCompany : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterCompanyBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterCompany.setOnClickListener{
            val name = binding.etRegisterCompanyName.text.toString()
            val email = binding.etRegisterCompanyEmail.text.toString()
            val number = Integer.parseInt(binding.etRegisterCompanyContactNumber.text.toString())
            val password = binding.etRegisterCompanyPassword.text.toString()
            var location = binding.etRegisterCompanyLocation.text.toString()
            var website = binding.etRegisterCompanyWebsite.text.toString()

            //getInstance defines the link of the db
            database = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Companies")
            val company = Company(name, email, number, password, location, website)
            database.child(name).setValue(company).addOnSuccessListener {
                binding.etRegisterCompanyName.text.clear()
                binding.etRegisterCompanyEmail.text.clear()
                binding.etRegisterCompanyContactNumber.text.clear()
                binding.etRegisterCompanyPassword.text.clear()
                binding.etRegisterCompanyLocation.text.clear()
                binding.etRegisterCompanyWebsite.text.clear()

                val intent = Intent (this, MainActivity::class.java)
                startActivity (intent)

                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}