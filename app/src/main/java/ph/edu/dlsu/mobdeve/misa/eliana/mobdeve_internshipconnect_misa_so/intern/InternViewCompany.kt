package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternCompanyDetailsInternshipsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternInternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompaniesBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompanyBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternViewCompany : AppCompatActivity() {
    private lateinit var binding: ActivityInternViewCompanyBinding
    private lateinit var internViewCompanyInternshipsAdapter: InternCompanyDetailsInternshipsAdapter
    private lateinit var internViewCompanyInternshipsArrayList: ArrayList<Internship>

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        var bundle: Bundle = intent.extras!!
        binding.tvViewCompanyProfileCompanyName.text = bundle.getString("name")
        binding.tvViewCompanyProfileIndustry.text = bundle.getString("industry")
        binding.tvViewCompanyProfileLocation.text = bundle.getString("location")
        binding.tvViewCompanyProfileCompanyAboutText.text = bundle.getString("about")
        binding.tvViewCompanyProfileContactNumber.text = bundle.getString("number")
        binding.tvViewCompanyProfileEmail.text = bundle.getString("email")
        binding.tvViewCompanyProfileWebsite.text = bundle.getString("website")

        //to get internships offered by company
        init()

        binding.rvInternViewCompanyInternships.setLayoutManager(LinearLayoutManager(applicationContext))
        internViewCompanyInternshipsAdapter = InternCompanyDetailsInternshipsAdapter(applicationContext, internViewCompanyInternshipsArrayList)
        binding.rvInternViewCompanyInternships.setAdapter(internViewCompanyInternshipsAdapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


    private fun init() {
        var dao: InternshipsDAO = InternshipsDAOArrayImpl()

        var internship = Internship()

        internship.companyName = "company name"
        internship.title = "title"
        internship.description = "description"
        internship.function = "function"
        internship.type = "type"
        internship.link = "link"

        dao.addInternship(internship)
        dao.addInternship(internship)
        dao.addInternship(internship)
        dao.addInternship(internship)
        dao.addInternship(internship)

        internViewCompanyInternshipsArrayList = dao.getInternships()
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