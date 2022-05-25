package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternMyInternshipsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.CompaniesDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.CompaniesDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternViewCompaniesBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternViewCompanies : AppCompatActivity() {
    private lateinit var binding: ActivityInternViewCompaniesBinding
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var internCompaniesArrayList: ArrayList<Company>

    private lateinit var dbref:DatabaseReference

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternViewCompaniesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        /*binding.rvCompanyList.setLayoutManager(LinearLayoutManager(applicationContext))
        // binding.rvList.setLayoutManager(GridLayoutManager(getApplicationContext(), 2))

        companyAdapter = CompanyAdapter(applicationContext, internCompaniesArrayList)
        binding.rvCompanyList.setAdapter(companyAdapter)*/

        getCompanyData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        var dao: CompaniesDAO = CompaniesDAOArrayImpl()

        var company = Company()

        company.name = "Procter & Gamble"
        company.about = "Procter & Gamble Company, major American manufacturer of soaps, cleansers, and other household products. Headquarters are in Cincinnati, Ohio. The company was formed in 1837 when William Procter, a British candlemaker, and James Gamble, an Irish soapmaker, merged their businesses in Cincinnati."
        company.email = "email@proctorgamble"
        company.industry = "Manufacturing"
        company.location = "11F, Seven/NEO, 5th Ave, Taguig, 1634 Metro Manila"
        company.number = "(02) 8558 8800"
        company.website = "https://ph.pg.com/"
        dao.addCompany(company)

        var company2 = Company()

        company2.name = "Shopee"
        company2.about = "Shopee is the leading e-commerce platform in Southeast Asia and Taiwan. It is a platform tailored for the region, providing customers with an easy, secure and fast online shopping experience through strong payment and logistical support."
        company2.email = "email@shopee"
        company2.industry = "E-commerce"
        company2.location = "Seven/NEO, 37th Floor, 5th Ave, Taguig, 1634"
        company2.number = "(02) 8880 5200"
        company2.website = "https://careers.shopee.ph/"
        dao.addCompany(company2)

        var company3 = Company()

        company3.name = "Amazon"
        company3.about = "Amazon.com, online retailer, manufacturer of electronic book readers, and Web services provider that became the iconic example of electronic commerce. Its headquarters are in Seattle, Washington."
        company3.email = "email@amazon"
        company3.industry = "E-commerce"
        company3.location = "Harbor Dr, Pasay, Metro Manila"
        company3.number = "22711438"
        company3.website = "https://www.amazon.jobs/en/"
        dao.addCompany(company3)

        var company4 = Company()

        company4.name = "Accenture"
        company4.about = "At the heart of every great change is a great human. Every day our People of Change are doing incredible things by working together to pursue our shared purpose–to deliver on the promise of technology and human ingenuity.\n" +
                "\n" +
                "Come be part of our team–bring your ideas, ingenuity and determination to make a difference, and we’ll solve some of the world’s biggest challenges."
        company4.email = "email@accenture"
        company4.industry = "Finance"
        company4.location = "MSE Building, Ayala Ave, Makati, 1200 Metro Manila"
        company4.number = "(02) 8558 8800"
        company4.website = "https://www.accenture.com/ph-en"
        dao.addCompany(company4)

        var company5 = Company()

        company5.name = "Shell"
        company5.about = "Shell is a global group of energy and petrochemical companies that aims to meet the world's growing need for more and cleaner energy solutions in ways that are economically, environmentally and socially responsible."
        company5.email = "email@proctorgamble"
        company5.industry = "Manufacturing"
        company5.location = "130 Dela Rosa Street, Legazpi Village, Makati, 1229 Kalakhang Maynila"
        company5.number = "(02) 3490 4000"
        company5.website = "https://www.shell.com/about-us.html"
        dao.addCompany(company5)

        internCompaniesArrayList = dao.getCompanies()
    }

    private fun getCompanyData() {
        var companyArrayList = ArrayList<Company>()

        dbref = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Companies")
        dbref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (companySnapshot in snapshot.children) {
                        //creating the object from list retrieved in db
                        val company = companySnapshot.getValue(Company::class.java)
                        companyArrayList.add(company!!)
                    }
                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvCompanyList.setLayoutManager(LinearLayoutManager(applicationContext))

                    companyAdapter = CompanyAdapter(applicationContext, companyArrayList)
                    binding.rvCompanyList.setAdapter(companyAdapter)
                }
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