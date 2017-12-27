package com.labs.valtech.vris.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.labs.valtech.vris.R
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.models.ITimeslot
import com.labs.valtech.vris.models.Timeslot
import com.labs.valtech.vris.business.repositories.Settings.ISettingRepository
import com.labs.valtech.vris.viewModels.RoomViewModel
import kotlinx.android.synthetic.main.activity_room.*
import org.joda.time.DateTime
import org.joda.time.LocalDateTime




/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class RoomActivity : BaseActivity<RoomViewModel>() {

    val _settingRepository: ISettingRepository by instance()
    val _roomsFirebase: DatabaseReference by kodein.with("Rooms").instance()
    @Volatile var _timeslots: ArrayList<ITimeslot> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setModel(this, R.layout.activity_room, RoomViewModel(_settingRepository.Room!!))
    }

    override fun onStart() {
        if(_settingRepository.Room == null)
            navigateToMainActivity()
        var currentRoom = _roomsFirebase.child(_settingRepository.Room!!.id)
        if(currentRoom == null)
            navigateToMainActivity()

        super.onStart()
        roomName.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(view: View?): Boolean {
                // todo ask for password or something
                _settingRepository.Room = null
                navigateToMainActivity()
                return true;
            }
        })

        // todo: https://firebaseopensource.com/projects/firebase/firebaseui-android/database/README.md
        // todo create factories for models
        _roomsFirebase
                .child(_settingRepository.Room!!.id)
                .child("timeslots")
                .addChildEventListener(object: ChildEventListener {

                    override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                        val startDate = DateTime.parse(dataSnapshot.child("startDate").value.toString()).toLocalDateTime()
                        val endDateValue = dataSnapshot.child("endDate").value.toString();
                        val endDate = if (endDateValue.isNullOrBlank()) null else DateTime.parse(endDateValue).toLocalDateTime()
                        val timeslot = Timeslot(dataSnapshot.key, startDate, endDate)
                        _timeslots.add(timeslot)
                        checkAvailable()
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                        val index = _timeslots.indexOfFirst { timeslot -> timeslot.id.equals(dataSnapshot.key)  }
                        val startDate = DateTime.parse(dataSnapshot.child("startDate").value.toString()).toLocalDateTime()
                        val endDateValue = dataSnapshot.child("endDate").value.toString();
                        val endDate = if (endDateValue.isNullOrBlank()) null else DateTime.parse(endDateValue).toLocalDateTime()
                        val timeslot = Timeslot(dataSnapshot.key, startDate, endDate)
                        _timeslots[index] = timeslot;
                        checkAvailable()
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                        val index = _timeslots.indexOfFirst { timeslot -> timeslot.id.equals(dataSnapshot.key)  }
                        _timeslots.removeAt(index)
                        checkAvailable()
                    }

                    override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                        throw NotImplementedError("Should never happen")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        Log.w("Rooms", "Failed to read value.", error.toException());
                    }
        })

        var timeListener = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context, intent: Intent) {
                if (intent.action!!.compareTo(Intent.ACTION_TIME_TICK) != 0) return

                Model.Date = DateTime.now().toLocalDateTime()
                checkAvailable()
            }
        }

        this.applicationContext.registerReceiver(timeListener, IntentFilter(Intent.ACTION_TIME_TICK))
        checkAvailable()
    }

    private fun checkAvailable() {
        val now = DateTime.now().toLocalDateTime()
        Model.Available = !_timeslots.any { timeslot -> isReserved(now!!, timeslot) }

        if(Model.Available){
            roomInfoBar.setBackgroundColor(getColor(R.color.colorAccent))
        }
        else{
            roomInfoBar.setBackgroundColor(getColor(R.color.colorInactive))
        }
    }

    private fun isReserved(now: LocalDateTime , timeslot: ITimeslot): Boolean{
        if(timeslot.startDate!!.isAfter(now)) return false
        if(timeslot.endDate == null) return true
        if(timeslot.endDate!!.isBefore(now)) return false;
        return true;
    }

    private fun navigateToMainActivity() = startActivity(Intent(this, MainActivity::class.java))
}
