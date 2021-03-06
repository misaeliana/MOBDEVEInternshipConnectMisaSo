package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btn_register.setOnClickListener {
            val intent = Intent (this, Register::class.java)
            startActivity (intent)
        }

        btn_login.setOnClickListener{
            val intent = Intent (this, Login::class.java)
            startActivity (intent)
        }
    }
}