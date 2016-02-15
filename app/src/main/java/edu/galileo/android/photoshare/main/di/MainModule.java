package edu.galileo.android.photoshare.main.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.photoshare.lib.base.EventBus;
import edu.galileo.android.photoshare.lib.base.ImageStorage;
import edu.galileo.android.photoshare.main.MainPresenter;
import edu.galileo.android.photoshare.main.MainPresenterImpl;
import edu.galileo.android.photoshare.main.MainRepository;
import edu.galileo.android.photoshare.main.MainRepositoryImpl;
import edu.galileo.android.photoshare.main.SessionInteractor;
import edu.galileo.android.photoshare.main.SessionInteractorImpl;
import edu.galileo.android.photoshare.main.UploadInteractor;
import edu.galileo.android.photoshare.main.UploadInteractorImpl;
import edu.galileo.android.photoshare.main.ui.MainView;
import edu.galileo.android.photoshare.main.ui.adapters.MainSectionsPagerAdapter;


/**
 * Created by ykro.
 */
@Module
public class MainModule {
    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, FragmentManager fragmentManager, Fragment[] fragments, String[] titles) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return this.view;
    }

    @Provides @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, UploadInteractor uploadInteractor, SessionInteractor sessionInteractor) {
        return new MainPresenterImpl(view, eventBus, uploadInteractor, sessionInteractor);
    }

    @Provides @Singleton
    UploadInteractor providesUploadInteractor(MainRepository repository) {
        return new UploadInteractorImpl(repository);
    }

    @Provides @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository) {
        return new SessionInteractorImpl(repository);
    }

    @Provides @Singleton
    MainRepository providesMainRepository(EventBus eventBus, Firebase firebase, ImageStorage imageStorage) {
        return new MainRepositoryImpl(eventBus, firebase, imageStorage);
    }

    @Provides @Singleton
    MainSectionsPagerAdapter providesAdapter(FragmentManager fm, Fragment[] fragments, String[] titles){
        return new MainSectionsPagerAdapter(fm, fragments, titles);
    }

    @Provides @Singleton
    FragmentManager providesAdapterFragmentManager(){
        return this.fragmentManager;
    }

    @Provides @Singleton
    Fragment[] providesFragmentArrayForAdapter(){
        return this.fragments;
    }

    @Provides @Singleton
    String[] providesStringArrayForAdapter(){
        return this.titles;
    }



}
