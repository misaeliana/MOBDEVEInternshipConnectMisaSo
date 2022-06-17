package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternMyInternshipsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMyInternshipsBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.AppliedInternship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternMyInternships : AppCompatActivity() {
    private lateinit var binding : ActivityInternMyInternshipsBinding
    private lateinit var internMyInternshipsAdapter: InternMyInternshipsAdapter
    private var myInternshipArrayList = ArrayList<Internship>()
    var appliedInternshipArrayList = ArrayList<AppliedInternship>()
    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private var firestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternMyInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()
        getMyInternships()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun getMyInternships() {

                GlobalScope.launch(Dispatchers.IO) {
                    var finalArrayList = addData()

                    withContext(Dispatchers.Main) {
                        println("bind data")
                        binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
                        internMyInternshipsAdapter = InternMyInternshipsAdapter(applicationContext, finalArrayList)
                        binding.rvList.setAdapter(internMyInternshipsAdapter)
                    }
                }
    }

    private suspend fun addData():ArrayList<Internship> {
        println("in add data")
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        var fsAppliedInternship = firestore.collection("AppliedInternship").whereEqualTo("internID", currentUser).get().await()
            println(fsAppliedInternship.size())
            for (internship in fsAppliedInternship) {
                var appliedInternshipobj = internship.toObject<AppliedInternship>()
                appliedInternshipArrayList.add(appliedInternshipobj)
            }

        println(appliedInternshipArrayList.size)
        for (appliedInternship in appliedInternshipArrayList) {
            val internshipObj = firestore.collection("Internships")
                .document(appliedInternship.internshipID.toString()).get().await()
                .toObject<Internship>()
            myInternshipArrayList.add(internshipObj!!)
            println("add data")
        }
        return myInternshipArrayList
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