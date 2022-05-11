package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.JobAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.data.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.data.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityJobListingBinding

class CompanyJobListing : AppCompatActivity() {

    private lateinit var binding: ActivityJobListingBinding
    private lateinit var jobListingAdapter: JobAdapter

    private lateinit var dbref: DatabaseReference
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCompanyJobListing()
    }

    private fun getCompanyJobListing() {
        var jobArrayList = ArrayList<Internship>()

        dbref = FirebaseDatabase.getInstance(dblink).getReference("Internships")
        dbref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (companySnapshot in snapshot.children) {
                        //creating the object from list retrieved in db
                        val job = companySnapshot.getValue(Internship::class.java)
                        jobArrayList.add(job!!)
                    }
                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))

                    jobListingAdapter = JobAdapter(applicationContext, jobArrayList)
                    binding.rvJobListing.setAdapter(jobListingAdapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}