package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyViewApplicantExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternUpdateProfileExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyViewApplicantInformationBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class CompanyViewApplicantInformation : AppCompatActivity() {
    private lateinit var binding : ActivityCompanyViewApplicantInformationBinding
    private lateinit var companyViewApplicantExperienceAdapter: CompanyViewApplicantExperienceAdapter
    private lateinit var companyViewApplicantExperienceArrayList: ArrayList<Experience>

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyViewApplicantInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle: Bundle = intent.extras!!
        binding.tvCompanyInternProfileName.text = bundle.getString("name")
        binding.tvCompanyInternProfileEmail.text = bundle.getString("email")
        binding.tvCompanyInternProfileNumber.text = bundle.getString("number")
        binding.tvCompanyInternProfileAbout.text = bundle.getString("about")
        binding.tvCompanyInternProfileSchool.text = bundle.getString("school")
        binding.tvCompanyInternProfileCourse.text = bundle.getString("course")
        binding.tvCompanyInternProfileGradYear.text = bundle.getString("gradYear")

        init()
        sidebar()

        binding.rvCompanyInternExperiences.setLayoutManager(LinearLayoutManager(applicationContext))

        companyViewApplicantExperienceAdapter = CompanyViewApplicantExperienceAdapter(applicationContext, companyViewApplicantExperienceArrayList)
        binding.rvCompanyInternExperiences.setAdapter(companyViewApplicantExperienceAdapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
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

        companyViewApplicantExperienceArrayList = dao.getExperiences()
    }

    private fun sidebar() {
        toggle = androidx.appcompat.app.ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navCompanySideMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_companyHome -> {
                    val intent = Intent (this, CompanyMenu::class.java)
                    startActivity (intent)
                }

                R.id.nav_companyProfile -> {
                    val intent = Intent (this, CompanyProfile::class.java)
                    startActivity (intent)
                }

                R.id.nav_companyOpenings -> {
                    val intent = Intent (this, CompanyJobListing::class.java)
                    startActivity (intent)
                }

                R.id.nav_companyLogout -> {
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