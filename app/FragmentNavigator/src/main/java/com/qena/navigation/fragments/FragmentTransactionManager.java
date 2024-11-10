package com.qena.navigation.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * This class manages fragment transactions using the Android FragmentManager.
 * It abstracts the process of adding, removing, showing, hiding, attaching, and detaching fragments
 * in a clean and reusable way, ensuring that transactions are performed efficiently with proper reordering.
 *
 * It provides a convenient wrapper around the FragmentTransaction API to manage fragment transactions.
 * It allows for operations like:
 * - Starting a new fragment transaction.
 * - Committing a fragment transaction.
 * - Adding, removing, showing, hiding, detaching, and attaching fragments.
 *
 * The class assumes that transactions are being handled sequentially, and it handles the transaction
 * logic internally for each operation. Additionally, it provides methods to ensure that the transaction
 * is committed correctly once all fragment operations are completed.
 */
public class FragmentTransactionManager {

    private FragmentManager fragmentManager; // The FragmentManager to manage fragments in the activity
    private FragmentTransaction transaction; // The current transaction being performed

    /**
     * Constructor that initializes the FragmentTransactionManager with the given FragmentManager.
     *
     * @param fragmentManager The FragmentManager to manage fragment transactions
     */
    public FragmentTransactionManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /**
     * Starts a new fragment transaction and sets reordering to true for optimized performance.
     * This should be called before any fragment operation (add, remove, show, etc.).
     */
    public void startTransaction() {
        transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);  // Optimizes fragment transaction performance
    }

    /**
     * Commits the current fragment transaction. This method should be called after all fragment
     * operations (add, remove, show, etc.) are complete to apply the changes.
     */
    public void commitTransaction() {
        transaction.commit(); // Finalizes and applies the fragment transaction
    }

    /**
     * Adds a fragment to the specified container. The fragment is added to the transaction.
     * This method should be called when you need to add a new fragment to the UI.
     *
     * @param fragment The fragment to be added
     * @param containerId The container view's ID where the fragment will be placed
     */
    public void addFragment(Fragment fragment, int containerId) {
        transaction.add(containerId, fragment); // Adds the fragment to the transaction
    }

    /**
     * Removes a fragment from the transaction. The fragment will be removed from the UI.
     * This method should be used when you want to remove a fragment completely.
     *
     * @param fragment The fragment to be removed
     */
    public void removeFragment(Fragment fragment) {
        transaction.remove(fragment); // Removes the fragment from the transaction
    }

    /**
     * Shows a fragment that was previously hidden or detached. This method should be used
     * when you want to make a fragment visible again in the UI.
     *
     * @param fragment The fragment to be shown
     */
    public void showFragment(Fragment fragment) {
        transaction.show(fragment); // Makes the fragment visible in the UI
    }

    /**
     * Hides a fragment from the UI without removing it. This method should be used
     * when you want to temporarily hide a fragment but retain its state.
     *
     * @param fragment The fragment to be hidden
     */
    public void hideFragment(Fragment fragment) {
        transaction.hide(fragment); // Hides the fragment from the UI
    }

    /**
     * Detaches a fragment from the UI. The fragment is no longer in the UI but its state is preserved.
     * This method should be used when you want to detach a fragment for later re-attachment.
     *
     * @param fragment The fragment to be detached
     */
    public void detachFragment(Fragment fragment) {
        transaction.detach(fragment); // Detaches the fragment, preserving its state
    }

    /**
     * Attaches a previously detached fragment back to the UI. This method should be used
     * when you want to bring a detached fragment back into the UI.
     *
     * @param fragment The fragment to be attached
     */
    public void attachFragment(Fragment fragment) {
        transaction.attach(fragment); // Attaches the fragment back to the UI
    }
    public void setFragmentAnimation(int enterAnim, int exitAnim) {
        transaction.setCustomAnimations(enterAnim,exitAnim); // تعيين تأثيرات الرسوم المتحركة
    }
}
