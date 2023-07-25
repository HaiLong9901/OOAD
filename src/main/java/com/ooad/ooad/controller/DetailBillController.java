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
public class DetailBillController  {

    @FXML
    private TableColumn<Bill, Integer> billId;
    @FXML
    private TableColumn<Bill, Integer> EmployId;
    @FXML
    private TableColumn<Bill, String> EquipmentId;
    @FXML
    private TableColumn<Bill, Date> CreatedAt;
    @FXML
    private TableColumn<Bill, Long> totalBill;


    @FXML
    private TableColumn<Bill, Void> accDetailBill;


    private Thread  billThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        GlobalState.BillList = FXCollections.observableArrayList(billDao.getAllBill());
                        setItems(GlobalState.BillList);
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
    public void displayBillTable(){
        billId.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
        EmployId.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("employeeid"));
        EquipmentId.setCellValueFactory(new PropertyValueFactory<Bill, String>("equipid"));
        CreatedAt.setCellValueFactory(new PropertyValueFactory<Bill, Date>("createdat"));
        totalBill.setCellValueFactory(new PropertyValueFactory<Bill, Long>("total"));


        accDetailBill.setCellFactory(new Callback<TableColumn<Bill, Void>, TableCell<Bill, Void>>() {
            @Override
            public TableCell<Bill, Void> call(TableColumn<Bill, Void> billVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Bill bill = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedBill = billDao.getBillByID(bill.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailBillForm.fxml"));
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

