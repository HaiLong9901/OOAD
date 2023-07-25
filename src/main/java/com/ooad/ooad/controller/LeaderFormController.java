package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.entity.Manager;
import com.ooad.ooad.shared.GlobalState;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;

public class LeaderFormController {
    @FXML
    private TableColumn<Department, String> depAddress;

    @FXML
    private TableColumn<Department, Void> delete;

    @FXML
    private AnchorPane depManagementLayout;

    @FXML
    private TableColumn<?, ?> depName;

    @FXML
    private TableView<?> depTable;


    private Thread leaderThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        GlobalState.leaderList = FXCollections.observableArrayList(leaderDao.getAllLeader());
                        setItems(GlobalState.leaderList);
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

    public void displayDepartmentTable() {
        //Khai báo các cột hiển thị dữ liệu
        depAddress.setCellValueFactory(new PropertyValueFactory<Department, String>("adress"));


        //Khai báo các cột chức năng
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

    }
}
