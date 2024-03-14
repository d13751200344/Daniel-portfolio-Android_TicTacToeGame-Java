package com.example.jwassignment2.ViewModel;

import androidx.lifecycle.ViewModel;

public class PlayerViewModel extends ViewModel {
    private String playerOneName;
    private String playerTwoName;

    //constructor
    public PlayerViewModel() {
    }

    public String getPlayerOneName() {
        return playerOneName;
    }
    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }
    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

}
