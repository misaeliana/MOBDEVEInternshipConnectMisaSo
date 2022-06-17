package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternUpdateProfileExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternProfileExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyJobListing
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyMenu
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyProfile
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternEditProfileBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class InternEditProfile : AppCompatActivity() {

    private lateinit var binding : ActivityInternEditProfileBinding

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private var firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getInternData()
        sidebar()

        binding.btnEditInternCancel.setOnClickListener {
            val intent = Intent (this, InternProfile::class.java)
            startActivity (intent)
        }

        binding.btnEditInternSave.setOnClickListener {
            updateInternProfile()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    fun getInternData() {
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid
        firestore.collection("Interns").document(currentUser).get().addOnSuccessListener { documentSnapshot ->
            var intern = documentSnapshot.toObject<Intern>()
            binding.etEditInternName.setText(intern?.name.toString())
            binding.etEditInternContactNumber.setText(intern?.number.toString())
            binding.etEditInternAbout.setText(intern?.about.toString())
            binding.etEditInternSchool.setText(intern?.school.toString())
            binding.etEditInternCourse.setText(intern?.course.toString())
            binding.etEditInternGradYear.setText(intern?.gradYear.toString())
        }
    }

    private fun updateInternProfile() {
        val name = binding.etEditInternName.text.toString()
        val number = binding.etEditInternContactNumber.text.toString()
        val about = binding.etEditInternAbout.text.toString()
        val school = binding.etEditInternSchool.text.toString()
        val course = binding.etEditInternCourse.text.toString()
        val gradYear = binding.etEditInternGradYear.text.toString()
        val currentUser:String = FirebaseAuth.getInstance().currentUser!!.uid

        val intern = mapOf<String, String>(
            "name" to name,
            "number" to number,
            "about" to about,
            "school" to school,
            "course" to course,
            "gradYear" to gradYear
        )

        firestore.collection("Interns").document(currentUser).set(intern, SetOptions.merge()).addOnSuccessListener {
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT)
            val intent = Intent (this, InternProfile::class.java)
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