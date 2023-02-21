package com.web.Mcq.utils;

public class UserConstants {
    public static final String NAME_REGEX = "^[a-zA-Z \\\\s]*$";
    public static final String Number_Regex = "^[0-9]";
    public static final String phoneNumber = "^[+]{1}(?:[0-9\\-\\(\\)\\/\\.]\\s?){6,15}[0-9]{1}$";
    public static final String NO_FIRST_NAME = "First name not provided";
    public static final String INVALID_FIRST_NAME = "Invalid First Name";
    public static final String INVALID_LAST_NAME = "Invalid Last Name";

    public static final String NO_PhoneNumber = " NO_PhoneNumber is not provided";
    public static final String INVALID_PhoneNumber = "Invalid PhoneNumber";
    public static final String Email_Id = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    public static final String API = "api/v1";

    public static final String NO_EmailId = " Email-id is not provided";

    public static final String INVALID_EmailId= "Invalid EmailId";

    public static final String POST_User="/insertData";
}
