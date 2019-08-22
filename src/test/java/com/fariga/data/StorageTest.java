
package com.fariga.data;

import com.fariga.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StorageTest {

    @InjectMocks
    private Storage storage;


    @Test
    public void getUserInfoByUsername_ReturnUser() {
        User expectedUser = new User("kventin", "tarantino", "django", "111", "not_logged-in", "User");

        storage.createUser(expectedUser);
        User actualUser = storage.getUserInfo("django");

        assertThat(actualUser, is(samePropertyValuesAs(expectedUser)));
        assertNotNull("User is null", actualUser);
    }

    @Test
    public void getUserInfoByUsername_ReturnNonUser() {
        User actualUserNumberTwo = storage.getUserInfo("piter");

        assertEquals(actualUserNumberTwo.getUsername(), "nonUser");
    }

    @Test
    public void updateUserInfo_ReturnListOfUsers() {
        User actualUser = mock(User.class);
        String expectedUsernameValue = "django";
        User expectedUser = new User("kventin", "tarantino", "django", "111", "not_logged-in", "User");

        storage.createUser(expectedUser);
        List<User> usersListWithUpdatedUser = storage.updateUserInfo("kventinos", "tarantinos", expectedUsernameValue);

        when(actualUser.getUsername()).thenReturn(expectedUsernameValue);
        assertThat(usersListWithUpdatedUser, hasItems(expectedUser));
    }

    @Test
    public void updateUserInfo_ReturnListOfUsersIfUserWasNotInList() {
        User anotherUser = new User("anna", "helman", "django", "444", "not_logged-in", "User");

        List<User> usersListWithUpdatedUser = storage.updateUserInfo(anotherUser.getFirstName(), anotherUser.getLastName(), anotherUser.getUsername());

        assertThat(usersListWithUpdatedUser, not(hasItem(anotherUser)));
    }

    @Test
    public void deleteUserByUsername_ReturnListOfUsersWithoutUser() {
        String expectedUsernameValue = "django";
        User expectedUser = new User("kventin", "tarantino", "django", "111", "not_logged-in", "User");

        storage.createUser(expectedUser);
        List<User> usersListWithoutDeletededUser = storage.deleteUser(expectedUsernameValue);

        assertThat(usersListWithoutDeletededUser, not(hasItem(expectedUser)));
    }

    @Test
    public void deleteUserByUsername_ReturnListOfUsersIfUserWasNotInList() {
        User anotherUser = new User("anna", "helman", "django", "444", "not_logged-in", "User");

        List<User> usersListWithoutDeletededUser = storage.deleteUser(anotherUser.getUsername());

        assertThat(usersListWithoutDeletededUser, not(hasItem(anotherUser)));
    }
}
