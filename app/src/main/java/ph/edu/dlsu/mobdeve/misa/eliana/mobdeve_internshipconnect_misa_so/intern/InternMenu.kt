package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_intern_menu.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.MainActivity
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternMenuBinding

class InternMenu : AppCompatActivity() {
    private lateinit var binding : ActivityInternMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btn_internMenuCompanies.setOnClickListener() {
            val intent = Intent (this, InternViewCompanies::class.java)
            startActivity (intent)
        }

        binding.btnInternMenuLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent (this, MainActivity::class.java)
            startActivity (intent)
            finish()
        }
    }
}