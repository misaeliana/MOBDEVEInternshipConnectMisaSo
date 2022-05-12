package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyProfileBinding

class CompanyProfile : AppCompatActivity() {

    private lateinit var binding:ActivityCompanyProfileBinding
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"
=======
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyProfileBinding

class CompanyProfile : AppCompatActivity() {
    private lateinit var binding : ActivityCompanyProfileBinding
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< Updated upstream

        getMyCompanyData()
    }

    private fun getMyCompanyData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
        companyDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                binding.tvCompanyName.text = it.child("name").value.toString()
                binding.tvIndustry.text = it.child("industry").value.toString()
                binding.tvLocation.text = it.child("location").value.toString()
                binding.tvCompanyAboutText.text = it.child("about").value.toString()
                binding.tvCompanyContactUs.text = it.child("number").value.toString()
                binding.tvLearnMore.text = it.child("learnMore").value.toString()
            }
            else
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
=======
>>>>>>> Stashed changes
    }
}