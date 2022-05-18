package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship


class AddInternship : AppCompatActivity() {

    private lateinit var binding : ActivityAddInternshipBinding
    private lateinit var database : DatabaseReference
    private var dblink ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInternshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        binding.buttonAddInternship.setOnClickListener{
            val companyName  = FirebaseAuth.getInstance().currentUser!!.uid
            val positionTitle = binding.textPositionTitle.text.toString()
            val jobDesc = binding.textJobDescription.text.toString()
            //val function = binding.textFunction.toString() // removed .text first
            //val type = binding.textType.toString() // removed .text first
            //val benefits = binding.textBenefits.text.toString()
            val link = binding.textLink.text.toString()


            //getInstance defines the link of the db
            database = FirebaseDatabase.getInstance(dblink).getReference("Internships")
            val internship = Internship(companyName, positionTitle, jobDesc, link)
            database.push().setValue(internship)

            binding.textPositionTitle.text.clear()
            binding.textJobDescription.text.clear()
            //binding.textFunction.text.clear() COMMENTED OUT SINCE MAY ERROR
            //binding.textType.text.clear()
            //binding.textBenefits.text.clear()
            binding.textLink.text.clear()

            val intent = Intent (this, CompanyJobListing::class.java)
            startActivity (intent)
            finish()

            Toast.makeText(this, "Internship added", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
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