package com.framgia.foodapp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.framgia.foodapp.R;

public class Navigator {
    public void addFragment(FragmentActivity fragmentActivity, Fragment fragment, int layoutId) {
        switch (layoutId) {
            case R.id.frameContainer:
                FragmentTransaction fragmentTransaction =
                        fragmentActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(layoutId, fragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
