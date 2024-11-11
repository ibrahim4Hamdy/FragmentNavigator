package com.qena.navigation.fragments;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;
/**
 * FragmentNavigator is a utility class for managing fragment navigation and transactions in an Android application.
 * It is designed to handle fragment transactions such as adding, removing, showing, or hiding fragments based on user navigation.
 * Additionally, it supports retaining the state of fragments when navigating between them.
 *
 * The class works with two main managers:
 * - **FragmentTransactionManager**: This class is responsible for handling the actual fragment transactions (add, remove, hide, show, etc.).
 * - **FragmentStateManager**: This class stores and manages whether a fragment's state should be retained when navigating away from it.
 *
 * By combining these managers, FragmentNavigator makes it easy to navigate between fragments while managing their state and transactions efficiently.
 */
public class NavigatorX {

    private final FragmentManager fragmentManager;               // The FragmentManager responsible for managing fragments
    private final FragmentTransactionManager transactionManager; // Manages fragment transaction operations (add, remove, hide, show)
    private final FragmentStateManager stateManager;             // Manages the state retention of fragments
    private final Map<Integer, Fragment> fragmentMap;            // A map that stores fragments by their associated menu item ID
    private Fragment currentFragment = null;               // The currently displayed fragment
    private boolean currentFragmentState;                  // Whether the current fragment's state should be retained

    /**
     * Constructor for the FragmentNavigator. It initializes the fragment transaction manager, state manager,
     * and the fragment map that will store fragments associated with each menu item.
     *
     * @param fragmentManager The FragmentManager instance to handle fragment transactions.
     */
    public NavigatorX(@NonNull FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.transactionManager = new FragmentTransactionManager(fragmentManager);
        this.stateManager = new FragmentStateManager();
        this.fragmentMap = new HashMap<>();
    }

    /**
     * Adds a new fragment to the navigator with a flag that specifies whether its state should be kept.
     * This allows the navigator to retain the state of a fragment when navigating back to it.
     *
     * @param menuItemId The unique ID associated with the fragment.
     * @param fragment   The fragment to be added.
     * @param keepState  A flag indicating whether the fragment should retain its state.
     */
    public void addFragment(@IdRes int menuItemId,@NonNull Fragment fragment, boolean keepState) {
        fragmentMap.put(menuItemId, fragment);                  // Store the fragment in the map using the menu item ID as key
        stateManager.addFragmentState(menuItemId, keepState);   // Save the state retention preference for this fragment
    }
    /**
     * This method adds a fragment to the fragment map and sets its arguments (if any) before adding it to the transaction.
     * The arguments are passed as a `Bundle`, which allows you to pass data to the fragment at the time of its creation.
     *
     * @param menuItemId The identifier for the fragment (e.g., a menu item ID).
     * @param fragment The fragment to be added.
     * @param keepState A boolean that indicates whether the fragment's state should be preserved across fragment transactions.
     * @param args A `Bundle` object containing any data that needs to be passed to the fragment.
     *
     * Example usage:
     *
     * // Creating a Bundle to store fragment arguments
     * Bundle args = new Bundle();
     * args.putString("key", "value");  // Add data to the Bundle
     *
     * // Adding the fragment with arguments
     * fragmentNavigator.addFragmentWithArgs(R.id.fragment_menu_item, new MyFragment(), true, args);
     */
    public void addFragment(@IdRes int menuItemId,@NonNull Fragment fragment,boolean keepState,Bundle args) {
        fragment.setArguments(args);  // Set the fragment's arguments, which can be accessed later in the fragment
        fragmentMap.put(menuItemId, fragment);
        stateManager.addFragmentState(menuItemId, keepState);
    }


    /**
     * Navigates to a fragment associated with the specified menu item ID.
     * The fragment is added or shown based on its current state, and the current fragment is hidden or removed.
     * If a fragment should retain its state, it will be hidden instead of removed.
     *
     * @param itemId           The ID of the menu item representing the fragment to navigate to.
     * @param containerViewId  The ID of the container in which the fragment will be placed.
     * @return true if navigation was successful, false if no fragment is found for the given ID.
     */
    public boolean navigateToFragment(@IdRes int itemId, @IdRes int containerViewId) {
        // Begin a new fragment transaction
        transactionManager.startTransaction();
        // Retrieve the fragment corresponding to the menu item ID
        Fragment selectedFragment = fragmentMap.get(itemId);
        // Retrieve whether the fragment should retain its state
        boolean selectedKeepState = stateManager.getFragmentState(itemId);

        if (selectedFragment == null) {
            // If the fragment does not exist for the given ID, return false
            return false;
        }

        // If the selected fragment is not added yet, we add it to the container and remove or hide the current fragment
        if (!selectedFragment.isAdded()) {
            if (!currentFragmentState) {
                transactionManager.addFragment(selectedFragment, containerViewId);  // Add the new fragment to the container
                if (currentFragment != null) {
                    transactionManager.removeFragment(currentFragment);  // Remove the current fragment if its state is not retained
                }
            } else {
                transactionManager.addFragment(selectedFragment, containerViewId);  // Add the new fragment to the container
                if (currentFragment != null) {
                    transactionManager.hideFragment(currentFragment);  // Hide the current fragment if its state is retained
                }
            }
        } else {
            // If the fragment is already added, we show it and hide or remove the current fragment as necessary
            if (!currentFragmentState && currentFragment != selectedFragment) {
                transactionManager.removeFragment(currentFragment);  // Remove the current fragment if its state is not retained
                transactionManager.showFragment(selectedFragment);    // Show the selected fragment
            } else {
                transactionManager.hideFragment(currentFragment);  // Hide the current fragment
                transactionManager.showFragment(selectedFragment);  // Show the selected fragment
            }
        }

        currentFragment = selectedFragment;        // Update the reference to the current fragment
        currentFragmentState = selectedKeepState;  // Update the state retention flag for the current fragment

        transactionManager.commitTransaction();  // Commit the transaction to finalize the changes

        return true;  // Return true indicating that the navigation was successful
    }
    
    /**
     * Sets a fragment as the current one and adds it to the container. This is typically used to set an initial fragment or reset the current fragment.
     *
     * @param menuId   The ID of the menu item representing the fragment to set as current.
     * @param fragment The fragment to be set as current.
     */
    public void setCurrentFragment(@IdRes int menuId, Fragment fragment,@IdRes int containerViewId) {
        this.currentFragment = fragmentMap.get(menuId);  // Retrieve the fragment using its associated menu item ID
        this.currentFragmentState = stateManager.getFragmentState(menuId);  // Retrieve whether the fragment should retain its state

        transactionManager.startTransaction();  // Begin a new fragment transaction
        transactionManager.addFragment(fragment, containerViewId);  // Add the fragment to the container
        transactionManager.commitTransaction();// Commit the transaction to finalize the changes
    }

    public void preloadFragment(@IdRes int menuItemId,@IdRes int containerViewId) {
        Fragment fragment = fragmentMap.get(menuItemId);
        if (fragment != null && !fragment.isAdded()) {
            transactionManager.startTransaction();
            transactionManager.addFragment(fragment,containerViewId);
            transactionManager.hideFragment(fragment);
            transactionManager.commitTransaction();
        }
    }
}

