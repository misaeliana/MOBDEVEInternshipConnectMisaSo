package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyMenuBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanyMenu : AppCompatActivity() {
    private lateinit var binding : ActivityCompanyMenuBinding
    private var firestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        firestore.collection("Interns").document(currentUser).get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                var company =  documentSnapshot.toObject<Intern>()
                val companyName = company?.name
                binding.tvCompanyMenuGreeting.text = "Hello " + companyName.toString()
            }
            else
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }*/

        binding.btnCompanyMenuJobs.setOnClickListener {
            val intent = Intent (this, CompanyJobListing::class.java)
            startActivity (intent)
        }

        binding.btnCompanyMenuProfile.setOnClickListener {
            val intent = Intent (this, CompanyProfile::class.java)
            startActivity (intent)
        }


        binding.btnCompanyMenuLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent (this, MainActivity::class.java)
            startActivity (intent)
            finish()
        }
    }
}