package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternUpdateExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.rvCompanyInternExperiences.setLayoutManager(LinearLayoutManager(applicationContext))

        internUpdateExperienceAdapter = InternUpdateProfileExperienceAdapter(applicationContext, internUpdateExperienceArrayList)
        binding.rvCompanyInternExperiences.setAdapter(internUpdateExperienceAdapter)
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

        internUpdateExperienceArrayList = dao.getExperiences()
    }
}