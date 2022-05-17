package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternMyInternshipsAdapter: RecyclerView.Adapter<InternMyInternshipsAdapter.InternMyInternshipViewHolder> {
    private var internMyInternshipsArrayList = ArrayList<Internship>()
    private lateinit var context: Context

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
        return InternMyInternshipsAdapter.InternMyInternshipViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternMyInternshipsAdapter.InternMyInternshipViewHolder,
                                  position: Int) {
        holder.bindInternship(internMyInternshipsArrayList[position])
    }

    class InternMyInternshipViewHolder(private val itemBinding: ItemInternshipBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindInternship(internship: Internship){
            itemBinding.textTitle.text = internship.title
            itemBinding.textCompany.text = internship.companyName
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }
    }
}