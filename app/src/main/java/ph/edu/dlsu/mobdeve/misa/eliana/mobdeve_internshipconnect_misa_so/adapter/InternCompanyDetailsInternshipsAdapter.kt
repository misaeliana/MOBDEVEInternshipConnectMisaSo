package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemCompanyInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class InternCompanyDetailsInternshipsAdapter: RecyclerView.Adapter<InternCompanyDetailsInternshipsAdapter.InternCompanyInternshipsViewHolder> {
    private var internCompanyInternshipsArrayList = ArrayList<Internship>()
    private lateinit var context: Context

    public constructor(context: Context, internCompanyInternshipsArrayList: ArrayList<Internship>) {
        this.context = context
        this.internCompanyInternshipsArrayList = internCompanyInternshipsArrayList
    }

    override fun getItemCount(): Int {
        return internCompanyInternshipsArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InternCompanyDetailsInternshipsAdapter.InternCompanyInternshipsViewHolder {
        val itemBinding = ItemCompanyInternshipBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternCompanyDetailsInternshipsAdapter.InternCompanyInternshipsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternCompanyDetailsInternshipsAdapter.InternCompanyInternshipsViewHolder,
                                  position: Int) {
        holder.bindInternship(internCompanyInternshipsArrayList[position])
    }

    class InternCompanyInternshipsViewHolder(private val itemBinding: ItemCompanyInternshipBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindInternship(internship: Internship){
            itemBinding.textTitle.text = internship.title
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }
    }
}