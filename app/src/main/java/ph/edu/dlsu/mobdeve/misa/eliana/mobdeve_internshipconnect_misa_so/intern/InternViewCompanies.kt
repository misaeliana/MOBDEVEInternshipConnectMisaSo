package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompaniesBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company


class InternViewCompanies : AppCompatActivity() {
    private lateinit var binding: ActivityInternViewCompaniesBinding
    private var internCompaniesArrayList =  ArrayList<Company>()
    private var filteredCompanies = ArrayList<Company>()
    private lateinit var companyAdapter:CompanyAdapter

    private var firestore = Firebase.firestore

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    private lateinit var industries:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewCompaniesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        industries = resources.getStringArray(R.array.industry_list)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.industry_list))
        binding.spinnerIndustry.adapter = adapter


        getCompanyData()

                binding.spinnerIndustry.onItemSelectedListener = object:
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedIndustry = industries[position]
                        val itemCount = internCompaniesArrayList.size

                        for (i in 0 until itemCount) {
                            val holder = binding.rvCompanyList.findViewHolderForAdapterPosition(i)

                            if (holder != null) {
                                holder.itemView.visibility = View.VISIBLE
                                holder.itemView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                                holder.itemView.layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT
                            }

                        }

                            if (selectedIndustry != "All") {
                                for (j in 0 until itemCount) {
                                    val holder = binding.rvCompanyList.findViewHolderForAdapterPosition(j)
                                    println(holder!=null)
                                    if (holder != null) {
                                        val industry = holder.itemView.findViewById<View>(R.id.tv_industry) as TextView
                                        if (selectedIndustry != industry.text) {
                                            holder.itemView.visibility = View.GONE
                                            holder.itemView.layoutParams.height = 0
                                            holder.itemView.layoutParams.width = 0
                                        }
                                    }
                                }
                            }
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

    private fun getCompanyData() {

        firestore.collection("Companies").get().addOnSuccessListener { documents ->

            for (companySnapshot in documents) {
                //creating the object from list retrieved in db
                    val company = companySnapshot.toObject<Company>()
                internCompaniesArrayList.add(company!!)
                filteredCompanies.add(company!!)
            }
            //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
            binding.rvCompanyList.setLayoutManager(LinearLayoutManager(applicationContext))
            companyAdapter = CompanyAdapter(applicationContext, filteredCompanies)
            binding.rvCompanyList.setAdapter(companyAdapter)
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