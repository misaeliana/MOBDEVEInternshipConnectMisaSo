package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyEditProfileBinding

class CompanyEditProfile : AppCompatActivity() {
    private lateinit var binding : ActivityCompanyEditProfileBinding
    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCompanyData()
        sidebar()

        binding.btnEditCompanyCancel.setOnClickListener {
            val intent = Intent (this, CompanyProfile::class.java)
            startActivity (intent)
        }

        binding.btnEditCompanySave.setOnClickListener {
            updateCompanyData()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun getCompanyData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
        companyDB.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                binding.etEditCompanyCompanyName.setText(it.child("name").value?.toString())
                binding.etEditCompanyLocation.setText(it.child("location").value?.toString())
                binding.etEditCompanyAbout.setText(it.child("about").value?.toString())
                binding.etEditCompanyContactNumber.setText(it.child("number").value?.toString())
                binding.etEditCompanyWebsite.setText(it.child("website").value?.toString())
            }
        }
    }

    private fun updateCompanyData() {
        val name = binding.etEditCompanyCompanyName.text.toString()
        val industry = binding.spinnerIndustry.selectedItem.toString()
        val number = binding.etEditCompanyContactNumber.text.toString()
        val about = binding.etEditCompanyAbout.text.toString()
        val location = binding.etEditCompanyLocation.text.toString()
        val website = binding.etEditCompanyWebsite.text.toString()
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid

        val companyDB = FirebaseDatabase.getInstance(dblink).getReference("Companies")
        val company = mapOf<String, String>(
            "name" to name,
            "industry" to industry,
            "location" to location,
            "about" to about,
            "number" to number,
            "website" to website
        )

        companyDB.child(currentUser).updateChildren(company).addOnSuccessListener {
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT)
            val intent = Intent (this, CompanyProfile::class.java)
            startActivity (intent)
            finish()
        }.addOnFailureListener{
            Toast.makeText(this, "Profile updated failed", Toast.LENGTH_SHORT)
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