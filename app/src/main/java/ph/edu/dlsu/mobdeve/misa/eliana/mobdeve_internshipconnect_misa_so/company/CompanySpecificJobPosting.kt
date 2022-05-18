package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanySpecificJobPostingApplicantsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanySpecificJobPostingBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanySpecificJobPosting : AppCompatActivity() {
    private lateinit var binding: ActivityCompanySpecificJobPostingBinding
    private lateinit var companySpecificJobPostingAdapter: CompanySpecificJobPostingApplicantsAdapter
    private lateinit var companySpecificJobPostingArrayList: ArrayList<Intern>

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

        binding.rvCompanySpecificJobPostingApplicants.setLayoutManager(LinearLayoutManager(applicationContext))

        companySpecificJobPostingAdapter = CompanySpecificJobPostingApplicantsAdapter(applicationContext, companySpecificJobPostingArrayList)
        binding.rvCompanySpecificJobPostingApplicants.setAdapter(companySpecificJobPostingAdapter)
    }

    private fun init() {
        var dao: InternsDAO = InternsDAOArrayImpl()

        var intern = Intern()

        intern.name = "Name"
        intern.email = "email"
        intern.number = 123412313

        dao.addIntern(intern)
        dao.addIntern(intern)
        dao.addIntern(intern)
        dao.addIntern(intern)
        dao.addIntern(intern)
        dao.addIntern(intern)

        companySpecificJobPostingArrayList = dao.getInterns()
    }
}