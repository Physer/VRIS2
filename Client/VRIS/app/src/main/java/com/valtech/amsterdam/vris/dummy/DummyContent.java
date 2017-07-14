package com.valtech.amsterdam.vris.dummy;

import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.Room;

import java.util.ArrayList;
import java.util.Date;
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
    public static final List<Reservation> ITEMS = new ArrayList<Reservation>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Reservation> ITEM_MAP = new HashMap<Integer, Reservation>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Reservation item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static Reservation createDummyItem(int position) {
        Person person = new Person(position, "testperson");
        Room room = new Room(position, "5B");
        return new Reservation(position, new Date(), new Date(), person, room);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
