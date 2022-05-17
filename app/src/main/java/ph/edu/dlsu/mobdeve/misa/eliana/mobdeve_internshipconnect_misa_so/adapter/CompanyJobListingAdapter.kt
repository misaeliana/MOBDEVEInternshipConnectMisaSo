package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemCompanyInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

class CompanyJobListingAdapter: RecyclerView.Adapter<CompanyJobListingAdapter.CompanyInternshipViewHolder> {
    private var companyInternshipArrayList = ArrayList<Internship>()
    private lateinit var context: Context

    public constructor(context: Context, companyInternshipArrayList:ArrayList<Internship>) {
        this.context = context
        this.companyInternshipArrayList = companyInternshipArrayList
    }

    override fun getItemCount(): Int {
        return companyInternshipArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanyJobListingAdapter.CompanyInternshipViewHolder {
        val itemBinding = ItemCompanyInternshipBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return CompanyJobListingAdapter.CompanyInternshipViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanyJobListingAdapter.CompanyInternshipViewHolder,
                                  position: Int) {
        holder.bindCompanyInternship(companyInternshipArrayList[position])
    }

    class CompanyInternshipViewHolder(private val itemBinding: ItemCompanyInternshipBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindCompanyInternship(internship: Internship){
            itemBinding.textTitle.text = internship.title
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }
    }
}