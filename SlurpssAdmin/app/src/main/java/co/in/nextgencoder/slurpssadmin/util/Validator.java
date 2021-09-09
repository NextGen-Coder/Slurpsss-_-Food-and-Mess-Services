package co.in.nextgencoder.slurpssadmin.util;

public class Validator {

    public String validateMess(String name, String mail, String address, String password) {

        if( name != null && name.equals( "")) {
            return "Name is required";
        }

        if( address != null && address.equals( "")) {
            return "Address is required";
        }

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
