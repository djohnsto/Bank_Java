package com.thoughtworks.bank;
public class Customer {
        public String firstName;
        public String lastName;
        public int ficoScore;
        public int id;

        public Customer(int Id, String FirstName, String LastName, int FicoScore)
        {
            firstName = FirstName;
            lastName = LastName;
            ficoScore = FicoScore;
            id = Id;
        }
}
