package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternMyInternshipsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternProfileExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyEditProfile
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyViewApplicantInformationBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternProfileBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternProfile : AppCompatActivity() {
    private lateinit var binding : ActivityInternProfileBinding
    private lateinit var internExperienceAdapter: InternProfileExperienceAdapter
    private lateinit var internshipExperienceArrayList: ArrayList<Experience>
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.rvInternProfileExperiences.setLayoutManager(LinearLayoutManager(applicationContext))
        internExperienceAdapter = InternProfileExperienceAdapter(applicationContext, internshipExperienceArrayList)
        binding.rvInternProfileExperiences.setAdapter(internExperienceAdapter)

        getInternData()

        binding.btnInternProfileEdit.setOnClickListener {
            val intent = Intent (this, InternEditProfile::class.java)
            startActivity (intent)
        }

        binding.btnInternProfileUpdate.setOnClickListener{
            val intent = Intent (this, InternUpdateExperience::class.java)
            startActivity (intent)
        }
    }

    private fun init() {
        var dao: ExperiencesDAO = ExperiencesDAOArrayImpl()

        var experience = Experience()

        experience.title = "title"
        experience.companyName = "company name"
        experience.internName = "intern name"
        experience.startDate = "start date"
        experience.endDate = "end date"

        dao.addExperience(experience)
        dao.addExperience(experience)
        dao.addExperience(experience)
        dao.addExperience(experience)
        dao.addExperience(experience)
        dao.addExperience(experience)
        dao.addExperience(experience)
        dao.addExperience(experience)

        internshipExperienceArrayList = dao.getExperiences()
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