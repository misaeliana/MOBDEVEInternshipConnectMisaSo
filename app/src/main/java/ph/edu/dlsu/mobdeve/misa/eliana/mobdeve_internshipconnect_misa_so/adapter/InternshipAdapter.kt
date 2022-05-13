package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship2

class InternshipAdapter: RecyclerView.Adapter<InternshipAdapter.InternshipViewHolder> {
    private var internshipArrayList = ArrayList<Internship2>()
    private lateinit var context: Context

    public constructor(context: Context, internshipArrayList: ArrayList<Internship2>) {
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

        fun bindInternship(internship: Internship2){
            itemBinding.textTitle.text = internship.title
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }
    }
}