package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ActivityAddInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.data.Internship


class AddInternship : AppCompatActivity() {

    private lateinit var binding : ActivityAddInternshipBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInternshipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddInternship.setOnClickListener{
            val positionTitle = binding.textPositionTitle.text.toString()
            val jobDesc = binding.textJobDescription.text.toString()
            val function = binding.textFunction.toString() // removed .text first
            val type = binding.textType.toString() // removed .text first
            //val benefits = binding.textBenefits.text.toString()
            val link = binding.textLink.text.toString()


            //getInstance defines the link of the db
            database = FirebaseDatabase.getInstance("https://mobdeve-internshipconnect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Internships")
            val internship = Internship(positionTitle, jobDesc, function, type, link)
            database.child(positionTitle).setValue(internship).addOnSuccessListener {

                binding.textPositionTitle.text.clear()
                binding.textJobDescription.text.clear()
                //binding.textFunction.text.clear() COMMENTED OUT SINCE MAY ERROR
                //binding.textType.text.clear()
                //binding.textBenefits.text.clear()
                binding.textLink.text.clear()

                val intent = Intent (this, CompanyJobListing::class.java)
                startActivity (intent)
                finish()

                Toast.makeText(this, "Internship added", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Execution Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}