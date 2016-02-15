package edu.galileo.android.photoshare.main;

import android.location.Location;

import edu.galileo.android.photoshare.main.events.MainEvent;

/**
 * Created by ykro.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(Location location, String path);
    void onEventMainThread(MainEvent event);
}
