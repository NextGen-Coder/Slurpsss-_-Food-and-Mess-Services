package co.in.nextgencoder.slurpss_foodandmessservices.util;

public class Validator {

    public String validateMessLogin( String mail, String password) {

        if( mail != null && mail.equals( "")) {
            return "Mail is required";
        }

        if( password != null && password.equals( "")) {
            return "Password is required";
        }

        if(  password.length() < 8) {
            return "Password length should be greater than 7";
        }

        return "Validation Successful";
    }
}
