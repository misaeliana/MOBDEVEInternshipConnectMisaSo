package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternInternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewInternshipsBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternViewInternships : AppCompatActivity() {
    private lateinit var binding: ActivityInternViewInternshipsBinding
    private lateinit var internInternshipAdapter: InternInternshipAdapter
    private lateinit var internshipArrayList: ArrayList<Internship>

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private lateinit var dbref: DatabaseReference
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()
        getInternships()
        /*binding.rvInternViewInternships.setLayoutManager(LinearLayoutManager(applicationContext))
        // binding.rvList.setLayoutManager(GridLayoutManager(getApplicationContext(), 2))

        internInternshipAdapter = InternInternshipAdapter(applicationContext, internshipArrayList)
        binding.rvInternViewInternships.setAdapter(internInternshipAdapter)*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
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

                    internInternshipAdapter = InternInternshipAdapter(applicationContext, jobArrayList)
                    binding.rvInternViewInternships.setAdapter(internInternshipAdapter)
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

        binding.navInternSideMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_internHome -> {
                    val intent = Intent (this, InternMenu::class.java)
                    startActivity (intent)
                }

                R.id.nav_viewInternships -> {
                    val intent = Intent (this, InternViewInternships::class.java)
                    startActivity (intent)
                }

                R.id.nav_internPastInternships -> {
                    val intent = Intent (this, InternMyInternships::class.java)
                    startActivity (intent)
                }

                R.id.nav_viewCompanies -> {
                    val intent = Intent (this, InternViewCompanies::class.java)
                    startActivity (intent)
                }

                R.id.nav_internProfile -> {
                    val intent = Intent (this, InternProfile::class.java)
                    startActivity (intent)
                }

                R.id.nav_internLogout -> {
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