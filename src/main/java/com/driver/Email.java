package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(this.password == oldPassword && newPassword.length()>=8){
            if(uppercaseCheck(newPassword) && lowercaseCheck(newPassword) && digitCheck(newPassword) && characterCheck(newPassword)){
                this.password = newPassword;
            }
        }
    }

    public boolean uppercaseCheck(String str){
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }

    public boolean lowercaseCheck(String str){
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(Character.isLowerCase(ch)){
                return true;
            }
        }
        return false;
    }

    public boolean digitCheck(String str){
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                return true;
            }
        }
        return false;
    }

    public boolean characterCheck(String str){
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(!Character.isLetter(ch) && !Character.isDigit(ch) && !Character.isWhitespace(ch)){
                return true;
            }
        }
        return false;
    }
}


