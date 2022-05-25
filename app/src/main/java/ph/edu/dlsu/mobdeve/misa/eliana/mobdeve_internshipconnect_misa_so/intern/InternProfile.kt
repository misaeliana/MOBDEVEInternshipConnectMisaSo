package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
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
    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        sidebar()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        var dao: ExperiencesDAO = ExperiencesDAOArrayImpl()

        var experience1 = Experience()

        experience1.title = "Product Management Intern"
        experience1.companyName = "On Demand Deals"
        experience1.internName = "Eliana Misa"
        experience1.startDate = "March 2022"
        experience1.endDate = "June 2022"

        dao.addExperience(experience1)

        var experience2 = Experience()

        experience2.title = "Product Intern"
        experience2.companyName = "Shopee"
        experience2.internName = "Eliana Misa"
        experience2.startDate = "June 2022"
        experience2.endDate = "September 2022"
        dao.addExperience(experience2)

        var experience3 = Experience()

        experience3.title = "Systems Intern"
        experience3.companyName = "Amazon"
        experience3.internName = "Eliana Misa"
        experience3.startDate = "September 2022"
        experience3.endDate = "February 2023"
        dao.addExperience(experience3)

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
                binding.tvInternProfileEmail.text = it.child("email").value.toString()
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

    private fun sidebar() {
        toggle = androidx.appcompat.app.ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navInternSideMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_internHome -> {
                    val intent = Intent (this, InternMenu::class.java)
                    startActivity (intent)
                }

                R.id.nav_viewInternships -> {
                    val intent = Intent (this, InternViewInternships::class.java)
                    startActivity (intent)
                }

                R.id.nav_internPastInternships -> {
                    val intent = Intent (this, InternMyInternships::class.java)
                    startActivity (intent)
                }

                R.id.nav_viewCompanies -> {
                    val intent = Intent (this, InternViewCompanies::class.java)
                    startActivity (intent)
                }

                R.id.nav_internProfile -> {
                    val intent = Intent (this, InternProfile::class.java)
                    startActivity (intent)
                }

                R.id.nav_internLogout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent (this, MainActivity::class.java)
                    startActivity (intent)
                    finish()
                }
            }
            true
        }
    }
}