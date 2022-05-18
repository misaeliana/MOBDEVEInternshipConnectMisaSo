package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanySpecifcJobPostingApplicantsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyViewApplicantExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanySpecificJobPostingBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanySpecificJobPosting : AppCompatActivity() {
    private lateinit var binding: ActivityCompanySpecificJobPostingBinding
    private lateinit var companySpecificJobPostingAdapter: CompanySpecifcJobPostingApplicantsAdapter
    private lateinit var companySpecificJobPostingArrayList: ArrayList<Intern>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanySpecificJobPostingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.rvCompanySpecificJobPostingApplicants.setLayoutManager(LinearLayoutManager(applicationContext))

        companySpecificJobPostingAdapter = CompanySpecifcJobPostingApplicantsAdapter(applicationContext, companySpecificJobPostingArrayList)
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