package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternCompanyDetailsInternshipsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternInternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompaniesBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompanyBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternViewCompany : AppCompatActivity() {
    private lateinit var binding: ActivityInternViewCompanyBinding
    private lateinit var internViewCompanyInternshipsAdapter: InternCompanyDetailsInternshipsAdapter

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private lateinit var dbref: DatabaseReference
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        var bundle: Bundle = intent.extras!!
        binding.tvViewCompanyProfileCompanyName.text = bundle.getString("name")
        binding.tvViewCompanyProfileIndustry.text = bundle.getString("industry")
        binding.tvViewCompanyProfileLocation.text = bundle.getString("location")
        binding.tvViewCompanyProfileCompanyAboutText.text = bundle.getString("about")
        binding.tvViewCompanyProfileContactNumber.text = bundle.getString("number")
        binding.tvViewCompanyProfileWebsite.text = bundle.getString("website")

        val companyName = bundle.getString("name")
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
        companyDB.orderByChild("name").equalTo(companyName).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot:DataSnapshot) {
                dataSnapshot.children.forEach{
                        val companyID: String = it.key.toString()
                        getInternships(companyID)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


    private fun getInternships(companyID:String) {
        var internViewCompanyInternshipsArrayList = ArrayList<Internship>()
        Toast.makeText(this, companyID, Toast.LENGTH_SHORT)
        val internshipsDB = FirebaseDatabase.getInstance(dblink).getReference("Internships")

        internshipsDB.orderByChild("companyName").equalTo(companyID).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot:DataSnapshot) {
                if (snapshot.exists()) {
                    for (internshipSnapshot in snapshot.children) {
                        val dbInternship = internshipSnapshot.getValue(Internship::class.java)
                        var internship = Internship(dbInternship?.companyName.toString(),
                            dbInternship?.title.toString(), dbInternship?.description.toString(),
                            dbInternship?.function.toString(), dbInternship?.type.toString(), dbInternship?.link.toString())
                        internViewCompanyInternshipsArrayList.add(internship!!)
                    }
                }
                binding.rvInternViewCompanyInternships.setLayoutManager(LinearLayoutManager(applicationContext))
                internViewCompanyInternshipsAdapter = InternCompanyDetailsInternshipsAdapter(applicationContext, internViewCompanyInternshipsArrayList)
                binding.rvInternViewCompanyInternships.setAdapter(internViewCompanyInternshipsAdapter)
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