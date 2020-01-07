package com.nextlevelstudy.dummy;

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
    public static final List<DummyUniversity> ITEMS = new ArrayList<DummyUniversity>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyUniversity> ITEM_MAP = new HashMap<String, DummyUniversity>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyUniversity(i));
        }
    }

    private static void addItem(DummyUniversity item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyUniversity createDummyUniversity(int position) {
        return new DummyUniversity(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyUniversity {
        public final String id;
        public final String name;
        public final String country;

        public DummyUniversity(String id, String name, String country) {
            this.id = id;
            this.name = name;
            this.country = country;
        }

        @Override
        public String toString() {
            return name.concat(",").concat(country);
        }
    }
}
