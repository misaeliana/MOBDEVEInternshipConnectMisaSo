package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyJobListingAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyJobListingBinding

class CompanyJobListing : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyJobListingBinding
    private lateinit var companyJobListingAdapter: CompanyJobListingAdapter
    private lateinit var companyinternshipArrayList: ArrayList<Internship>

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private lateinit var dbref: DatabaseReference
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyJobListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        /*binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))

        companyJobListingAdapter = CompanyJobListingAdapter(applicationContext, companyinternshipArrayList)
        binding.rvJobListing.setAdapter(companyJobListingAdapter)*/

        getCompanyJobListing()

        binding.btnAddInternship.setOnClickListener{
            val intent = Intent (this, AddInternship::class.java)
            startActivity (intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun getCompanyJobListing() {
        var jobArrayList = ArrayList<Internship>()

        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
        dbref = FirebaseDatabase.getInstance(dblink).getReference("Internships")
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid

        dbref.orderByChild("companyName").equalTo(currentUser).addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (companySnapshot in snapshot.children) {
                        //creating the object from list retrieved in db
                        val job = companySnapshot.getValue(Internship::class.java)
                        jobArrayList.add(job!!)
                    }
                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))

                    companyJobListingAdapter = CompanyJobListingAdapter(applicationContext, jobArrayList)
                    binding.rvJobListing.setAdapter(companyJobListingAdapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun sidebar() {
        toggle = androidx.appcompat.app.ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navCompanySideMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_companyHome -> {
                    val intent = Intent (this, CompanyMenu::class.java)
                    startActivity (intent)
                }

                R.id.nav_companyProfile -> {
                    val intent = Intent (this, CompanyProfile::class.java)
                    startActivity (intent)
                }

                R.id.nav_companyOpenings -> {
                    val intent = Intent (this, CompanyJobListing::class.java)
                    startActivity (intent)
                }

                R.id.nav_companyLogout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent (this, MainActivity::class.java)
                    startActivity (intent)
                    finish()
                }
            }
            true
        }
    }
}