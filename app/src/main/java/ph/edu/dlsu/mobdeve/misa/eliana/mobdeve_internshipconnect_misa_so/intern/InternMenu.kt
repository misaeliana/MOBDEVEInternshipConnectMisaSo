package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_intern_menu.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMenuBinding

class InternMenu : AppCompatActivity() {
    private lateinit var binding : ActivityInternMenuBinding
    private var dblink ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Interns")
        companyDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                val internName = it.child("name").value
                binding.tvInternMenuGreeting.text = "Hello " + internName.toString()
            }
            else
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        binding.btnInternMenuMyJobs.setOnClickListener {
            val intent = Intent (this, InternMyInternships::class.java)
            startActivity (intent)
        }

        binding.btnInternMenuJobs.setOnClickListener {
            val intent = Intent (this, InternViewInternships::class.java)
            startActivity (intent)
        }

        binding.btnInternMenuCompanies.setOnClickListener() {
            val intent = Intent (this, InternViewCompanies::class.java)
            startActivity (intent)
        }

        binding.btnInternMenuProfile.setOnClickListener {
            val intent = Intent (this, InternProfile::class.java)
            startActivity (intent)
        }

        binding.btnInternMenuLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent (this, MainActivity::class.java)
            startActivity (intent)
            finish()
        }
    }
}