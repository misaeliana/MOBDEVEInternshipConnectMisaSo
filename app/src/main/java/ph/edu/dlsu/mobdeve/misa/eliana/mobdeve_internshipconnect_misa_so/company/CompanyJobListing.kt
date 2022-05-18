package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyJobListingAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyJobListingBinding

class CompanyJobListing : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyJobListingBinding
    private lateinit var companyJobListingAdapter: CompanyJobListingAdapter
    private lateinit var companyinternshipArrayList: ArrayList<Internship>

//    private lateinit var dbref: DatabaseReference
//    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyJobListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))

        companyJobListingAdapter = CompanyJobListingAdapter(applicationContext, companyinternshipArrayList)
        binding.rvJobListing.setAdapter(companyJobListingAdapter)

        //getCompanyJobListing()

        binding.btnAddInternship.setOnClickListener{
            val intent = Intent (this, AddInternship::class.java)
            startActivity (intent)
        }
    }

    private fun init() {
        var dao: InternshipsDAO = InternshipsDAOArrayImpl()

        var companyInternship = Internship()

        companyInternship.companyName = "company name"
        companyInternship.title = "title"
        companyInternship.description = "description"
        companyInternship.function = "function"
        companyInternship.type = "type"
        companyInternship.link = "link"
        dao.addInternship(companyInternship)
        dao.addInternship(companyInternship)
        dao.addInternship(companyInternship)
        dao.addInternship(companyInternship)
        dao.addInternship(companyInternship)

        companyinternshipArrayList = dao.getInternships()
    }

//    private fun getCompanyJobListing() {
//        var jobArrayList = ArrayList<Internship>()
//
//        dbref = FirebaseDatabase.getInstance(dblink).getReference("Internships")
//        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
//        dbref.orderByChild("companyName").equalTo(currentUser).addValueEventListener(object: ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (companySnapshot in snapshot.children) {
//                        //creating the object from list retrieved in db
//                        val job = companySnapshot.getValue(Internship::class.java)
//                        jobArrayList.add(job!!)
//                    }
//                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
//                    binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))
//
//                    companyInternInternshipAdapter = InternInternshipAdapter(applicationContext, jobArrayList)
//                    binding.rvJobListing.setAdapter(companyInternInternshipAdapter)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//
//            /*dbref.addValueEventListener(object: ValueEventListener {
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        for (companySnapshot in snapshot.children) {
//                            //creating the object from list retrieved in db
//                            val job = companySnapshot.getValue(Internship::class.java)
//                            jobArrayList.add(job!!)
//                        }
//                        //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
//                        binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))
//
//                        jobListingAdapter = JobAdapter(applicationContext, jobArrayList)
//                        binding.rvJobListing.setAdapter(jobListingAdapter)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })*/
//    }
}