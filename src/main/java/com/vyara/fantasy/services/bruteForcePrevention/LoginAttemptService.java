package com.vyara.fantasy.services.bruteForcePrevention;

public interface LoginAttemptService {
    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked(String key);
}
