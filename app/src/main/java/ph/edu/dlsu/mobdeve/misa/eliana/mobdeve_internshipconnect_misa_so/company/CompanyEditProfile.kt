package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyEditProfileBinding

class CompanyEditProfile : AppCompatActivity() {
    private lateinit var binding : ActivityCompanyEditProfileBinding
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCompanyData()

        binding.btnEditCompanyCancel.setOnClickListener {
            val intent = Intent (this, CompanyMenu::class.java)
            startActivity (intent)
        }

        binding.btnEditCompanySave.setOnClickListener {
            //add code to update info
            val intent = Intent (this, CompanyMenu::class.java)
            startActivity (intent)
        }
    }

    private fun getCompanyData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
        companyDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                binding.etEditCompanyContactNumber.setText(it.child("number").value?.toString())
                binding.etEditCompanyAbout.setText(it.child("about").value?.toString())
                binding.etEditCompanyLocation.setText(it.child("location").value?.toString())
                binding.etEditCompanyWebsite.setText(it.child("course").value?.toString())
            }
        }
    }

}