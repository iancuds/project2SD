package com.spring.presentation.service;

import java.io.Serializable;

public class TeacherDTO implements Serializable {



        private String email;
        private String name;
        private String token;
        private String passwd;

        public TeacherDTO()
        {}
        public TeacherDTO(String e, String n, String t, String p)
        {
            this.email=e;
            this.name=n;
            this.passwd=p;
            this.token=t;

        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        @Override
        public String toString() {
            return "TeacherDTO{" +
                    "email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    ", token='" + token + '\'' +
                    ", passwd='" + passwd + '\'' +
                    '}';
        }







}



