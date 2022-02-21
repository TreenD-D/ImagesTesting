package ru.imagestestingapp.feature.patientslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.transform.RoundedCornersTransformation
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import pro.appcraft.lib.utils.common.setGone
import pro.appcraft.lib.utils.common.setVisible
import ru.imagestesting.domain.model.patients.PatientEntry
import ru.imagestestingapp.R
import java.time.format.DateTimeFormatter

class PatientItem(val entry: PatientEntry) :
    AbstractItem<PatientItem.ViewHolder>() {
    override val type: Int = R.id.itemPatient


    override val layoutRes: Int = R.layout.item_list_patient


    class ViewHolder(itemView: View) : FastAdapter.ViewHolder<PatientItem>(itemView) {
        private val patientText: TextView = itemView.findViewById(R.id.patientNameView)


        override fun bindView(item: PatientItem, payloads: List<Any>) {
            patientText.text =
                item.entry.lastName + " " + item.entry.name + " " + item.entry.thirdName + ", " + item.entry.birthDate
        }

        override fun unbindView(item: PatientItem) {
            patientText.text = null
        }
    }

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
}