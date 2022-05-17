package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternInternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMyInternshipsBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternMyInternships : AppCompatActivity() {
    private lateinit var binding : ActivityInternMyInternshipsBinding
    private lateinit var internInternshipAdapter: InternInternshipAdapter
    private lateinit var internshipArrayList: ArrayList<Internship>
    //private lateinit var internshipArrayList2: ArrayList<Internship2>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternMyInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
        // binding.rvList.setLayoutManager(GridLayoutManager(getApplicationContext(), 2))

        internInternshipAdapter = InternInternshipAdapter(applicationContext, internshipArrayList)
        binding.rvList.setAdapter(internInternshipAdapter)
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

        internshipArrayList = dao.getInternships()
    }
}