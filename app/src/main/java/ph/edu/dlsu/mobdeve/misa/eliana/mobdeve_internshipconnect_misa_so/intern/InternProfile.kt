package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyEditProfile
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyViewApplicantInformationBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternProfileBinding

class InternProfile : AppCompatActivity() {
    private lateinit var binding : ActivityInternProfileBinding
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getInternData()

        binding.btnInternProfileEdit.setOnClickListener {
            val intent = Intent (this, InternEditProfile::class.java)
            startActivity (intent)
        }
    }

    private fun getInternData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val internDB = FirebaseDatabase.getInstance(dblink).getReference("Interns")
        internDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                binding.tvInternProfileName.text = it.child("name").value.toString()
                binding.tvInternProfileNumber.text = it.child("number").value.toString()
                binding.tvInternProfileAbout.text = it.child("about").value.toString()
                binding.tvInternProfileSchool.text = it.child("school").value.toString()
                binding.tvInternProfileCourse.text = it.child("course").value.toString()
                binding.tvInternProfileGradYear.text = it.child("gradYear").value.toString()
            }
            else
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}