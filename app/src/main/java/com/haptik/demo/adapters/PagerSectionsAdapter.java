package com.haptik.demo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.haptik.demo.activities.ChatActivity;
import com.haptik.demo.fragments.ChatListFragment;
import com.haptik.demo.fragments.FavChatListFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PagerSectionsAdapter extends FragmentPagerAdapter {
    private ChatListFragment chatListFragment;
    private FavChatListFragment favChatListFragment;

    public PagerSectionsAdapter(FragmentManager fm) {
        super(fm);
        chatListFragment = new ChatListFragment();
        favChatListFragment = new FavChatListFragment();
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                 return chatListFragment;

            case 1:
                return favChatListFragment;
            default:
                return  null;
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Chat List";
            case 1:
                return "Conversation";

        }
        return null;
    }
}