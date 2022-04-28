package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener{
            val name = binding.textName.text.toString()
            val email = binding.textEmail.text.toString()
            val number = Integer.parseInt(binding.textContactNumber.text.toString())
            val password = binding.textPassword.text.toString()

            //getInstance defines the link of the db
            database = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
            val user = User(name, email, number, password)
            database.child(email).setValue(user).addOnSuccessListener {
                System.out.print("success")
                binding.textName.text.clear()
                binding.textEmail.text.clear()
                binding.textContactNumber.text.clear()
                binding.textPassword.text.clear()

                val intent = Intent (this, MainActivity::class.java)
                startActivity (intent)

                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}