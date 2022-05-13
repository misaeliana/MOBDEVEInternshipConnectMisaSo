package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMenuBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMyInternshipsBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship2

class InternMyInternships : AppCompatActivity() {
    private lateinit var binding : ActivityInternMyInternshipsBinding
    private lateinit var internshipAdapter: InternshipAdapter
    private lateinit var internshipArrayList: ArrayList<Internship2>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternMyInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
        // binding.rvList.setLayoutManager(GridLayoutManager(getApplicationContext(), 2))

        internshipAdapter = InternshipAdapter(applicationContext, internshipArrayList)
        binding.rvList.setAdapter(internshipAdapter)
    }

    private fun init() {
        var dao: InternshipsDAO = InternshipsDAOArrayImpl()

        var internship = Internship2()

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