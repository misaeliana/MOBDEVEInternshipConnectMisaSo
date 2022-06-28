package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternUpdateProfileExperienceAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.CompaniesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.CompaniesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.ExperiencesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMenuBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternUpdateExperienceBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class InternUpdateExperience : AppCompatActivity() {
    private lateinit var binding : ActivityInternUpdateExperienceBinding
    private lateinit var internUpdateExperienceAdapter: InternUpdateProfileExperienceAdapter
    private var internUpdateExperienceArrayList = ArrayList<Experience>()
    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private var firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternUpdateExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()
        getInternExperience()

        binding.btnUpdateExperienceAdd.setOnClickListener{
            var experience = Experience()

            experience.title = binding.etUpdateExperienceTitle.text.toString()
            experience.companyName = binding.etUpdateExperienceCompanyName.text.toString()
            experience.internID =  FirebaseAuth.getInstance().currentUser!!.uid
            experience.startDate = binding.etUpdateExperienceStartDate.text.toString()
            experience.endDate = binding.etUpdateExperienceEndDate.text.toString()

            internUpdateExperienceAdapter.addExperience(experience)
            //internUpdateExperienceArrayList.add(experience)

            //clear fields
            binding.etUpdateExperienceCompanyName.text.clear()
            binding.etUpdateExperienceTitle.text.clear()
            binding.etUpdateExperienceStartDate.text.clear()
            binding.etUpdateExperienceEndDate.text.clear()
        }

        binding.btnUpdateExperienceSave.setOnClickListener {
            updateExperience()
        }

        binding.btnUpdateExperienceCancel.setOnClickListener{
            val intent = Intent (this, InternProfile::class.java)
            startActivity (intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun updateExperience() {
        //delete previous experience first
        /*var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        firestore.collection("InternExperience").whereEqualTo("interID", currentUser).get().addOnSuccessListener { documents ->
            for (experience in documents) {


            }
        }*/

        GlobalScope.launch(Dispatchers.IO) {
            println("delete")
            deleteData()
            withContext(Dispatchers.Main) {
                for (experience in internUpdateExperienceArrayList) {
                    var experiencedb = mapOf(
                        "title" to experience.title,
                        "companyName" to experience.companyName,
                        "internID" to FirebaseAuth.getInstance().currentUser!!.uid,
                        "startDate" to experience.startDate,
                        "endDate" to experience.endDate
                    )
                    firestore.collection("Experience").add(experiencedb).addOnSuccessListener {
                    }
                    println("add")
                }
            }
        }

        val intent = Intent (this, InternProfile::class.java)
        startActivity (intent)
        finish()
    }

    private suspend fun deleteData() {
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        var currentExperience = firestore.collection("Experience").whereEqualTo("internID", currentUser).get().await()
        for (experience in currentExperience) {
            println(experience.id)
            firestore.collection("Experience").document(experience.id).delete().await()
        }
    }

    private fun getInternExperience() {
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        firestore.collection("Experience").whereEqualTo("internID", currentUser).get().addOnSuccessListener { documents ->

            for (companySnapshot in documents) {
                //creating the object from list retrieved in db
                    val experience = companySnapshot.toObject<Experience>()
                    internUpdateExperienceArrayList.add(experience!!)
                }

            binding.rvCompanyInternExperiences.setLayoutManager(LinearLayoutManager(applicationContext))
            internUpdateExperienceAdapter = InternUpdateProfileExperienceAdapter(applicationContext, internUpdateExperienceArrayList)
            binding.rvCompanyInternExperiences.setAdapter(internUpdateExperienceAdapter)
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