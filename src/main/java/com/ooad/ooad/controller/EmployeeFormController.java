package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.dao.AssignmentDao;
import com.ooad.ooad.dao.ManagerDao;
import com.ooad.ooad.entity.Assignment;
import com.ooad.ooad.entity.Manager;
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
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    @FXML
    private AnchorPane adminTaskBar;

    @FXML
    private TableColumn<Assignment, Date> assignCreatedCol;

    @FXML
    private TableColumn<Assignment, Void> assignDetailCol;

    @FXML
    private TableColumn<Assignment, Integer> assignIdCol;

    @FXML
    private AnchorPane assignLayout;

    @FXML
    private TableColumn<Assignment, String> assignManagerCol;

    @FXML
    private TableColumn<Assignment, Integer> assignRequestIdCol;

    @FXML
    private TableColumn<Assignment, String> assignStatusCol;

    @FXML
    private TableView<Assignment> assignTable;

    @FXML
    private TableColumn<Assignment, Void> assignUpdateCol;

    @FXML
    private Label assignid;

    @FXML
    private TableColumn<?, ?> billDateCol;

    @FXML
    private TableColumn<?, ?> billDetailCol;

    @FXML
    private TableColumn<?, ?> billEquimentCol;

    @FXML
    private TableColumn<?, ?> billIdCol;

    @FXML
    private AnchorPane billLayout;

    @FXML
    private TableView<?> billTable;

    @FXML
    private TableColumn<?, ?> billTotalCol;

    @FXML
    private TableColumn<?, ?> billUpdateCol;

    @FXML
    private Label billsId;

    @FXML
    private Button createBillBtn;

    @FXML
    private Label dateHeader;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button manageAssignBtn;

    @FXML
    private Button manageBillBtn;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label userNameHeader;

    @FXML
    private Label userNameLabel;

    private AssignmentDao assignmentDao = new AssignmentDao();

    private ManagerDao managerDao = new ManagerDao();

    private AlertMessage alert = new AlertMessage();

    public Thread assignmentThread = new Thread() {

        public void run() {
            try {
                while (true) {

                    if (isAssignThreatActive) {
                        System.out.println("Running");
                        try {
                            ObservableList<Assignment> assignmentList = FXCollections.observableArrayList(assignmentDao.getAssignmentsByEmployeeId(GlobalState.loggedInEmployee.getId()));
                            assignTable.setItems(assignmentList);
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void switchForm(ActionEvent event) {
        if (event.getSource() == manageAssignBtn) {
            setAssignThreatActive(false);
        } else if (event.getSource() == manageBillBtn) {
            setAssignThreatActive(true);
        }
    }

    public void logout() {
        if (alert.confirmationMessage("Bạn muốn đăng xuất tài khoản này ?")) {
            GlobalState.loggedInEmployee = null;
            assignmentThread.interrupt();
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

    private boolean isAssignThreatActive = true;

    public boolean isAssignThreatActive() {
        return isAssignThreatActive;
    }

    public void setAssignThreatActive(boolean assignThreatActive) {
        isAssignThreatActive = assignThreatActive;
    }

    public void displayAssignmentTable() {
        assignIdCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("id"));
        assignRequestIdCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("requestId"));
        assignCreatedCol.setCellValueFactory(new PropertyValueFactory<Assignment, Date>("createdAt"));
        assignManagerCol.setCellValueFactory(celldata -> {
            try {
                Manager manager = managerDao.getManagerById(celldata.getValue().getManagerId());
                return javafx.beans.binding.Bindings.createStringBinding(() -> manager.getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
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
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailAssignmentEmployee.fxml"));
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameHeader.setText(GlobalState.loggedInEmployee.getName());
        userNameLabel.setText(GlobalState.loggedInEmployee.getName());
        userIdLabel.setText(Integer.toString(GlobalState.loggedInEmployee.getId()));
        displayAssignmentTable();
        assignmentThread.start();
    }
}
