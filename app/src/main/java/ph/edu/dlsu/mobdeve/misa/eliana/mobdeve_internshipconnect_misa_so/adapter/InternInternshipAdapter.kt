package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternInternshipDetails
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company

class InternInternshipAdapter: RecyclerView.Adapter<InternInternshipAdapter.InternshipViewHolder> {
    private var internshipArrayList = ArrayList<Internship>()
    private lateinit var context: Context
    private var firestore = Firebase.firestore


    public constructor(context: Context, internshipArrayList: ArrayList<Internship>) {
        this.context = context
        this.internshipArrayList = internshipArrayList
    }

    override fun getItemCount(): Int {
        return internshipArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InternInternshipAdapter.InternshipViewHolder {
        val itemBinding = ItemInternshipBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternshipViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternInternshipAdapter.InternshipViewHolder,
                                  position: Int) {
        holder.bindInternship(internshipArrayList[position])
    }

    inner class InternshipViewHolder(private val itemBinding: ItemInternshipBinding) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var internship = Internship()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindInternship(internship: Internship){
            this.internship = internship
            itemBinding.textTitle.text = internship.title
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type

            val companyID = internship.companyID

            if (companyID != null) {
                firestore.collection("Companies").document(companyID).get().addOnSuccessListener { document->
                    val company = document.toObject<Company>()
                    itemBinding.textCompany.text = company?.name
                }
            }
        }

        override fun onClick(p0: View?) {
            var goToInternship = Intent(context, InternInternshipDetails::class.java)
            var bundle = Bundle()

            bundle.putString("title", internship.title)
            bundle.putString("function", internship.function)
            bundle.putString("type", internship.type)
            bundle.putString("description", internship.description)
            bundle.putString("link", internship.link)
            bundle.putString("companyID", internship.companyID)



            goToInternship.putExtras(bundle)
            goToInternship.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToInternship)
        }
    }
}