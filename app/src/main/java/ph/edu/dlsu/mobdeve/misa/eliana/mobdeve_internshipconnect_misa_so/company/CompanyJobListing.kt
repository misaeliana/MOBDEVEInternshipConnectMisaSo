package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.CompanyJobListingAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityCompanyJobListingBinding

class CompanyJobListing : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyJobListingBinding
    private lateinit var companyJobListingAdapter: CompanyJobListingAdapter
    private lateinit var companyinternshipArrayList: ArrayList<Internship>

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

//    private lateinit var dbref: DatabaseReference
//    private var dblink:String ="https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyJobListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        sidebar()

        binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))

        companyJobListingAdapter = CompanyJobListingAdapter(applicationContext, companyinternshipArrayList)
        binding.rvJobListing.setAdapter(companyJobListingAdapter)

        //getCompanyJobListing()

        binding.btnAddInternship.setOnClickListener{
            val intent = Intent (this, AddInternship::class.java)
            startActivity (intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        var dao: InternshipsDAO = InternshipsDAOArrayImpl()

        var companyInternship1 = Internship()
        companyInternship1.companyName = "Procter & Gamble"
        companyInternship1.title = "Data Science Intern for Supply Chain"
        companyInternship1.description = "Do you want to work on iconic brands like Ariel, Pampers, Gillette, Head & Shoulders or Oral-B, and belong to one of the most advanced Data Science & Analytics group in the industry, developing sophisticated mathematical models and algorithms and applying Operations Research techniques?\n" +
                                        "\n" +
                                        "By joining P&G, you will be given responsibilities as of Day 1. You will build your Data Science and Analytics skills, develop your ability to influence and think strategically and learn how to work across domains and functions to make P&G’s supply chain more performant, more resilient and more sustainable. You will evolve in a dynamic and supporting work environment where employees are at the core. We value every individual and encourage initiatives, promoting agility and work/life balance."
        companyInternship1.function = "Technology/Software Development"
        companyInternship1.type = "Full-time"
        companyInternship1.link = "www.youtube.com/watch?v=1ial3aeA9SE"
        dao.addInternship(companyInternship1)

        var companyInternship2 = Internship()
        companyInternship2.companyName = "Procter & Gamble"
        companyInternship2.title = "DATA SCIENCE MANAGEMENT INTERN - P&G SUMMER 2022"
        companyInternship2.description = "Are you a data-savvy strategic player who understands technology and wants to be immersed in the day-to-day business with planning, product, sales and marketing? Are you ready to make a lasting impact by solving critical business questions for a global industry leader using data, science and technology? P&G Data Science team is looking for a curious and confident soul who loves cutting-edge technologies, has a solid foundation on computer science, statistics or mathematics, excellent in communicating, and passionate to lead and make things happen!"
        companyInternship2.function = "Technology/Software Development"
        companyInternship2.type = "Full-time"
        companyInternship2.link = "www.youtube.com/watch?v=1ial3aeA9SE"
        dao.addInternship(companyInternship2)

        var companyInternship3 = Internship()
        companyInternship3.companyName = "Procter & Gamble"
        companyInternship3.title = "MARKETING TECHNOLOGIST MANAGEMENT INTERN"
        companyInternship3.description = "Are you a business-savvy strategic player who, understands technology and wants to be involved with planning, product development, sales and marketing? We are looking for someone with a deep understanding of digital marketing and eCommerce! A Marketing Technologist who possesses technical skills that can be applied in our digital business programs to enable our brands to successfully DTC, CRM, eCommerce, Search and Media performance marketing programs. Our roles are critical enablers at the intersection of marketing, data, and analytics!"
        companyInternship3.function = "Marketing"
        companyInternship3.type = "Part-time"
        companyInternship3.link = "www.youtube.com/watch?v=1ial3aeA9SE"
        dao.addInternship(companyInternship3)

        var companyInternship4 = Internship()
        companyInternship4.companyName = "Procter & Gamble"
        companyInternship4.title = "IT Managerial Summer Internship 2022"
        companyInternship4.description = "Are you willing to step into the shoes of an IT manager and help bring groundbreaking, technology-based capabilities to life? Do you want to combine your studies with work experience in a dynamic IT organization?\n" +
                "\n" +
                "Apply for a unique summer work experience, to join our diverse team of women and men - IT professionals using both IT and business skills every single day.\n" +
                "\n" +
                "The IT solutions we build help move millions of trucks efficiently, crunch data to deliver business insights, allow our automated manufacturing plants to run robustly, aid in design of new products and thousands of store shelves, provide immersive virtual reality experiences for product and store testing, help our sales force run their work in the field with mobile solutions, support millions of payments and reports, lead consumer communication in the Web, ensure cybersecurity, enable high quality video teamwork and more."
        companyInternship4.function = "Technology/Software Development"
        companyInternship4.type = "Project-based"
        companyInternship4.link = "www.youtube.com/watch?v=1ial3aeA9SE"
        dao.addInternship(companyInternship4)

        var companyInternship5 = Internship()
        companyInternship5.companyName = "Procter & Gamble"
        companyInternship5.title = "Finance Internship (m/f/d)"
        companyInternship5.description = "Are you looking to apply your finance knowledge to practice? Are you eager to lead exciting projects and have a meaningful impact with your ideas? Are you ready to sustainably shape the strategies behind our leading brands? If you relish a culture in which your development is key and you feel a hunger to learn, a dedication to lead, and a passion to win – this position is for you!"
        companyInternship5.function = "Finance"
        companyInternship5.type = "Full-time"
        companyInternship5.link = "www.youtube.com/watch?v=1ial3aeA9SE"
        dao.addInternship(companyInternship5)

        companyinternshipArrayList = dao.getInternships()
    }

//    private fun getCompanyJobListing() {
//        var jobArrayList = ArrayList<Internship>()
//
//        dbref = FirebaseDatabase.getInstance(dblink).getReference("Internships")
//        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
//        dbref.orderByChild("companyName").equalTo(currentUser).addValueEventListener(object: ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (companySnapshot in snapshot.children) {
//                        //creating the object from list retrieved in db
//                        val job = companySnapshot.getValue(Internship::class.java)
//                        jobArrayList.add(job!!)
//                    }
//                    //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
//                    binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))
//
//                    companyInternInternshipAdapter = InternInternshipAdapter(applicationContext, jobArrayList)
//                    binding.rvJobListing.setAdapter(companyInternInternshipAdapter)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//
//            /*dbref.addValueEventListener(object: ValueEventListener {
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        for (companySnapshot in snapshot.children) {
//                            //creating the object from list retrieved in db
//                            val job = companySnapshot.getValue(Internship::class.java)
//                            jobArrayList.add(job!!)
//                        }
//                        //rv_companyList.adapter = CompanyAdapter(applicationContext, companyArrayList)
//                        binding.rvJobListing.setLayoutManager(LinearLayoutManager(applicationContext))
//
//                        jobListingAdapter = JobAdapter(applicationContext, jobArrayList)
//                        binding.rvJobListing.setAdapter(jobListingAdapter)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })*/
//    }

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