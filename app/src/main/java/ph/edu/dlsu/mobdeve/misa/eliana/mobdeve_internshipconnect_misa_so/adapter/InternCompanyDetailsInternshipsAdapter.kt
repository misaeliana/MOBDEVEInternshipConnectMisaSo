package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemCompanyInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.intern.InternInternshipDetails
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
        return InternCompanyInternshipsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternCompanyDetailsInternshipsAdapter.InternCompanyInternshipsViewHolder,
                                  position: Int) {
        holder.bindInternship(internCompanyInternshipsArrayList[position])
    }

    inner class InternCompanyInternshipsViewHolder(private val itemBinding: ItemCompanyInternshipBinding) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        var internship = Internship()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindInternship(internship: Internship){
            this.internship = internship
            itemBinding.textTitle.text = internship.title
            itemBinding.textFunction.text = internship.function
            itemBinding.textType.text = internship.type
        }

        override fun onClick(p0: View?) {
            var goToInternship = Intent(context, InternInternshipDetails::class.java)
            var bundle = Bundle()

            bundle.putString("title", internship.title)
            bundle.putString("function", internship.function)
            bundle.putString("type", internship.type)
            bundle.putString("description", internship.description)
            bundle.putString("link", internship.link)

            goToInternship.putExtras(bundle)
            goToInternship.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToInternship)
        }
    }
}