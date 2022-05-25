package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternUpdateProfileExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.CompaniesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.CompaniesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMenuBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternUpdateExperienceBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class InternUpdateExperience : AppCompatActivity() {
    private lateinit var binding : ActivityInternUpdateExperienceBinding
    private lateinit var internUpdateExperienceAdapter: InternUpdateProfileExperienceAdapter
    private lateinit var internUpdateExperienceArrayList: ArrayList<Experience>
    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternUpdateExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        sidebar()

        binding.rvCompanyInternExperiences.setLayoutManager(LinearLayoutManager(applicationContext))

        internUpdateExperienceAdapter = InternUpdateProfileExperienceAdapter(applicationContext, internUpdateExperienceArrayList)
        binding.rvCompanyInternExperiences.setAdapter(internUpdateExperienceAdapter)

        binding.btnUpdateExperienceAdd.setOnClickListener{
            var experience = Experience()

            experience.title = binding.etUpdateExperienceTitle.text.toString()
            experience.companyName = binding.etUpdateExperienceCompanyName.text.toString()
            experience.internID = "Intern name"
            experience.startDate = binding.etUpdateExperienceStartDate.text.toString()
            experience.endDate = binding.etUpdateExperienceEndDate.text.toString()

            internUpdateExperienceAdapter.addExperience(experience)

            //clear fields
            binding.etUpdateExperienceCompanyName.text.clear()
            binding.etUpdateExperienceTitle.text.clear()
            binding.etUpdateExperienceStartDate.text.clear()
            binding.etUpdateExperienceEndDate.text.clear()
        }

        binding.btnUpdateExperienceSave.setOnClickListener {
            //save updated data in db
            val intent = Intent (this, InternProfile::class.java)
            startActivity (intent)
        }

        binding.btnUpdateExperienceCancel.setOnClickListener{
            val intent = Intent (this, InternProfile::class.java)
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
        experience1.internID = "Eliana Misa"
        experience1.startDate = "March 2022"
        experience1.endDate = "June 2022"

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

        internUpdateExperienceArrayList = dao.getExperiences()
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