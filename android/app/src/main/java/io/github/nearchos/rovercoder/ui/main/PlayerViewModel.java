package io.github.nearchos.rovercoder.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayerViewModel extends ViewModel {

    private PlayerData mPlayerData;

    private MutableLiveData<PlayerData> mPlayerDataMutableLiveData = new MutableLiveData<>();

    public void setPlayerData(final PlayerData playerData) {
        this.mPlayerData = playerData;
        mPlayerDataMutableLiveData.setValue(playerData);
    }

    public LiveData<PlayerData> getPlayerDataLiveData() {
        return mPlayerDataMutableLiveData;
    }

    public boolean increaseSteps() {
        mPlayerData = mPlayerData.increaseCurrentStep();
        setPlayerData(mPlayerData);
        return mPlayerData.isFinished();
    }
}