package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.dao.*;
import com.ooad.ooad.entity.*;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerFormeController implements Initializable {
    private ManagerDao managerDao = new ManagerDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    private EquipmentDao equipmentDao = new EquipmentDao();

    private EmployeeDao employeeDao = new EmployeeDao();

    private LeaderDao leaderDao = new LeaderDao();

    private RequestDao requestDao = new RequestDao();

    private AssignmentDao assignmentDao = new AssignmentDao();

    private BillDao billDao = new BillDao();
    @FXML
    private AnchorPane requestLayout;

    @FXML
    private AnchorPane equipLayout;

    @FXML
    private AnchorPane billLayout;

    @FXML
    private AnchorPane assignLayout;
    @FXML
    private TableColumn<Manager, String> accEmail;

    @FXML
    private TableColumn<Manager, Integer> accId;

    @FXML
    private TableColumn<Manager, String> accName;

    @FXML
    private TableColumn<Manager, String> accPhoneNum;

    @FXML
    private TableColumn<Manager, Void> accDelCol;
    @FXML
    private TableColumn<Manager, Void> accDetailCol;
    @FXML
    private TableColumn<Manager, Void> accUpdateCol;

    @FXML
    private AnchorPane accountManagementLayout;

    @FXML
    private AnchorPane depManagementLayout;

    @FXML
    private AnchorPane adminTaskBar;

    @FXML
    private Button createAccBtn;

    @FXML
    private Button createDepBtn;

    @FXML
    private Label dateHeader;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button manageAccBtn;

    @FXML
    private Button manageDepBtn;

    @FXML
    private Button manageAssignBtn;

    @FXML
    private Button manageBillBtn;

    @FXML
    private Button manageEquipBtn;

    @FXML
    private Button manageReportBtn;

    @FXML
    private ComboBox<String> rolesComboBox;

    @FXML
    private TableView tableView;

    @FXML
    private TableView depTable;

    @FXML
    private TableColumn<Department, String> depAddress;

    @FXML
    private TableColumn<Department, Integer> depId;
    @FXML
    private TableColumn<Department, String> depName;

    @FXML
    private TableColumn<Department, Void> depUpdateCol;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label userNameHeader;

    @FXML
    private Label userNameLabel;

    @FXML
    private TableColumn<Employee, Void> empDel;

    @FXML
    private TableColumn<Employee, Void> empDetail;

    @FXML
    private TableColumn<Employee, String> empEmail;

    @FXML
    private TableColumn<Employee, Integer> empId;

    @FXML
    private TableColumn<Employee, String> empName;

    @FXML
    private TableColumn<Employee, String> empPhone;

    @FXML
    private TableColumn<Employee, Void> empUpdate;

    @FXML
    private TableView<Employee> employeeTable;


    @FXML
    private TableColumn<Leader, Void> leaderDelCol;

    @FXML
    private TableColumn<Leader, Void> leaderDetailCol;

    @FXML
    private TableColumn<Leader, String> leaderEmailCol;

    @FXML
    private TableColumn<Leader, Integer> leaderIdCol;

    @FXML
    private TableColumn<Leader, String> leaderNameCol;

    @FXML
    private TableColumn<Leader, String> leaderPhoneCol;

    @FXML
    private TableView<Leader> leaderTable;

    @FXML
    private TableColumn<Leader, Void> leaderUpdateCol;

    @FXML
    private TableColumn<Equipment, Integer> equipDepIdCol;

    @FXML
    private TableColumn<Equipment, Void> equipDetailCol;

    @FXML
    private TableColumn<Equipment, Date> equipExpiryCol;

    @FXML
    private TableColumn<Equipment, String> equipIdCol;

    @FXML
    private TableColumn<Equipment, String> equipModelCol;

    @FXML
    private TableColumn<Equipment, String> equipNameCol;

    @FXML
    private TableColumn<Equipment, Date> equipPurchaseCol;

    @FXML
    private TableView<Equipment> equipTable;

    @FXML
    private TableColumn<Request, Void> requestAssignCol;

    @FXML
    private TableColumn<Request, Date> requestCreatedAtCol;

    @FXML
    private TableColumn<Request, Void> requestDetailCol;

    @FXML
    private TableColumn<Request, String> requestEquipIdCol;

    @FXML
    private TableColumn<Request, Integer> requestIdCol;

    @FXML
    private TableColumn<Request, Integer> requestLeaderIdCol;

    @FXML
    private TableView<Request> requestTable;

    @FXML
    private TableColumn<Assignment, Date> assignCreatedAtCol;

    @FXML
    private TableColumn<Assignment, Void> assignDetailCol;

    @FXML
    private TableColumn<Assignment, Integer> assignEmpIdCol;

    @FXML
    private TableColumn<Assignment, Integer> assignIdCol;

    @FXML
    private TableColumn<Assignment, Integer> assignRequestIdCol;

    @FXML
    private TableColumn<Assignment, String> assignStatusCol;

    @FXML
    private TableColumn<Assignment, Void> assignUpdateCol;

    @FXML
    private TableView<Assignment> assignmentTable;

    @FXML
    private TableColumn<Bill, Date> billDateCol;

    @FXML
    private TableColumn<Bill, Void> billDetailcol;

    @FXML
    private TableColumn<Bill, String> billEmpCol;

    @FXML
    private TableColumn<Bill, String> billEquimentCol;

    @FXML
    private TableColumn<Bill, Integer> billIdCol;


    @FXML
    private TableView<Bill> billTable;

    @FXML
    private TableColumn<Bill, Long> billTotalcol;


    private AlertMessage alert = new AlertMessage();

    private boolean isManagerThreadActive = true;
    private boolean isLeaderThreadActive = false;
    private boolean isEmployeeThreadActive = false;
    private boolean isAssignThreadActive = false;
    private boolean isRequestThreadActive = false;
    private boolean isEquipThreadActive = false;
    private boolean isBillThreadActive = false;
    private boolean isDepThreadActive = false;

    @FXML
    private void logout() {
        if (alert.confirmationMessage("Bạn muốn đăng xuất tài khoản này ?")) {
            GlobalState.managerId = null;
            managerThread.interrupt();
            leaderThread.interrupt();
            employeeThread.interrupt();
            logoutBtn.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(OOADApplication.class.getResource("login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Đăng nhập");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == manageAccBtn) {
            depManagementLayout.setVisible(false);
            accountManagementLayout.setVisible(true);
            equipLayout.setVisible(false);
            assignLayout.setVisible(false);
            billLayout.setVisible(false);
            requestLayout.setVisible(false);
//            isManagerThreadActive = true;
//            isAssignThreadActive = false;
//            isRequestThreadActive = false;
//            isEquipThreadActive = false;
//            isBillThreadActive = false;
//            isDepThreadActive = false;
            rolesComboBox.setValue("manager");
        } else if (event.getSource() == manageDepBtn) {
            depManagementLayout.setVisible(true);
            accountManagementLayout.setVisible(false);
            equipLayout.setVisible(false);
            assignLayout.setVisible(false);
            billLayout.setVisible(false);
            requestLayout.setVisible(false);
//            isManagerThreadActive = false;
//            isAssignThreadActive = false;
//            isRequestThreadActive = false;
//            isEquipThreadActive = false;
//            isBillThreadActive = false;
//            isDepThreadActive = true;
        } else if (event.getSource() == manageEquipBtn) {
            equipLayout.setVisible(true);
            accountManagementLayout.setVisible(false);
            depManagementLayout.setVisible(false);
            assignLayout.setVisible(false);
            billLayout.setVisible(false);
            requestLayout.setVisible(false);
//            isManagerThreadActive = false;
//            isAssignThreadActive = false;
//            isRequestThreadActive = false;
//            isEquipThreadActive = true;
//            isBillThreadActive = false;
//            isDepThreadActive = false;
        } else if (event.getSource() == manageAssignBtn) {
            assignLayout.setVisible(true);
            accountManagementLayout.setVisible(false);
            depManagementLayout.setVisible(false);
            equipLayout.setVisible(false);
            billLayout.setVisible(false);
            requestLayout.setVisible(false);
//            isManagerThreadActive = false;
//            isAssignThreadActive = true;
//            isRequestThreadActive = false;
//            isEquipThreadActive = false;
//            isBillThreadActive = false;
//            isDepThreadActive = false;
        } else if (event.getSource() == manageBillBtn) {
            billLayout.setVisible(true);
            assignLayout.setVisible(false);
            accountManagementLayout.setVisible(false);
            depManagementLayout.setVisible(false);
            equipLayout.setVisible(false);
            requestLayout.setVisible(false);
//            isManagerThreadActive = false;
//            isAssignThreadActive = false;
//            isRequestThreadActive = false;
//            isEquipThreadActive = false;
//            isBillThreadActive = true;
//            isDepThreadActive = false;
        } else if (event.getSource() == manageReportBtn) {
            requestLayout.setVisible(true);
            billLayout.setVisible(false);
            assignLayout.setVisible(false);
            accountManagementLayout.setVisible(false);
            depManagementLayout.setVisible(false);
            equipLayout.setVisible(false);
//            isManagerThreadActive = false;
//            isAssignThreadActive = false;
//            isRequestThreadActive = true;
//            isEquipThreadActive = false;
//            isBillThreadActive = false;
//            isDepThreadActive = false;
        }

    }

    public void switchAccountTable(ActionEvent event) {
        if (rolesComboBox.getValue() == "manager") {
            tableView.setVisible(true);
            employeeTable.setVisible(false);
            leaderTable.setVisible(false);
            managerThread = new Thread() {
                public void run() {
                    try {
                        while (true) {
                            try {
                                GlobalState.managersList = FXCollections.observableArrayList(managerDao.getAllManager());
                                tableView.setItems(GlobalState.managersList);
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            managerThread.start();
            employeeThread.interrupt();
            leaderThread.interrupt();
        } else if (rolesComboBox.getValue() == "employee") {
            tableView.setVisible(false);
            employeeTable.setVisible(true);
            leaderTable.setVisible(false);
            employeeThread = new Thread() {

                public void run() {
                    try {
                        while (true) {
                            try {
                                ObservableList<Employee> employeeList = FXCollections.observableArrayList(employeeDao.getAllEmployee());
                                employeeTable.setItems(employeeList);
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            employeeThread.start();
            managerThread.interrupt();
            leaderThread.interrupt();
        }
        else if (rolesComboBox.getValue() == "leader") {
            tableView.setVisible(false);
            employeeTable.setVisible(false);
            leaderTable.setVisible(true);
            managerThread.interrupt();
            employeeThread.interrupt();
            leaderThread = new Thread() {
                public void run() {
                    try {
                        while (true) {
                            try {
                                ObservableList<Leader> leaderList = FXCollections.observableArrayList(leaderDao.getAllLeader());
                                leaderTable.setItems(leaderList);
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            leaderThread.start();
        }

    }

    private Thread managerThread = new Thread() {

        public void run() {
            try {
                while (true) {
                        try {
                            ObservableList<Manager> managerList = FXCollections.observableArrayList(managerDao.getAllManager());
                            tableView.setItems(managerList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Thread employeeThread = new Thread() {
        public void run() {
            try {
                while (true) {
                        try {
                            List<Employee> test = employeeDao.getAllEmployee();
                            test.forEach(employee -> System.out.println(employee.toString()));
                            ObservableList<Employee> employeeList = FXCollections.observableArrayList(employeeDao.getAllEmployee());
                            employeeTable.setItems(employeeList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Thread leaderThread = new Thread() {
        public void run() {
            try {
                while (true) {
                        try {
                            ObservableList<Leader> leaderList = FXCollections.observableArrayList(leaderDao.getAllLeader());
                            leaderTable.setItems(leaderList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Thread equipThread = new Thread() {

        public void run() {
            try {
                while (true) {
                        try {
                            ObservableList<Equipment> equipList = FXCollections.observableArrayList(equipmentDao.getAllEquipment());
                            equipTable.setItems(equipList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public Thread requestThread = new Thread() {

        public void run() {
            try {
                while (true) {
                        try {
                            ObservableList<Request> requestList = FXCollections.observableArrayList(requestDao.getAllRequest());
                            requestTable.setItems(requestList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public Thread assignmentThread = new Thread() {

        public void run() {
            try {
                while (true) {
                        try {
                            ObservableList<Assignment> assignmentList = FXCollections.observableArrayList(assignmentDao.getAllAssignment());
                            assignmentTable.setItems(assignmentList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public Thread billThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        ObservableList<Bill> billList = FXCollections.observableArrayList(billDao.getAllBill());
                        billTable.setItems(billList);
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public void displayAdminAccount() {
        try {
            Manager manager = managerDao.getManagerByPhone(GlobalState.managerPhone);
            if (manager == null) {
                return;
            }
            userNameHeader.setText(manager.getName());
            userNameLabel.setText(manager.getName());
            userIdLabel.setText(Integer.toString(manager.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void redirectToCreateAccForm() {
        try {
            Parent root = FXMLLoader.load(OOADApplication.class.getResource("CreateAccountForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tạo tài khoản");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToCreateDepForm() {
        try {
            Parent root = FXMLLoader.load(OOADApplication.class.getResource("CreateDepartmentForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Thêm phòng ban");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayManagerTable() {
        accId.setCellValueFactory(new PropertyValueFactory<Manager, Integer>("id"));
        accName.setCellValueFactory(new PropertyValueFactory<Manager, String>("name"));
        accEmail.setCellValueFactory(new PropertyValueFactory<Manager, String>("email"));
        accPhoneNum.setCellValueFactory(new PropertyValueFactory<Manager, String>("phone"));
        accDetailCol.setCellFactory(new Callback<TableColumn<Manager, Void>, TableCell<Manager, Void>>() {
            @Override
            public TableCell<Manager, Void> call(TableColumn<Manager, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Manager manager = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedManager = managerDao.getManagerByPhone(manager.getPhone());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailManagerForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Thông tin quản lý");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        accDelCol.setCellFactory(new Callback<TableColumn<Manager, Void>, TableCell<Manager, Void>>() {
            @Override
            public TableCell<Manager, Void> call(TableColumn<Manager, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xóa");
                    {
                        button.setOnAction(event -> {
                            Manager manager = getTableView().getItems().get(getIndex());
                            alert.confirmationMessage("Bạn có muốn xóa tài khoản này ?");
                            try {
                                managerDao.deleteManager(manager.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        accUpdateCol.setCellFactory(new Callback<TableColumn<Manager, Void>, TableCell<Manager, Void>>() {
            @Override
            public TableCell<Manager, Void> call(TableColumn<Manager, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Cập nhật");
                    {
                        button.setOnAction(event -> {
                            Manager manager = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedManager = managerDao.getManagerByPhone(manager.getPhone());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("UpdateManagerForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Cập nhật quản lý");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }

    public void displayEmployeeTable() {
        empId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        empName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        empEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        empPhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        empDetail.setCellFactory(new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(TableColumn<Employee, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            System.out.println("test: " + employee.toString());
                            GlobalState.selectedEmployee = employee;
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailEmployeeForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Thông tin quản lý");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        empDel.setCellFactory(new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(TableColumn<Employee, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xóa");
                    {
                        button.setOnAction(event -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            if (alert.confirmationMessage("Bạn có muốn xóa tài khoản này ?")) {
                                try {
                                    employeeDao.deleteEmployee(employee.getId());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        empUpdate.setCellFactory(new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(TableColumn<Employee, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Cập nhật");
                    {
                        button.setOnAction(event -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedEmployee = employeeDao.getEmployeeByPhone(employee.getPhone());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("UpdateEmployeeForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Cập nhật nhân viên");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }

    public void displayLeaderTable() {
        leaderIdCol.setCellValueFactory(new PropertyValueFactory<Leader, Integer>("id"));
        leaderNameCol.setCellValueFactory(new PropertyValueFactory<Leader, String>("name"));
        leaderEmailCol.setCellValueFactory(new PropertyValueFactory<Leader, String>("email"));
        leaderPhoneCol.setCellValueFactory(new PropertyValueFactory<Leader, String>("phone"));
        leaderDetailCol.setCellFactory(new Callback<TableColumn<Leader, Void>, TableCell<Leader, Void>>() {
            @Override
            public TableCell<Leader, Void> call(TableColumn<Leader, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Leader leader = getTableView().getItems().get(getIndex());
//                            try {
//                                GlobalState.selectedManager = managerDao.getManagerByPhone(manager.getPhone());
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailManagerForm.fxml"));
//                                Stage stage = new Stage();
//                                stage.setTitle("Thông tin quản lý");
//                                stage.setScene(new Scene(root));
//                                stage.show();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        leaderDelCol.setCellFactory(new Callback<TableColumn<Leader, Void>, TableCell<Leader, Void>>() {
            @Override
            public TableCell<Leader, Void> call(TableColumn<Leader, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xóa");
                    {
                        button.setOnAction(event -> {
                            Leader leader = getTableView().getItems().get(getIndex());
                            if (alert.confirmationMessage("Bạn có muốn xóa tài khoản này ?")) {
                                try {
                                    leaderDao.deleteLeader(leader.getId());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        leaderUpdateCol.setCellFactory(new Callback<TableColumn<Leader, Void>, TableCell<Leader, Void>>() {
            @Override
            public TableCell<Leader, Void> call(TableColumn<Leader, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Cập nhật");
                    {
                        button.setOnAction(event -> {
//                            Manager manager = getTableView().getItems().get(getIndex());
//                            try {
//                                GlobalState.selectedManager = managerDao.getManagerByPhone(manager.getPhone());
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("UpdateManagerForm.fxml"));
//                                Stage stage = new Stage();
//                                stage.setTitle("Cập nhật quản lý");
//                                stage.setScene(new Scene(root));
//                                stage.show();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }

    public void displayEquipTable() {
        equipIdCol.setCellValueFactory(new PropertyValueFactory<Equipment, String>("id"));
        equipNameCol.setCellValueFactory(new PropertyValueFactory<Equipment, String>("name"));
        equipModelCol.setCellValueFactory(new PropertyValueFactory<Equipment, String>("model"));
        equipPurchaseCol.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("purchase"));
        equipDepIdCol.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("depId"));
        equipExpiryCol.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("expiry"));
        equipDetailCol.setCellFactory(new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(TableColumn<Equipment, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Equipment equipment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedEquipment = equipmentDao.getEquipmentById(equipment.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailEquipForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Chi tiết thiết bị");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }

    public void displayDepartmentTable() {
        depId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
        depName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        depAddress.setCellValueFactory(new PropertyValueFactory<Department, String>("address"));
        depUpdateCol.setCellFactory(new Callback<TableColumn<Department, Void>, TableCell<Department, Void>>() {
            @Override
            public TableCell<Department, Void> call(TableColumn<Department, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Cập nhật");
                    {

                        button.setOnAction(event -> {

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
        try {
            ObservableList data = FXCollections.observableArrayList(departmentDao.getAllDepartment());
            depTable.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread() {

            public void run() {
                try {
                    while (true) {
                        try {
                            ObservableList data = FXCollections.observableArrayList(departmentDao.getAllDepartment());
                            depTable.setItems(data);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    public void displayAssignmentTable() {
        assignIdCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("id"));
        assignRequestIdCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("requestId"));
        assignEmpIdCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("empId"));
        assignCreatedAtCol.setCellValueFactory(new PropertyValueFactory<Assignment, Date>("createdAt"));
        assignStatusCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getStatus() == 1) {
                return  javafx.beans.binding.Bindings.createStringBinding(() -> "Chờ nhận việc");
            } else if (cellData.getValue().getStatus() == 2) {
                return javafx.beans.binding.Bindings.createStringBinding(() -> "Đang thực hiện");
            } else if (cellData.getValue().getStatus() == 3) {
                return javafx.beans.binding.Bindings.createStringBinding(() -> "Đã hoàn thành");
            } else if (cellData.getValue().getStatus() == 4) {
                return javafx.beans.binding.Bindings.createStringBinding(() -> "Nhân viên bận");
            } else if (cellData.getValue().getStatus() == 5) {
                return javafx.beans.binding.Bindings.createStringBinding(() -> "Hủy");
            }
            return null;
        });
        assignDetailCol.setCellFactory(new Callback<TableColumn<Assignment, Void>, TableCell<Assignment, Void>>() {
            @Override
            public TableCell<Assignment, Void> call(TableColumn<Assignment, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Assignment assignment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedAssignment = assignmentDao.getAssignmentById(assignment.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailAssignmentForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Chi tiết phiếu giao việc");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

        assignUpdateCol.setCellFactory(new Callback<TableColumn<Assignment, Void>, TableCell<Assignment, Void>>() {
            @Override
            public TableCell<Assignment, Void> call(TableColumn<Assignment, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Cập nhật");
                    {

                        button.setOnAction(event -> {
                            Assignment assignment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedAssignment = assignmentDao.getAssignmentById(assignment.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("UpdateAssignmentForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Cập nhập trạng thái phiếu giao việc");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }

    public void displayReqestTable() {
        requestIdCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("id"));
        requestEquipIdCol.setCellValueFactory(new PropertyValueFactory<Request, String>("equipId"));
        requestLeaderIdCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("leader"));
        requestCreatedAtCol.setCellValueFactory(new PropertyValueFactory<Request, Date>("createdAt"));
        requestDetailCol.setCellFactory(new Callback<TableColumn<Request, Void>, TableCell<Request, Void>>() {
            @Override
            public TableCell<Request, Void> call(TableColumn<Request, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Request request = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedRequest = requestDao.getRequestById(request.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailReqForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Chi tiết báo cáo hỏng hóc");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

        requestAssignCol.setCellFactory(new Callback<TableColumn<Request, Void>, TableCell<Request, Void>>() {
            @Override
            public TableCell<Request, Void> call(TableColumn<Request, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Giao việc");
                    {
                        button.setOnAction(event -> {
                            Request request = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedRequest = requestDao.getRequestById(request.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("AssignRequestForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Phiếu giao việc");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }

    public void displayBillTable() {
        billIdCol.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
        billDateCol.setCellValueFactory(new PropertyValueFactory<Bill, Date>("createdAt"));
        billEquimentCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("eqipId"));
        billTotalcol.setCellValueFactory(new PropertyValueFactory<Bill,Long>("total"));
        billEmpCol.setCellValueFactory(celldata -> {
            try {
                Bill bill = celldata.getValue();
                Employee employee = employeeDao.getEmployeeById(bill.getEmpId());

                return javafx.beans.binding.Bindings.createStringBinding(() -> employee.getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        billDetailcol.setCellFactory(new Callback<TableColumn<Bill, Void>, TableCell<Bill, Void>>() {
            @Override
            public TableCell<Bill, Void> call(TableColumn<Bill, Void> billVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Bill bill = getTableView().getItems().get(getIndex());
                            GlobalState.selectedBillDetail = bill;
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailBillForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Chi tiết hóa đơn");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        ObservableList<String> roleList = FXCollections.observableArrayList("manager", "leader", "employee");
        rolesComboBox.setItems(roleList);
        rolesComboBox.setValue("manager");
        managerThread.start();
        equipThread.start();
        requestThread.start();
        assignmentThread.start();
        billThread.start();
        displayAdminAccount();
        displayManagerTable();
        displayEmployeeTable();
        displayLeaderTable();
        displayEquipTable();
        displayDepartmentTable();
        displayReqestTable();
        displayAssignmentTable();
        displayBillTable();
    }
}
