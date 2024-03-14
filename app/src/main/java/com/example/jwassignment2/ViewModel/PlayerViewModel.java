package com.example.jwassignment2.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayerViewModel extends ViewModel {
    private MutableLiveData<String> playerOneName = new MutableLiveData<>();
    private MutableLiveData<String> playerTwoName = new MutableLiveData<>();

    public LiveData<String> getPlayerOneName() {
        return playerOneName;
    }

    public LiveData<String> getPlayerTwoName() {
        return playerTwoName;
    }

    //constructor
    public PlayerViewModel() {
    }

    public void setPlayerOneName(String name) {
        playerOneName.setValue(name);
    }

    public void setPlayerTwoName(String name) {
        playerTwoName.setValue(name);
    }

}
