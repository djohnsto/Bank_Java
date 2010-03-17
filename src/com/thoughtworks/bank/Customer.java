package com.thoughtworks.bank;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 15, 2010
 * Time: 5:13:54 PM
 * To change this template use File | Settings | File Templates.
 */
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
