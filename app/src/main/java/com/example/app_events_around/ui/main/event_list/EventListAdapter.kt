package com.example.app_events_around.ui.main.event_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_events_around.R
import com.example.app_events_around.common.extensions.concatHttps
import com.example.app_events_around.common.extensions.convertToDateFormat
import com.example.app_events_around.common.extensions.convertToPtBrCurrency
import com.example.app_events_around.common.extensions.inflate
import com.example.app_events_around.domain.model.Event
import kotlinx.android.synthetic.main.view_item_event.view.*

class EventListAdapter(var callback: Callback) :
    RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    var events: List<Event> = listOf()

    override fun getItemCount(): Int = events.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(events[position])

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.view_item_event)) {
        fun bind(event: Event) {
            with(itemView) {
                tvTitle.text = event.title
                tvDescription.text = event.description
                tvDate.text = event.date.convertToDateFormat()
                tvPrice.text = event.price.convertToPtBrCurrency()
                Glide.with(this).load(event.image.concatHttps()).into(ivImage)
                setOnClickListener { callback.onClickEvent(event) }
            }
        }
    }

    interface Callback {
        fun onClickEvent(event: Event)
    }
}