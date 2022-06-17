package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanySpecificJobPostingApplicantsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanySpecificJobPostingBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.AppliedInternship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class CompanySpecificJobPosting : AppCompatActivity() {
    private lateinit var binding: ActivityCompanySpecificJobPostingBinding
    private lateinit var companySpecificJobPostingAdapter: CompanySpecificJobPostingApplicantsAdapter
    private var companySpecificJobPostingApplicant = ArrayList<AppliedInternship>()
    private var companySpecificJobPostingArrayList = ArrayList<Intern>()

    private var firestore = Firebase.firestore

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanySpecificJobPostingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle: Bundle = intent.extras!!
        binding.tvInternshipDetailsTitle.text = bundle.getString("title")
        binding.tvInternshipDetailsFunction.text = bundle.getString("function")
        binding.tvInternshipDetailsType.text = bundle.getString("type")
        binding.tvInternshipDetailsDescription.text = bundle.getString("description")
        binding.tvInternshipDetailsLink.text = bundle.getString("link")

        getApplicantList(bundle.getString("title").toString())

        sidebar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun getApplicantList(title:String) {
        GlobalScope.launch(Dispatchers.IO) {
            var finalArrayList = getData(title)

            withContext(Dispatchers.Main) {
                println("binding")
                binding.rvCompanySpecificJobPostingApplicants.setLayoutManager(LinearLayoutManager(applicationContext))
                companySpecificJobPostingAdapter = CompanySpecificJobPostingApplicantsAdapter(applicationContext, finalArrayList)
                binding.rvCompanySpecificJobPostingApplicants.setAdapter(companySpecificJobPostingAdapter)
            }
        }
    }

    private suspend fun getData(title:String):ArrayList<Intern> {
        println("in get data")
        var internshipID = ""
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        //get internshipid based on company id and title
        var fsinternshipID = firestore.collection("Internships").whereEqualTo("companyID", currentUser).whereEqualTo("title", title).get().await()
            for (internship in fsinternshipID) {
                internshipID = internship.id
            }

        println(internshipID)
        //get interns who applied for position
        var fsExperience = firestore.collection("AppliedInternship").whereEqualTo("internshipID", internshipID).get().await()
                for (intern in fsExperience) {
                    var internobj = intern.toObject<AppliedInternship>()
                    companySpecificJobPostingApplicant.add(internobj!!)
                }
        println(companySpecificJobPostingApplicant.size)

                //get intern information
                for (applicant in companySpecificJobPostingApplicant) {
                    var internData = firestore.collection("Interns").document(applicant.internID.toString()).get().await().toObject<Intern>()
                    companySpecificJobPostingArrayList.add(internData!!)
                }
        return companySpecificJobPostingArrayList
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