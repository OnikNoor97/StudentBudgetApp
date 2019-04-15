package com.example.o_noo.studentbudgetapp;


public class Extra
{

    public boolean goodPassword(String password)
    {
        boolean returnBoolean, suitableLength = false, hasUpperCase = false;
        int hasNumbers = 0;
        char onik;

        if (password.length() > 5)
        {
            suitableLength = true;
        }
        for (int i = 0; i < password.length(); i++)
        {
            onik = password.charAt(i);

            if (Character.isDigit(onik)) {
                hasNumbers++;
            }
            else if (Character.isUpperCase(onik)) {
                hasUpperCase = true;
            }
        }
        if (hasNumbers > 2 && suitableLength && hasUpperCase) {
            returnBoolean = true;
        }
        else {
            returnBoolean = false;
        }
        return returnBoolean;
    }

    public String lol(double num)
    {
        String returnString = "transactions";

        if (num >= 2 || num <= 0)
        {

        }
        else
        {
            returnString = "transaction";
        }

        return returnString;
    }

}
