package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternEditProfileBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityInternInternshipDetailsBinding

class InternInternshipDetails : AppCompatActivity() {

    private lateinit var binding : ActivityInternInternshipDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternInternshipDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle: Bundle = intent.extras!!
        binding.tvInternshipDetailsTitle.text = bundle.getString("title")
        binding.tvInternshipDetailsFunction.text = bundle.getString("function")
        binding.tvInternshipDetailsType.text = bundle.getString("type")
        binding.tvInternshipDetailsDescription.text = bundle.getString("description")
        binding.tvInternshipDetailsLink.text = bundle.getString("link")
    }
}