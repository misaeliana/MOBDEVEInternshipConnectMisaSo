package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemExperienceBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class InternProfileExperienceAdapter: RecyclerView.Adapter<InternProfileExperienceAdapter.InternExperienceViewHolder> {
    private var internExperienceArrayList = ArrayList<Experience>()
    private lateinit var context: Context

    public constructor(context: Context, internExperienceArrayList: ArrayList<Experience>) {
        this.context = context
        this.internExperienceArrayList = internExperienceArrayList
    }

    override fun getItemCount(): Int {
        return internExperienceArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InternProfileExperienceAdapter.InternExperienceViewHolder {
        val itemBinding = ItemExperienceBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternProfileExperienceAdapter.InternExperienceViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternProfileExperienceAdapter.InternExperienceViewHolder,
                                  position: Int) {
        holder.bindInternship(internExperienceArrayList[position])
    }

    class InternExperienceViewHolder(private val itemBinding: ItemExperienceBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindInternship(experience: Experience){
            itemBinding.tvExperienceTitle.text = experience.title
            itemBinding.tvExperienceCompanyName.text = experience.companyName
            itemBinding.tvExperienceDuration.text = experience.startDate +" - " + experience.endDate
        }
    }
}