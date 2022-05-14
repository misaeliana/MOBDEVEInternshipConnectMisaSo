package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompaniesBinding

class InternViewCompanies : AppCompatActivity() {

    private lateinit var binding: ActivityInternViewCompaniesBinding
    private lateinit var companyAdapter:CompanyAdapter

    private lateinit var dbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewCompaniesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCompanyData()
    }

    private fun getCompanyData() {
        var companyArrayList = ArrayList<Company>()

        dbref = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Companies")
        dbref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (companySnapshot in snapshot.children) {
                        //creating the object from list retrieved in db
                        val company = companySnapshot.getValue(Company::class.java)
                        companyArrayList.add(company!!)
                    }
                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvCompanyList.setLayoutManager(LinearLayoutManager(applicationContext))

                    companyAdapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvCompanyList.setAdapter(companyAdapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}