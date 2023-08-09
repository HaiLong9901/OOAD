package com.ooad.ooad.shared;

import com.ooad.ooad.entity.*;
import javafx.collections.ObservableList;

public class GlobalState {
    public static String managerPhone;
    public static Integer managerId;
    public static ObservableList<Manager> managersList;
    public static Manager selectedManager;

    public static Manager loggedInManager;

    public static Equipment selectedEquipment;

    public static Leader loggedinLeader;

    public static Request selectedRequest;

    public static Assignment selectedAssignment;

//    public static Thread managerThread =
    public static Employee loggedInEmployee;
}
