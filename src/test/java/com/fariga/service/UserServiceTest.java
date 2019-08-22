package com.fariga.service;

import com.fariga.data.Storage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private Storage storage;

    private UserService userService;

    @Before
    public void setUpStorageAndUserServiceInstances() {
        userService = new UserService(storage);
    }

    @Test
    public void checkIfUserWithUsernameAndPasswordExistInList_ReturnBooleanValue() {
        assertTrue(userService.check("firstUsername", "111"));
        assertFalse(userService.check("mip", "112"));
    }

    @Test
    public void checkingUserByUsernameInList_ReturnBooleanValue() {
        assertTrue(userService.checkingUsernameInList("firstUsername"));
        assertFalse(userService.checkingUsernameInList("mip"));
    }

    @Test
    public void updateLoginStatusByGettingUsername_ReturnBooleanValue() {
        assertTrue(userService.updateLoginStatus("firstUsername"));
        assertFalse(userService.updateLoginStatus("sonya"));
    }

    @Test
    public void logoutUserByGettingUsername_ReturnBooleanValue() {
        updateLoginStatusByGettingUsername_ReturnBooleanValue();
        assertTrue(userService.logout("firstUsername"));
        assertFalse(userService.logout("mip"));
    }

}
