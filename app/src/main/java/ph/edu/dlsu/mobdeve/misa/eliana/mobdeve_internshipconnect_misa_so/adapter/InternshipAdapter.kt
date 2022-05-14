package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemInternshipBinding

class InternshipAdapter: RecyclerView.Adapter<InternshipAdapter.InternshipViewHolder> {
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
    ): InternshipAdapter.InternshipViewHolder {
        val itemBinding = ItemInternshipBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternshipViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternshipAdapter.InternshipViewHolder,
                                  position: Int) {
        holder.bindInternship(internshipArrayList[position])
    }

    class InternshipViewHolder(private val itemBinding: ItemInternshipBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindInternship(internship: Internship){
            itemBinding.textTitle.text = internship.title
            itemBinding.textCompany.text = internship.companyName
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }
    }
}