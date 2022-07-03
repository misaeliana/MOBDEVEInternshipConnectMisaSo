package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
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
    private var internshipArrayList = ArrayList<Internship>()

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle
    private var firestore = Firebase.firestore

    private lateinit var functions:Array<String>
    private lateinit var types: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()
        getInternships()

        functions = resources.getStringArray(R.array.function_list)
        types  = resources.getStringArray(R.array.type_list)

        binding.spinnerFunction.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterInternships()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterInternships()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun getInternships() {

        firestore.collection("Internships").get().addOnSuccessListener { documents ->
            for (internshipSnapshot in documents) {
                //creating the object from list retrieved in db
                val job = internshipSnapshot.toObject<Internship>()
                internshipArrayList.add(job!!)
            }
            //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
            binding.rvInternViewInternships.setLayoutManager(LinearLayoutManager(applicationContext))

            internInternshipAdapter = InternInternshipAdapter(applicationContext, internshipArrayList)
            binding.rvInternViewInternships.setAdapter(internInternshipAdapter)
        }
    }

    private fun filterInternships() {
        var selectedFunction = functions[binding.spinnerFunction.selectedItemPosition]
        var selectedType = types[binding.spinnerType.selectedItemPosition]

        var itemCount = internshipArrayList.size

        for (i in 0 until itemCount) {
            val holder = binding.rvInternViewInternships.findViewHolderForAdapterPosition(i)

            if (holder != null) {
                holder.itemView.visibility = View.VISIBLE
                holder.itemView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                holder.itemView.layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT
            }

        }

        //only type has filter
        if (selectedFunction == "--Job Function--" && selectedType!= "--Type--") {
            for (i in 0 until itemCount) {
                val holder = binding.rvInternViewInternships.findViewHolderForAdapterPosition(i)
                if (holder != null) {
                    val type = holder.itemView.findViewById<View>(R.id.text_type) as TextView
                    if (selectedType != type.text) {
                        holder.itemView.visibility = View.GONE
                        holder.itemView.layoutParams.height = 0
                        holder.itemView.layoutParams.width = 0
                    }
                }
            }
        }
        //only function has filter
        else if (selectedFunction != "--Job Function--" && selectedType== "--Type--"){
            for (i in 0 until itemCount) {
                val holder = binding.rvInternViewInternships.findViewHolderForAdapterPosition(i)
                if (holder != null) {
                    val function = holder.itemView.findViewById<View>(R.id.text_function) as TextView
                    if (selectedFunction != function.text) {
                        holder.itemView.visibility = View.GONE
                        holder.itemView.layoutParams.height = 0
                        holder.itemView.layoutParams.width = 0
                    }

                }
            }
        }
        //both has filter
        else if (selectedFunction != "--Job Function--" && selectedType!= "--Type--"){
            for (i in 0 until itemCount) {
                val holder = binding.rvInternViewInternships.findViewHolderForAdapterPosition(i)
                if (holder != null) {
                    val function = holder.itemView.findViewById<View>(R.id.text_function) as TextView
                    val type = holder.itemView.findViewById<View>(R.id.text_type) as TextView
                    println(selectedFunction)
                    println(function.text)
                    println(selectedType)
                    println(type.text)
                    if (selectedFunction != function.text ||x selectedType != type.text) {
                        holder.itemView.visibility = View.GONE
                        holder.itemView.layoutParams.height = 0
                        holder.itemView.layoutParams.width = 0
                    }
                }
            }
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