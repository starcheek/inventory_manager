import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class RegistrationValidatorTest {

    private lateinit var registrationValidator: RegistrationValidator

    @BeforeEach
    fun onBefore() {
        registrationValidator = RegistrationValidator()
    }

    @Test
    fun `validateRegistrationInput() empty username returns false`() {
        val actualResult = registrationValidator.validateRegistrationInput(
            "",
            "qwerty",
            "qwerty"
        )
        assertFalse(actualResult)
    }

    @Test
    fun `validateRegistrationInput() empty password returns false`() {
        val actualResult = registrationValidator.validateRegistrationInput(
            "qwerty",
            "",
            "qwerty"
        )
        assertFalse(actualResult)
    }

    @Test
    fun `validateRegistrationInput() empty repeatedPassword returns false`() {
        val actualResult = registrationValidator.validateRegistrationInput(
            "qwerty",
            "qwerty",
            ""
        )
        assertFalse(actualResult)
    }
}
