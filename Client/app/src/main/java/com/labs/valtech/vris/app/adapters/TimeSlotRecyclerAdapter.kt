package com.labs.valtech.vris.app.adapters

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.Query
import com.labs.valtech.vris.R
import com.labs.valtech.vris.business.factories.DataModel.IDataModelFactory
import com.labs.valtech.vris.models.ITimeslot
import org.joda.time.DateTime

/**
 * Created by marvin.brouwer on 29-12-2017.
 */
class TimeSlotRecyclerAdapter(
        private val _context: Context,
        options: FirebaseRecyclerOptions<ITimeslot>) :FirebaseRecyclerAdapter<ITimeslot, RecyclerView.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Create a new instance of the ViewHolder, in this case we are using a custom
        // layout called R.layout.message for each item
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_timeslot, parent, false)

        return object: RecyclerView.ViewHolder(view) { }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, model: ITimeslot) {

        val now = DateTime.now().toLocalDateTime()
        val isActive = model.active(now!!)

        val timeFormat = _context.getString(R.string.timeFormat)
        var time = model.startDate.toString(timeFormat)
        if(model.endDate != null) time += " - ${model.endDate!!.toString(timeFormat)}"

        holder.itemView.findViewById<TextView>(R.id.timeslotCaption).setText(model.caption)
        holder.itemView.findViewById<TextView>(R.id.timeslotTime).setText(time)

        holder.itemView.findViewById<TextView>(R.id.timeslotCaption).setTypeface(null, if (isActive) Typeface.BOLD else Typeface.NORMAL);
        holder.itemView.findViewById<TextView>(R.id.timeslotTime).setTypeface(null, if (isActive) Typeface.BOLD else Typeface.NORMAL)
    }

    // todo filter only on today?
    constructor(context: Context, roomsQuery: Query, dataModelFactory: IDataModelFactory): this(context, FirebaseRecyclerOptions.Builder<ITimeslot>()
        .setQuery(roomsQuery, fun(dataSnapshot: DataSnapshot) =  dataModelFactory.createTimeSlot(dataSnapshot) )
        .build())
}