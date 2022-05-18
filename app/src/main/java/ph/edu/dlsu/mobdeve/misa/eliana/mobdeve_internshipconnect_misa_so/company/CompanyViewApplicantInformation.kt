package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.rvCompanyInternExperiences.setLayoutManager(LinearLayoutManager(applicationContext))

        companyViewApplicantExperienceAdapter = CompanyViewApplicantExperienceAdapter(applicationContext, companyViewApplicantExperienceArrayList)
        binding.rvCompanyInternExperiences.setAdapter(companyViewApplicantExperienceAdapter)
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
}