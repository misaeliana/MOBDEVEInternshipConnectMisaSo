package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternInternshipAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter.InternMyInternshipsAdapter
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAO
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao.InternshipsDAOArrayImpl
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMyInternshipsBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternMyInternships : AppCompatActivity() {
    private lateinit var binding : ActivityInternMyInternshipsBinding
    private lateinit var internMyInternshipsAdapter: InternMyInternshipsAdapter
    private lateinit var myInternshipArrayList: ArrayList<Internship>
    //private lateinit var internshipArrayList2: ArrayList<Internship2>
    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternMyInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        sidebar()
        binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
        // binding.rvList.setLayoutManager(GridLayoutManager(getApplicationContext(), 2))

        internMyInternshipsAdapter = InternMyInternshipsAdapter(applicationContext, myInternshipArrayList)
        binding.rvList.setAdapter(internMyInternshipsAdapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        var dao: InternshipsDAO = InternshipsDAOArrayImpl()

        var internship = Internship()

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

        myInternshipArrayList = dao.getInternships()
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