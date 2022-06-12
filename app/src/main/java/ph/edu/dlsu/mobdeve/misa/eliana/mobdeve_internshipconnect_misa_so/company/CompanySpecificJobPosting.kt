package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanySpecificJobPostingApplicantsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanySpecificJobPostingBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanySpecificJobPosting : AppCompatActivity() {
    private lateinit var binding: ActivityCompanySpecificJobPostingBinding
    private lateinit var companySpecificJobPostingAdapter: CompanySpecificJobPostingApplicantsAdapter
    private lateinit var companySpecificJobPostingArrayList: ArrayList<Intern>

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanySpecificJobPostingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle: Bundle = intent.extras!!
        binding.tvInternshipDetailsTitle.text = bundle.getString("title")
        binding.tvInternshipDetailsFunction.text = bundle.getString("function")
        binding.tvInternshipDetailsType.text = bundle.getString("type")
        binding.tvInternshipDetailsDescription.text = bundle.getString("description")
        binding.tvInternshipDetailsLink.text = bundle.getString("link")

        init()
        sidebar()

        binding.rvCompanySpecificJobPostingApplicants.setLayoutManager(LinearLayoutManager(applicationContext))

        companySpecificJobPostingAdapter = CompanySpecificJobPostingApplicantsAdapter(applicationContext, companySpecificJobPostingArrayList)
        binding.rvCompanySpecificJobPostingApplicants.setAdapter(companySpecificJobPostingAdapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        var dao: InternsDAO = InternsDAOArrayImpl()

        var intern1 = Intern()

        intern1.name = "Eliana Misa"
        intern1.email = "eliana_misa@dlsu.edu.ph"
        intern1.number = "09181182787"
        intern1.about = "I aspire to pursue a career in Technology and am currently exploring the fields of Solutions Design, Project Management, and UI/UX. I’m seeking to obtain internships to further deepen my knowledge and experience in these fields. "
        intern1.school = "De La Salle University"
        intern1.course = "BS Information Systems"
        intern1.gradYear = 2023
        dao.addIntern(intern1)

        var intern2 = Intern()

        intern2.name = "Tiffany So"
        intern2.email = "tiffany_so@dlsu.edu.ph"
        intern2.number = "0983792983"
        intern2.about = "Tiffany So is a junior and is taking up a Bachelor of Science degree in Information Systems. She took up the course because she was interested in both business and technology, thus this was the course that fits her interests and allows her to solve business problems with technology."
        intern2.school = "De La Salle University"
        intern2.course = "BS Information Systems"
        intern2.gradYear = 2023
        dao.addIntern(intern2)

        companySpecificJobPostingArrayList = dao.getInterns()
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