package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.entity.Assignment;
import com.ooad.ooad.shared.GlobalState;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.sql.SQLException;
import java.util.Date;

public class AssigmentsFormController {
    @FXML
    private TableColumn<Assignment, Integer> assBill;

    @FXML
    private TableColumn<Assignment, Date> assDate;

    @FXML
    private TableColumn<Assignment, Integer> assEquipment;


    @FXML
    private TableColumn<Assignment, Integer> assEmployee;

    @FXML
    private TableColumn<Assignment, Integer> assManager;

    @FXML
    private TableColumn<Assignment, Integer> assRequest;

    @FXML
    private TableColumn<Assignment, Integer> assState;

    @FXML
    private TableColumn<Assignment, Date> assFinishday;

    @FXML
    private TableColumn<Assignment, Void> assDetail;
    @FXML
    private TableColumn<Assignment, Void> assUpdate;


    private Thread employeeThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        GlobalState.AssignmentsList = FXCollections.observableArrayList(assignmentDao.getAllLRequest());
                        tableView.setItems(GlobalState.AssignmentList);
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

    public void displayEquipmentTable()
    {
        assBill.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("id"));
        assRequest.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("requestId"));
        assManager.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("managerId"));
        assEmployee.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("empId"));
        assState.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("status"));
        assDate.setCellValueFactory(new PropertyValueFactory<Assignment, Date>("createdAt"));
        assFinishday.setCellValueFactory(new PropertyValueFactory<Assignment, Date>("expectation"));
        assEquipment.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("equipid"));

        assDetail.setCellFactory(new Callback<TableColumn<Assignment, Void>, TableCell<Assignment, Void>>() {
            @Override
            public TableCell<Assignment, Void> call(TableColumn<Assignment, Void> assignmentVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Assignment assignment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedAssignment = assignmentDao.getAssignmentByID(assignment.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("CreateAssignmentsForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Thông tin giao việc");
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
}
