package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanySpecificJobPosting
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
        return CompanyInternshipViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanyJobListingAdapter.CompanyInternshipViewHolder,
                                  position: Int) {
        holder.bindCompanyInternship(companyInternshipArrayList[position])
    }

    inner class CompanyInternshipViewHolder(private val itemBinding: ItemCompanyInternshipBinding) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
        var internship  = Internship()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindCompanyInternship(internship: Internship){
            this.internship = internship
            itemBinding.textTitle.text = internship.title
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }

        override fun onClick(p0: View?) {
            var goToSpecificJobListing = Intent(context, CompanySpecificJobPosting::class.java)
            var bundle = Bundle()

            bundle.putString("title", internship.title)
            bundle.putString("function", internship.function)
            bundle.putString("type", internship.type)
            bundle.putString("description", internship.description)
            bundle.putString("link", internship.link)

            goToSpecificJobListing.putExtras(bundle)
            goToSpecificJobListing.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToSpecificJobListing)
        }
    }
}