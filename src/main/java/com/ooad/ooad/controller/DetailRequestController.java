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
public class DetailRequestController  {

    @FXML
    private TableColumn<Request, Integer> requestId;
    @FXML
    private TableColumn<Request, Integer> leaderId;
    @FXML
    private TableColumn<Request, String> equipId;
    @FXML
    private TableColumn<Request, Date> createdat;
    @FXML
    private TableColumn<Request, Boolean> isActive;
    @FXML
    private TableColumn<Request, String> descfault;
    @FXML
    private TableColumn<Request, Void> accDetailReport;
    @FXML
    private TableColumn<Request, Void> accAsignment;

    private Thread leaderThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        GlobalState.RequestList = FXCollections.observableArrayList(requestDao.getAllRequest());
                        setItems(GlobalState.RequestList);
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
    public void displayEquipmentTable(){
        requestId.setCellValueFactory(new PropertyValueFactory<Request, Integer>("id"));
        leaderId.setCellValueFactory(new PropertyValueFactory<Request, Integer>("leader"));
        equipId.setCellValueFactory(new PropertyValueFactory<Request, String>("equipId"));
        createdat.setCellValueFactory(new PropertyValueFactory<Request, Date>("createdat"));
        descfault.setCellValueFactory(new PropertyValueFactory<Request, String>("descfault"));
        isActive.setCellValueFactory(new PropertyValueFactory<Request, Boolean>("isEquipActive"));

        accDetailReport.setCellFactory(new Callback<TableColumn<Request, Void>, TableCell<Request, Void>>() {
            @Override
            public TableCell<Request, Void> call(TableColumn<Request, Void> requestVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Request request = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedRequest = requestDao.getRequestByID(request.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailEquipmentForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Thông tin thiết bị");
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

