package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
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
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternProfile : AppCompatActivity() {
    private lateinit var binding : ActivityInternProfileBinding
    private lateinit var internExperienceAdapter: InternProfileExperienceAdapter
    private lateinit var internshipExperienceArrayList: ArrayList<Experience>
    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle
    private var firestore = Firebase.firestore


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

        experience1.title = "title"
        experience1.companyName = "company name"
        experience1.internID = "intern name"
        experience1.startDate = "start date"
        experience1.endDate = "end date"

        dao.addExperience(experience1)

        var experience2 = Experience()

        experience2.title = "Product Intern"
        experience2.companyName = "Shopee"
        experience2.internID = "Eliana Misa"
        experience2.startDate = "June 2022"
        experience2.endDate = "September 2022"
        dao.addExperience(experience2)

        var experience3 = Experience()

        experience3.title = "Systems Intern"
        experience3.companyName = "Amazon"
        experience3.internID = "Eliana Misa"
        experience3.startDate = "September 2022"
        experience3.endDate = "February 2023"
        dao.addExperience(experience3)

        internshipExperienceArrayList = dao.getExperiences()
    }

    private fun getInternData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        firestore.collection("Interns").document(currentUser).get().addOnSuccessListener { document ->
           if (document !=null) {
               var intern = document.toObject(Intern::class.java)
               binding.tvInternProfileName.text = intern?.name.toString()
               binding.tvInternProfileEmail.text = FirebaseAuth.getInstance().currentUser!!.email.toString()
               //binding.tvInternProfileNumber.text = intern?.number.toString()
               binding.tvInternProfileAbout.text = intern?.about.toString()
               binding.tvInternProfileSchool.text = intern?.school.toString()
               binding.tvInternProfileCourse.text = intern?.course.toString()
               //binding.tvInternProfileGradYear.text = intern?.gradYear.toString()
           }
            else
                Toast.makeText(this, "no data", Toast.LENGTH_SHORT)
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