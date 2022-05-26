package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternInternshipDetails

class InternInternshipAdapter: RecyclerView.Adapter<InternInternshipAdapter.InternshipViewHolder> {
    private var internshipArrayList = ArrayList<Internship>()
    private lateinit var context: Context

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
            itemBinding.textCompany.text = internship.companyName
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
            bundle.putString("company", internship.companyName)

            goToInternship.putExtras(bundle)
            goToInternship.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToInternship)
        }
    }
}