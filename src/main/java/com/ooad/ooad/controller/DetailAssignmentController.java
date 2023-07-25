package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.entity.*;
import com.ooad.ooad.shared.GlobalState;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class DetailAssignmentController  {

    @FXML
    private TableColumn<Assignment, Integer> assignId;
    @FXML
    private TableColumn<Assignment, Integer> requestId;
    @FXML
    private TableColumn<Assignment, Integer> managerId;
    @FXML
    private TableColumn<Assignment, String> equipmentId;
    @FXML
    private TableColumn<Assignment, Integer> employId;
    @FXML
    private TableColumn<Assignment, Date> createdAt;
    @FXML
    private TableColumn<Assignment, Date> expectation;
    @FXML
    private TableColumn<Assignment, Integer> status;
    @FXML
    private TableColumn<Assignment, Void> accDetailRequest;
    @FXML
    private TableColumn<Assignment, Void> accUpdateRequest;

    private Thread leaderThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        GlobalState.AssignmentList = FXCollections.observableArrayList(assignmentDao.getAllRequest());
                        setItems(GlobalState.AssignmentList);
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
    public void displayAssignmentTable(){
        assignId.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("id"));
        requestId.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("requestid"));
        managerId.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("managerid"));
        equipmentId.setCellValueFactory(new PropertyValueFactory<Assignment, String>("equipid"));
        employId.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("employeeid"));
        createdAt.setCellValueFactory(new PropertyValueFactory<Assignment, Date>("createdat"));
        expectation.setCellValueFactory(new PropertyValueFactory<Assignment, Date>("expectation"));
        status.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("status"));

        accDetailRequest.setCellFactory(new Callback<TableColumn<Assignment, Void>, TableCell<Assignment, Void>>() {
            @Override
            public TableCell<Assignment, Void> call(TableColumn<Assignment, Void> assignmentVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Assignment assignment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedAssignment = asignmentDao.getAssignmentByID(assignment.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetaiAssignment.fxml"));
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

