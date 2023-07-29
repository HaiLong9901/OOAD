package com.ooad.ooad.shared;

import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.entity.Leader;
import com.ooad.ooad.entity.Manager;
import javafx.collections.ObservableList;

public class GlobalState {
    public static String managerPhone;
    public static Integer managerId;
    public static ObservableList<Manager> managersList;
    public static Manager selectedManager;

    public static Equipment selectedEquipment;

    public static Leader loggedinLeader;

//    public static Thread managerThread =
}
