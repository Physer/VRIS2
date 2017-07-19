package com.valtech.amsterdam.vris.dummy;

import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.Room;
import com.valtech.amsterdam.vris.model.TimeSlot;

import org.joda.time.DateTime;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Reservation> RESERVATIONS = new ArrayList<>();
    public static final List<ITimeSlot> TIMESLOTS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Reservation> RESERVATIONS_MAP = new HashMap<Integer, Reservation>();

    private static final int COUNT = 25;


    static {
        // Add some sample items.
        addItem(new Reservation(1, new DateTime(2017, 7, 14, 9, 30), new DateTime(2017, 7, 14, 10, 30), new Person(1, "Meneer Een")));
        addItem(new TimeSlot(2, new DateTime(2017, 7, 14, 10, 30), new DateTime(2017, 7, 14, 10, 45)));
        addItem(new Reservation(3, new DateTime(2017, 7, 14, 10, 45), new DateTime(2017, 7, 14, 11, 00), new Person(2, "Meneer Twee")));
        addItem(new Reservation(4, new DateTime(2017, 7, 14, 11, 00), new DateTime(2017, 7, 14, 11, 30), new Person(3, "Meneer Drie")));
        addItem(new TimeSlot(5, new DateTime(2017, 7, 14, 11, 30), new DateTime(2017, 7, 14, 13, 00)));
        addItem(new Reservation(6, new DateTime(2017, 7, 14, 13, 00), new DateTime(2017, 7, 14, 13, 45), new Person(3, "Meneer Drie")));
        addItem(new TimeSlot(7, new DateTime(2017, 7, 14, 13, 45), new DateTime(2017, 7, 14, 15, 15)));
        addItem(new Reservation(8, new DateTime(2017, 7, 14, 15, 15), new DateTime(2017, 7, 14, 17, 0), new Person(1, "Meneer Een")));
        addItem(new TimeSlot(9, new DateTime(2017, 7, 14, 17, 0), new DateTime(2017, 7, 14, 18, 0)));
        addItem(new TimeSlot(10, new DateTime(2017, 7, 14, 18, 0), new DateTime(2018, 7, 14, 18, 0)));
    }

    private static void addItem(Reservation item) {
        RESERVATIONS.add(item);
        RESERVATIONS_MAP.put(item.getId(), item);
    }

    private static void addItem(TimeSlot item) {
        TIMESLOTS.add(item);
    }
}