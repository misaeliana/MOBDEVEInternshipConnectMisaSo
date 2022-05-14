package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.JobAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMenuBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewInternshipsBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship2

class InternViewInternships : AppCompatActivity() {
    private lateinit var binding : ActivityInternViewInternshipsBinding
    private lateinit var internshipAdapter: InternshipAdapter
    private lateinit var internshipArrayList: ArrayList<Internship>
    private lateinit var internshipArrayList2: ArrayList<Internship2>

    private lateinit var dbref: DatabaseReference
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init()
        getInternships()
        //binding.rvInternViewInternships.setLayoutManager(LinearLayoutManager(applicationContext))
        // binding.rvList.setLayoutManager(GridLayoutManager(getApplicationContext(), 2))

        //internshipAdapter = InternshipAdapter(applicationContext, internshipArrayList)
        //binding.rvInternViewInternships.setAdapter(internshipAdapter)
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

        //internshipArrayList = dao.getInternships()
    }

    private fun getInternships() {
        var jobArrayList = ArrayList<Internship>()

        dbref = FirebaseDatabase.getInstance(dblink).getReference("Internships")
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (companySnapshot in snapshot.children) {
                        //creating the object from list retrieved in db
                        val job = companySnapshot.getValue(Internship::class.java)
                        jobArrayList.add(job!!)
                    }
                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvInternViewInternships.setLayoutManager(LinearLayoutManager(applicationContext))

                    internshipAdapter = InternshipAdapter(applicationContext, jobArrayList)
                    binding.rvInternViewInternships.setAdapter(internshipAdapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}