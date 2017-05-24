package com.example.florian.exam_240517;

/**
 * Created by florian on 24/05/2017.
 */

public class Object_Classes {
    public static class Contacts{
        private String C_id;
        private String C_name;
        private String C_email;
        private String C_adress;
        private String C_gender;

        public String getId(){return C_id;}
        public void setId(String nv){ this.C_id = nv;}

        public String getCName(){return C_name;}
        public void setCName(String nv){ this.C_name = nv;}

        public String getMail(){return C_email;}
        public void setMail(String nv){ this.C_email = nv;}

        public String getAdress(){return C_adress;}
        public void setAdress(String nv){ this.C_adress = nv;}

        public String getGender(){return C_gender;}
        public void setGender(String nv){ this.C_gender = nv;}

    }
}
