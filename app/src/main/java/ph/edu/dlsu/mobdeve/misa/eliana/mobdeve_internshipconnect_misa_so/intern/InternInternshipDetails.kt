package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.R
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternEditProfileBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternInternshipDetailsBinding

class InternInternshipDetails : AppCompatActivity() {

    private lateinit var binding : ActivityInternInternshipDetailsBinding

    lateinit var toggle: androidx.appcompat.app.ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternInternshipDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sidebar()

        var bundle: Bundle = intent.extras!!
        binding.tvInternshipDetailsTitle.text = bundle.getString("title")
        binding.tvInternshipDetailsFunction.text = bundle.getString("function")
        binding.tvInternshipDetailsType.text = bundle.getString("type")
        binding.tvInternshipDetailsDescription.text = bundle.getString("description")
        binding.tvInternshipDetailsLink.text = bundle.getString("link")
        binding.tvInternshipDetailsCompany.text = bundle.getString("company")

        var source = bundle.getString("source")

        if (source == "myInternships") {
            binding.btnTvInternshipDetailsApply.visibility = View.GONE
            binding.btnTvInternshipDetailsFeedback.visibility = View.VISIBLE
        } else {
            binding.btnTvInternshipDetailsApply.visibility = View.VISIBLE
            binding.btnTvInternshipDetailsFeedback.visibility = View.GONE
        }

        binding.btnTvInternshipDetailsFeedback.setOnClickListener {
            val intent = Intent (this, InternFeedback::class.java)
            startActivity (intent)
        }

        binding.btnTvInternshipDetailsApply.setOnClickListener {
            val intent = Intent (this, InternMyInternships::class.java)
            startActivity (intent)
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