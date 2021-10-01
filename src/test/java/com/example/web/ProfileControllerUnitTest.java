package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerUnitTest {

    @Test
    public void call_project_real_profile() {
        // given
        final String expectedProfile = "real";
        final MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        final ProfileController controller = new ProfileController(env);

        // when
        final String profile = controller.profile();

        // then
        assertEquals(profile, expectedProfile);
    }

    @Test
    public void will_return_default_when_non_active_profile() {
        // given
        final String expectedProfile = "default";
        final MockEnvironment env = new MockEnvironment();
        final ProfileController controller = new ProfileController(env);

        // when
        final String profile = controller.profile();

        // then
        assertEquals(profile, expectedProfile);
    }

}