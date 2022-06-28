package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemExperienceUpdateBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class InternUpdateProfileExperienceAdapter: RecyclerView.Adapter<InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder> {
    private var internUpdateExperienceArrayList = ArrayList<Experience>()
    private lateinit var context: Context

    public constructor(context: Context, internUpdateExperienceArrayList: ArrayList<Experience>) {
        this.context = context
        this.internUpdateExperienceArrayList = internUpdateExperienceArrayList
    }

    fun removeExperience(position: Int){
        internUpdateExperienceArrayList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    fun addExperience(experience: Experience){
        internUpdateExperienceArrayList.add(0, experience)
        notifyItemInserted(0)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return internUpdateExperienceArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder {
        val itemBinding = ItemExperienceUpdateBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternUpdateExperienceViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder,
                                  position: Int) {
        holder.bindExperience(internUpdateExperienceArrayList[position])
    }

    inner class InternUpdateExperienceViewHolder(private val itemBinding: ItemExperienceUpdateBinding)
        : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        fun bindExperience(experience: Experience){
            itemBinding.tvExperienceTitle.text = experience.title
            itemBinding.tvExperienceCompanyName.text = experience.companyName
            itemBinding.tvExperienceDuration.text = experience.startDate +" - " + experience.endDate

            itemBinding.btnUpdateExperienceDelete.setOnClickListener{removeExperience(position)}
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }
}