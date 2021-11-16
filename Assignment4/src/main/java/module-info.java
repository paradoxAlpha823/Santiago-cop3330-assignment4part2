/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Diego Santiago
 */

module ucf.assignments {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    exports ucf.assignments;
    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments.controllers;
    opens ucf.assignments.controllers to javafx.fxml;
    opens ucf.assignments.database to javafx.fxml, javafx.base;

}