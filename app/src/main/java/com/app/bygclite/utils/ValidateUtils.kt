package com.app.bygclite.utils

import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern

/** Created by Jishnu P Dileep on 27-05-2021 */

fun isEmailValid(email: String): Boolean {
    val regExpn = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

    val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)

    return matcher.matches()
}

fun isPhoneNumberValid(phone: String): Boolean {
    return if (!Pattern.matches("[a-zA-Z]+", phone)) {
        (phone.length == 10)
    } else {
        false
    }
}

fun isOtpValid(otp: String?): Boolean {
    return otp?.length == 4
}

fun isZipCodeValid(zipCode: String?): Boolean {
    return zipCode?.length == 6
}

fun isPasswordValid(password: String?): Boolean {
    return password?.length!! >= 6
}

fun isCharacterPasswordValid(password: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(password)
    return matcher.matches()
}


fun isValidRegisterCode(regCode: String?): Boolean {

    val pattern = Pattern.compile("[a-zA-Z0-9]*")
    val matcher = pattern.matcher(regCode)
    return !matcher.matches()
}

fun isValidWebSite(website: String?): Boolean {
    val pattern =
        Pattern.compile("^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\/]@!\\\$&'\\/(\\/)\\*\\+,;=.]+\$")
    val matcher = pattern.matcher(website)
    return !matcher.matches()
}

fun isValidBankAccountNumber(bankAccountNumber: String?): Boolean {
    val pattern = Pattern.compile("[0-9]{9,18}")
    val matcher = pattern.matcher(bankAccountNumber)
    return !matcher.matches()
}

fun isValidIFSCCode(bankIFSC: String?): Boolean {

    val pattern = Pattern.compile("^[A-Za-z]{4}0[A-Z0-9a-z]{6}\$")
    val matcher = pattern.matcher(bankIFSC)
    return !matcher.matches()
}

fun isValidPanCard(panCardNo: String): Boolean {
    val pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
    val matcher = pattern.matcher(panCardNo)
    return !matcher.matches()
}

fun TextInputLayout.setError(isEnabled: Boolean) {
    error = if (!isEnabled) "Field cannot be empty" else null
    isErrorEnabled = !isEnabled
}

fun removeLastChar(s: String?): String? {
    return if (s == null || s.isEmpty()) {
        s
    } else s.substring(0, s.length - 2)
}

fun validName(name: String?): Boolean {
    val pattern = Pattern.compile("[a-zA-Z.'/ ]*")
    val matcher = pattern.matcher(name)
    return !matcher.matches()
}

fun isPasswordValidForNewBuild(pass: String?): Boolean {
    val regExpn = ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\\\/%§\"&“|`´}{°><:.;#')(@_\$\"!?*=^-]).{4,}\$")
    val pattern = Pattern.compile(regExpn)
    val matcher = pattern.matcher(pass ?: "")
    return matcher.matches()
}
