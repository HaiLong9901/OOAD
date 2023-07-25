package com.ooad.ooad.controller;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.entity.Bill;
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

public class DetailBillController {

    @FXML
    private TableColumn<Bill, Date> billDate;

    @FXML
    private TableColumn<Bill, Void> billDetails;

    @FXML
    private TableColumn<Bill, Integer> billEquiment;

    @FXML
    private TableColumn<Bill, Integer> billName;


    @FXML
    private TableColumn<Bill, Long> billTotalcost;

    @FXML
    private TableColumn<Bill, Integer> billid;
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
        billid.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
        billName.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("employeeid"));
        billEquiment.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("equipid"));
        billDate.setCellValueFactory(new PropertyValueFactory<Bill, Date>("createdat"));
        billTotalcost.setCellValueFactory(new PropertyValueFactory<Bill, Long>("total"));


        billDetails.setCellFactory(new Callback<TableColumn<Bill, Void>, TableCell<Bill, Void>>() {
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
                                Parent root = FXMLLoader.load(OOADApplication.class.getResource("CreateBillForm.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Thông tin hóa đơn");
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


