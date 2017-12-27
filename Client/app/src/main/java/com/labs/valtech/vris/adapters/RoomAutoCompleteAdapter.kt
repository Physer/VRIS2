package com.labs.valtech.vris.adapters

import android.content.Context
import android.support.annotation.Nullable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.labs.valtech.vris.R
import com.labs.valtech.vris.models.IRoom
import com.labs.valtech.vris.models.Room
import me.xdrop.fuzzywuzzy.FuzzySearch


/**
 * Created by marvin.brouwer on 27-12-2017.
 */
class RoomAutoCompleteAdapter(private val context: Context, override val kodein: LazyKodein):BaseAdapter(), Filterable, LazyKodeinAware {

    val _roomsFirebase: DatabaseReference by kodein.with("Rooms").instance()

    @Volatile
    var _availableRooms: ArrayList<IRoom> = ArrayList()

    init {
        _roomsFirebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                _availableRooms.addAll(
                        dataSnapshot.children.map { child ->
                            {
                                val room = child.getValue<Room>(Room::class.java)!!
                                room.id = child.key;
                                room;
                            }()
                        }
                )
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Rooms", "Failed to read value.", error.toException());
            }
        })
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(@Nullable constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrBlank()) {
                    filterResults.values = ArrayList<IRoom>()
                    filterResults.count = 0
                    return filterResults
                }

                val rooms = findRooms(constraint!!.toString())
                filterResults.values = rooms
                filterResults.count = rooms.size

                return filterResults
            }

            override fun publishResults(@Nullable constraint: CharSequence?, results: FilterResults?) {
                if (constraint != null && results != null && results.count > 0) {
                    resultList = results.values as List<IRoom>
                    notifyDataSetChanged()
                } else {
                    resultList = ArrayList<IRoom>(0)
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    private var resultList: List<IRoom> = ArrayList<IRoom>()

    // Assign the data to the FilterResults


    override fun getCount(): Int {
        return resultList.size
    }

    override fun getItem(index: Int): IRoom {
        return resultList[index]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position);
        if(item == null) return convertView!!

        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView = inflater.inflate(R.layout.item_room, parent, false)
        val roomName = convertView!!.findViewById<TextView>(R.id.roomName)
        roomName.setText(item.getFullRoomName())
        return convertView!!
    }

    /**
     * Returns a search result for the given book title.
     */
    fun findRooms(query: String?): List<IRoom> {
        if(query.isNullOrBlank()) return ArrayList<IRoom>(0)

        var searchResults = FuzzySearch.extractTop(query, _availableRooms.map { room -> room.getFullRoomName() }.toHashSet(), MAX_RESULTS);
        return searchResults.map { result -> _availableRooms.first { room -> room.getFullRoomName().equals(result.string, false) } }
    }

    companion object {

        private val MAX_RESULTS = 10
    }
}