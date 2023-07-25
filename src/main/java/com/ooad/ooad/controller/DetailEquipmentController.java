package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.entity.Manager;
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
public class DetailEquipmentController  {

    @FXML
    private TableColumn<Equipment, String> equipmentId;
    @FXML
    private TableColumn<Equipment, String> equipmentName;
    @FXML
    private TableColumn<Equipment, String> model;
    @FXML
    private TableColumn<Equipment, Date> purchase;
    @FXML
    private TableColumn<Equipment, Date> expiry;
    @FXML
    private TableColumn<Equipment, Integer> price;
    @FXML
    private TableColumn<Equipment, Integer> depId;
    @FXML
    private TableColumn<Equipment, Boolean> isActive;
    @FXML
    private TableColumn<Equipment, Void> accDetailCol;


    private Thread leaderThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        GlobalState.EquipmentList = FXCollections.observableArrayList(equipmentDao.getAllEquipment());
                        setItems(GlobalState.EquipmentList);
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
        equipmentId.setCellValueFactory(new PropertyValueFactory<Equipment, String>("id"));
        equipmentName.setCellValueFactory(new PropertyValueFactory<Equipment, String>("name"));
        model.setCellValueFactory(new PropertyValueFactory<Equipment, String>("model"));
        purchase.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("purchase"));
        expiry.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("expiry"));
        price.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("price"));
        depId.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("depId"));
        isActive.setCellValueFactory(new PropertyValueFactory<Equipment, Boolean>("isActive"));

        accDetailCol.setCellFactory(new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(TableColumn<Equipment, Void> managerVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Equipment equipment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedEquipment = equipmentDao.getEquipmentByID(equipment.getId());
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

