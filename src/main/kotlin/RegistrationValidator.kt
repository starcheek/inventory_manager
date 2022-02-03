class RegistrationValidator {

    fun validateRegistrationInput(username: String, password: String, repeatedPassword: String): Boolean {
        if (username.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()) {
            return false
        }
        if (password != repeatedPassword) {
            return false
        }

        return true
    }
}
