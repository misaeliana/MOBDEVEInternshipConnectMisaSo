package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternInternshipDetails
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternMyInternshipsAdapter: RecyclerView.Adapter<InternMyInternshipsAdapter.InternMyInternshipViewHolder> {
    private var internMyInternshipsArrayList = ArrayList<Internship>()
    private lateinit var context: Context

    private var firestore = Firebase.firestore


    public constructor(context: Context, internMyInternshipsArrayList: ArrayList<Internship>) {
        this.context = context
        this.internMyInternshipsArrayList = internMyInternshipsArrayList
    }

    override fun getItemCount(): Int {
        return internMyInternshipsArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InternMyInternshipsAdapter.InternMyInternshipViewHolder {
        val itemBinding = ItemInternshipBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternMyInternshipViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternMyInternshipsAdapter.InternMyInternshipViewHolder,
                                  position: Int) {
        holder.bindInternship(internMyInternshipsArrayList[position])
    }

    inner class InternMyInternshipViewHolder(private val itemBinding: ItemInternshipBinding)
        : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var internship = Internship()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindInternship(internship: Internship){
            this.internship = internship
            itemBinding.textTitle.text = internship.title
            itemBinding.textCompany.text = internship.companyID
            itemBinding.textFunctionType.text = internship.function + ", " + internship.type
        }

        override fun onClick(p0: View?) {
            var goToInternship = Intent(context, InternInternshipDetails::class.java)
            var bundle = Bundle()

            bundle.putString("title", internship.title)
            bundle.putString("function", internship.function)
            bundle.putString("type", internship.type)
            bundle.putString("description", internship.description)
            bundle.putString("link", internship.link)
            bundle.putString("company", internship.companyID)
            bundle.putString("source", "myInternships")

            goToInternship.putExtras(bundle)
            goToInternship.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToInternship)
        }
    }
}