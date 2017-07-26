package com.valtech.amsterdam.vris.dummy;

import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;
import com.valtech.amsterdam.vris.model.TimeSlotList;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
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
    public static final ArrayList<Reservation> RESERVATIONS = new ArrayList<>();
    public static final TimeSlotList TIMESLOTS = new TimeSlotList();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Reservation> RESERVATIONS_MAP = new HashMap<Integer, Reservation>();

    private static final int COUNT = 25;


    static {
        // Add some sample items.
        addItem(new Reservation(1, "Lunchmeeting met onmogelijk lange titel",
                new DateTime(2017, 7, 14, 9, 30).toLocalDateTime(),
                new DateTime(2017, 7, 14, 10, 30).toLocalDateTime(),
                new Person(1, "Meneer een"), null));
        addItem(new TimeSlot(2,
                new DateTime(2017, 7, 14, 10, 30).toLocalDateTime(),
                new DateTime(2017, 7, 14, 10, 45).toLocalDateTime()));
        addItem(new Reservation(3, "Nog zo'n meeting met ellendig lange titel",
                new DateTime(2017, 7, 14, 10, 45).toLocalDateTime(),
                new DateTime(2017, 7, 14, 11, 00).toLocalDateTime(),
                new Person(2, "Meneer Twee"), null));
        addItem(new Reservation(4, "Dummy 3",
                new DateTime(2017, 7, 14, 11, 00).toLocalDateTime(),
                new DateTime(2017, 7, 14, 11, 30).toLocalDateTime(),
                new Person(3, "Meneer Drie"), null));
        addItem(new TimeSlot(5,
                new DateTime(2017, 7, 14, 11, 30).toLocalDateTime(),
                new DateTime(2017, 7, 14, 13, 00).toLocalDateTime()));
        addItem(new Reservation(6, "Dummy 4",
                new DateTime(2017, 7, 14, 13, 00).toLocalDateTime(),
                new DateTime(2017, 7, 14, 13, 45).toLocalDateTime(),
                new Person(3, "Meneer Drie"), null));
        addItem(new TimeSlot(7,
                new DateTime(2017, 7, 14, 13, 45).toLocalDateTime(),
                new DateTime(2017, 7, 14, 15, 15).toLocalDateTime()));
        addItem(new Reservation(8, "Dummy 5",
                new DateTime(2017, 7, 14, 15, 15).toLocalDateTime(),
                new DateTime(2017, 7, 14, 17, 0).toLocalDateTime(),
                new Person(1, "Meneer Een"), null));
        addItem(new TimeSlot(9,
                new DateTime(2017, 7, 14, 17, 0).toLocalDateTime(),
                new DateTime(2018, 7, 14, 18, 0).toLocalDateTime()));
    }

    private static void addItem(Reservation item) {
        RESERVATIONS.add(item);
        RESERVATIONS_MAP.put(item.getId(), item);
    }

    private static void addItem(TimeSlot item) {
        TIMESLOTS.add(item);
    }
}