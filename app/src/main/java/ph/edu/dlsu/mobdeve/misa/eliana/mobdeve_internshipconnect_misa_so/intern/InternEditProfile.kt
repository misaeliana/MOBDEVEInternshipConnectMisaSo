package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyMenu
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternEditProfileBinding

class InternEditProfile : AppCompatActivity() {

    private lateinit var binding : ActivityInternEditProfileBinding
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInternData()

        binding.btnEditInternCancel.setOnClickListener {
            val intent = Intent (this, InternMenu::class.java)
            startActivity (intent)
        }

        binding.btnEditInternSave.setOnClickListener {
            //add code to update info
            val intent = Intent (this, InternMenu::class.java)
            startActivity (intent)
        }
    }

    fun getInternData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Interns")
        companyDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                binding.etEditInternContactNumber.setText(it.child("number").value?.toString())
                binding.etEditInternAbout.setText(it.child("about").value?.toString())
                binding.etEditInternSchool.setText(it.child("school").value?.toString())
                binding.etEditInternCourse.setText(it.child("course").value?.toString())
                binding.etEditInternGradYear.setText(it.child("gradYear").value?.toString())

            }
        }
    }
}