package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyMenu
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyProfile
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
            updateInternProfile()
        }
    }

    fun getInternData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Interns")
        companyDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                binding.etEditInternName.setText(it.child("name").value?.toString())
                binding.etEditInternContactNumber.setText(it.child("number").value?.toString())
                binding.etEditInternAbout.setText(it.child("about").value?.toString())
                binding.etEditInternSchool.setText(it.child("school").value?.toString())
                binding.etEditInternCourse.setText(it.child("course").value?.toString())
                binding.etEditInternGradYear.setText(it.child("gradYear").value?.toString())

            }
        }
    }

    private fun updateInternProfile() {
        val number = binding.etEditInternContactNumber.text.toString()
        val about = binding.etEditInternAbout.text.toString()
        val school = binding.etEditInternSchool.text.toString()
        val course = binding.etEditInternCourse.text.toString()
        val gradYear = binding.etEditInternGradYear.text.toString()
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid

        val internDB = FirebaseDatabase.getInstance(dblink).getReference("Interns")
        val company = mapOf<String, String>(
            "number" to number,
            "about" to about,
            "school" to school,
            "course" to course,
            "gradYear" to gradYear
        )

        internDB.child(currentUser).updateChildren(company).addOnSuccessListener {
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT)
            val intent = Intent (this, InternProfile::class.java)
            startActivity (intent)
        }.addOnFailureListener{
            Toast.makeText(this, "Profile updated failed", Toast.LENGTH_SHORT)
        }
    }
}