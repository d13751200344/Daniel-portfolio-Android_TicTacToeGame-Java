package com.example.jwassignment2.ViewModel;

import androidx.lifecycle.ViewModel;

public class PlayerViewModel extends ViewModel {
    private String playerOneName;
    private String playerTwoName;

    //constructor
    public PlayerViewModel() {
    }

    public String getPlayerOneNameName() {
        return playerOneName;
    }
    public String getPlayerTwoNameName() {
        return playerTwoName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }
    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    // Method to validate player names (optional)
    public boolean arePlayerNamesValid() {
        return !playerOneName.isEmpty() && !playerTwoName.isEmpty();
    }
}
