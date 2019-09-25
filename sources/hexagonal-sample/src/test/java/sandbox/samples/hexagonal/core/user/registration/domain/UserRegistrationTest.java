package sandbox.samples.hexagonal.core.user.registration.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationInput.UserRegistrationInputBuilder;

public class UserRegistrationTest {

  private UserRegistrationDataProvider userRegistrationService;
  private ConfirmationMailSender userRegistrationMailSender;
  private UserRegistrationNotifier userRegistrationNotifier;

  @BeforeEach
  void setup() {
    userRegistrationService = mock(UserRegistrationDataProvider.class);
    userRegistrationMailSender = mock(ConfirmationMailSender.class);
    userRegistrationNotifier = mock(UserRegistrationNotifier.class);
  }

  @Test
  public void shouldThrowExceptionWhenMissingEmail() {
    // when
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                setupDomainObject()
                    .createUserAccount(sampleRegistrationInput().email(null).build()));

    // then
    assertEquals("Email is required.", exception.getMessage());
  }

  @Test
  public void shouldPrepareUserAccountDataWithGeneratedActivationCode() {
    // given
    UserRegistrationInput registrationInput = sampleRegistrationInput().build();

    // when
    UserAccountData userAccountData = setupDomainObject().createUserAccount(registrationInput);

    // then
    assertNotNull(userAccountData.getActivationCode());
    assertEquals(registrationInput.getLogin(), userAccountData.getLogin());
    assertEquals(registrationInput.getPassword(), userAccountData.getPassword());
    assertEquals(registrationInput.getEmail(), userAccountData.getEmail());
    assertNull(userAccountData.getId());
    assertFalse(userAccountData.isActive());

  }

  @Test
  public void shouldSendMailWhenCreateUserAccount() {
    // given
    UserRegistrationInput registrationInput = sampleRegistrationInput().build();

    // when
    setupDomainObject().createUserAccount(registrationInput);

    // then
    verify(userRegistrationMailSender, times(1)).send(any());
  }

  @Test
  public void shouldActivateUserAccount() {
    // given
    UserAccountData userData = sampleUserData();
    when(userRegistrationService.findUserByActivationCode(userData.getActivationCode()))
        .thenReturn(Optional.of(userData));

    // when
    UserAccountData userAccountData = setupDomainObject().activateUserAccount(userData.getActivationCode());

    // then
    assertTrue(userAccountData.isActive());
  }

  @Test
  public void shouldSendEventWhenUserAccountActivated() {
    // given
    UserAccountData userData = sampleUserData();
    when(userRegistrationService.findUserByActivationCode(userData.getActivationCode()))
        .thenReturn(Optional.of(userData));

    // when
    setupDomainObject().activateUserAccount(userData.getActivationCode());

    // then
    verify(userRegistrationNotifier, times(1)).sendMessage(userData);
  }

  @Test
  public void shouldThrowExceptionWhenConfirmationCodeIsInvalid() {
    // given
    UserAccountData userData = sampleUserData();
    when(userRegistrationService.findUserByActivationCode(userData.getActivationCode()))
        .thenReturn(Optional.of(userData));

    // when
    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> setupDomainObject().activateUserAccount("invalidCode"));

    // then
    assertEquals("Couldn't confirm your account.", exception.getMessage());
  }

  private UserRegistrationInputBuilder sampleRegistrationInput() {
    return UserRegistrationInput.builder().email("test@gmail.com").login("tester").password("123");
  }

  private UserRegistration setupDomainObject() {
    return new UserRegistration(
        userRegistrationService, userRegistrationMailSender, userRegistrationNotifier);
  }

  private UserAccountData sampleUserData() {
    UserAccountData userData = new UserAccountData();
    userData.setLogin(RandomStringUtils.randomAlphanumeric(10));
    userData.setActivationCode(RandomStringUtils.randomAlphanumeric(20));
    userData.setActive(false);
    userData.setId(1L);
    return userData;
  }
}
