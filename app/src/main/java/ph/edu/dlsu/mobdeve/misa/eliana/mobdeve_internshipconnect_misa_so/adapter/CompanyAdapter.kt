package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemCompanyBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternViewCompany

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

    inner class CompanyViewHolder(private val itemBinding:ItemCompanyBinding) :RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        var company = Company()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindCompany(company:Company) {
            this.company = company
            itemBinding.tvCompanyName.text = company.name
            itemBinding.tvIndustry.text = company.industry
            itemBinding.tvLocation.text = company.location
        }

        override fun onClick(p0: View?) {
            var goToCompany = Intent(context, InternViewCompany::class.java)
            var bundle = Bundle()

            bundle.putString("name", company.name)
            bundle.putString("industry", company.industry)
            bundle.putString("location", company.location)
            bundle.putString("about", company.about)
            bundle.putString("number", company.number)
            bundle.putString("website", company.website)
            bundle.putString("email", company.email)
            //add for video

            goToCompany.putExtras(bundle)
            goToCompany.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToCompany)
        }
    }
}