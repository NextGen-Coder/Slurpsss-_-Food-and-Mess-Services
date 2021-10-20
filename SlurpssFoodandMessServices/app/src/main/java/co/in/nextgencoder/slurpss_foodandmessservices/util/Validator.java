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

    public String validateUserLogin( String mail, String password) {

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


    public String validateAddDish(String name, String price, String description) {

        if( name != null && name.equals( "")) {
            return "Name is required";
        }

        if( price != null && price.equals( "")) {
            return "Price is required";
        }

        if( description != null && description.equals( "")) {
            return "Description is required";
        }

        return "Validation Successful";
    }

    public String validateAddPackage(String name, String price, String description) {

        if( name != null && name.equals( "")) {
            return "Name is required";
        }

        if( price != null && price.equals( "")) {
            return "Price is required";
        }

        if( description != null && description.equals( "")) {
            return "Description is required";
        }

        return "Validation Successful";
    }

    public String validateUpdateMess(String addressURL, String messDescription, String messSafety) {

        if( addressURL != null && addressURL.equals( "")) {
            return "Address URL is required";
        }

        if( messDescription != null && messDescription.equals( "")) {
            return "Description is required";
        }

        if(messSafety != null && messSafety.equals( "")) {
            return "Safety Measures is required";
        }

        return "Validation Successful";
    }
}
