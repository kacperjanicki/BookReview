package com.example.bookreview;

import java.util.prefs.Preferences;

public class AuthManager {
    private static final Preferences prefs = Preferences.userRoot().node("com.example.bookreview");

    public static void saveLoginState(String username) {
        prefs.put("loggedInUser", username);
    }

    public static String getLoggedInUser() {
        return prefs.get("loggedInUser", null); // Returns null if no user is logged in
    }

    public static void clearLoginState() {
        prefs.remove("loggedInUser"); // Logs the user out
    }
}
