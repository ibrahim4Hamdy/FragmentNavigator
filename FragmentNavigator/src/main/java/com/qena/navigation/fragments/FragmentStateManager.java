package com.qena.navigation.fragments;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for managing the state retention of fragments based on their associated menu item IDs.
 * It allows the storage and retrieval of whether a fragment should retain its state or not.
 *
 * The main purpose of this class is to provide a centralized way of handling the state retention flag for each fragment.
 * This is particularly useful when dealing with navigation between fragments, where some fragments may need to
 * retain their state (e.g., user input or scroll position) when navigated away from, while others may not.
 *
 * The `FragmentStateManager` class provides the following functionality:
 * - Storing a "keepState" flag for each fragment identified by a menu item ID.
 * - Retrieving the state flag for a given fragment to decide whether to keep its state or not.
 *
 * This class operates with the assumption that each fragment has an associated menu item ID, and the state flag
 * indicates whether the fragment should retain its state upon navigation.
 */
public class FragmentStateManager {

    private final Map<Integer, Boolean> fragmentKeepStateMap; // Map to store the "keep state" flag for each fragment

    /**
     * Constructor that initializes the FragmentStateManager and its internal map.
     * Initializes the map to store state flags for fragments.
     */
    public FragmentStateManager() {
        fragmentKeepStateMap = new HashMap<>();
    }

    /**
     * Adds a state flag for a fragment identified by its menu item ID.
     *
     * @param menuItemId The ID associated with the fragment.
     * @param keepState The state flag indicating whether the fragment should retain its state.
     */
    public void addFragmentState(int menuItemId, boolean keepState) {
        fragmentKeepStateMap.put(menuItemId, keepState); // Store the state flag for the fragment
    }

    /**
     * Retrieves the "keep state" flag for a fragment based on its menu item ID.
     * If no state flag is found for the given ID, it returns false by default.
     *
     * @param menuItemId The ID associated with the fragment.
     * @return The "keep state" flag (true if the fragment should retain its state, false otherwise).
     */
    @SuppressLint("NewApi")
    public boolean getFragmentState(int menuItemId) {
        return fragmentKeepStateMap.getOrDefault(menuItemId, false); // Default to false if no state is set
    }
}
