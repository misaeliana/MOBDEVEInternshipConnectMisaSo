package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}