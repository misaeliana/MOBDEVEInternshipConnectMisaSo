package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemCompanyBinding

class CompanyAdapter: RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private var companyArrayList = ArrayList<Company>()
    private lateinit var context:Context

    public constructor(context: Context, companyArrayList:ArrayList<Company>) {
        this.context = context
        this.companyArrayList = companyArrayList
    }

    override fun getItemCount(): Int {
        return companyArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanyAdapter.CompanyViewHolder {
        val itemBinding = ItemCompanyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindCompany(companyArrayList[position])

    }

    class CompanyViewHolder(private val itemBinding:ItemCompanyBinding)
        :RecyclerView.ViewHolder(itemBinding.root){

        fun bindCompany(company:Company) {
            itemBinding.tvCompanyName.text = company.name
            itemBinding.tvIndustry.text = company.industry
            itemBinding.tvLocation.text = company.location
        }
    }
}