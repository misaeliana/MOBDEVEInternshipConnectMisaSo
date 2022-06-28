package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_company_profile.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyProfileBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanyProfile : AppCompatActivity() {

    private lateinit var binding:ActivityCompanyProfileBinding
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private var firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sidebar()

        getMyCompanyData()

        binding.btnCompanyProfileEdit.setOnClickListener {
            val intent = Intent (this, CompanyEditProfile::class.java)
            startActivity (intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun getMyCompanyData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        firestore.collection("Companies").document(currentUser).get().addOnSuccessListener { document  ->
            if (document != null) {
                var company = document.toObject(Company::class.java)
                if (company?.name != null)
                    binding.tvCompanyProfileCompanyName.text = company?.name.toString()
                if (company?.industry != null)
                    binding.tvCompanyProfileIndustry.text = company?.industry.toString()
                if (company?.location != null)
                    binding.tvCompanyProfileLocation.text = company?.location.toString()
                if (company?.about != null)
                    binding.tvCompanyProfileCompanyAboutText.text = company?.about.toString()
                if (company?.number != null)
                    binding.tvCompanyProfileContactNumber.text = company?.number.toString()
                    binding.tvCompanyProfileEmail.text = FirebaseAuth.getInstance().currentUser!!.email
                if (company?.website != null)
                    binding.tvCompanyProfileWebsite.text = company?.website.toString()
                //binding.tvCompanyProfileVideo.text = it.child("learnMore").value.toString()
            }
            else
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
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