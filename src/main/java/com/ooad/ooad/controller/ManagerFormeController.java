package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.dao.DepartmentDao;
import com.ooad.ooad.dao.ManagerDao;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.entity.Manager;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.application.Platform;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ManagerFormeController implements Initializable {
    private ManagerDao managerDao = new ManagerDao();
    private DepartmentDao departmentDao = new DepartmentDao();
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

    private AlertMessage alert = new AlertMessage();

    public void switchForm(ActionEvent event) {
        System.out.println(event.getSource().toString());
        if (event.getSource() == manageAccBtn) {
            depManagementLayout.setVisible(false);
            accountManagementLayout.setVisible(true);
            System.out.println("click acc");
        } else if (event.getSource() == manageDepBtn) {
            depManagementLayout.setVisible(true);
            accountManagementLayout.setVisible(false);
            System.out.println("click dep");
        }

    }

    public void switchAccountTable(ActionEvent event) {
        if (rolesComboBox.getValue() == "manager") {
            tableView.setVisible(true);
            employeeTable.setVisible(false);
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
        } else if (rolesComboBox.getValue() == "employee") {
            tableView.setVisible(false);
            employeeTable.setVisible(true);
            managerThread.interrupt();

        }
    }

    private Thread managerThread = new Thread() {

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
        empDel.setCellFactory(new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(TableColumn<Employee, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xóa");
                    {
                        button.setOnAction(event -> {
//                            Manager manager = getTableView().getItems().get(getIndex());
//                            alert.confirmationMessage("Bạn có muốn xóa tài khoản này ?");
//                            try {
//                                managerDao.deleteManager(manager.getId());
//                            } catch (SQLException e) {
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
        empUpdate.setCellFactory(new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(TableColumn<Employee, Void> managerVoidTableColumn) {
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

    public void displayDepartmentTable() {
        depId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
        depName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        depAddress.setCellValueFactory(new PropertyValueFactory<Department, String>("address"));
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
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
//        if (accountManagementLayout.isVisible()) {
//            runTime();
//        }
        managerThread.start();
        ObservableList<String> roleList = FXCollections.observableArrayList("manager", "leader", "employee");
        rolesComboBox.setItems(roleList);
        rolesComboBox.setValue("manager");
        displayAdminAccount();
        displayManagerTable();
        displayDepartmentTable();
    }
}
