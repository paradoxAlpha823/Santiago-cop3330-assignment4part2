/*
 *  UCF COP3330 Fall 2021 Assignment 4 Part 2 Solution
 *  Copyright 2021 Diego Santiago
 */

package ucf.assignments.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import ucf.assignments.database.ItemData;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteAllButton;

    @FXML
    private Button displayDone;

    @FXML
    private Button displayNotDone;

    @FXML
    private DatePicker itemDateField;

    @FXML
    private ToggleButton finishButton;

    @FXML
    private TextField itemDescriptionField;

    @FXML
    private TextField itemTitleField;

    @FXML
    private TableView<ItemData.Task> itemsList;

    @FXML
    private TableColumn<ItemData.Task, String> titleColumn;

    @FXML
    private TableColumn<ItemData.Task, String> dateColumn;

    @FXML
    private TableColumn<ItemData.Task, String> descColumn;



    ObservableList<ItemData.Task> parts = FXCollections.observableArrayList((new ItemData.Task("", "", "", false)));

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


        //Title Column controls

        titleColumn.setCellValueFactory(new PropertyValueFactory<ItemData.Task, String>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ItemData.Task, String>>()
                                    {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<ItemData.Task, String> event)
                                        {
                                            ItemData.Task etask = event.getRowValue();
                                            etask.setTitle(event.getNewValue());
                                            etask.setComplete(finishButton.isSelected());
                                        }
                                    }
        );

        //Date Column controls
        dateColumn.setCellValueFactory(new PropertyValueFactory<ItemData.Task, String>("date"));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ItemData.Task, String>>()
                                    {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<ItemData.Task, String> event)
                                        {
                                            ItemData.Task etask = event.getRowValue();
                                            etask.setDate(event.getNewValue());
                                            etask.setComplete(finishButton.isSelected());
                                        }
                                    }
        );

        descColumn.setCellValueFactory(new PropertyValueFactory<ItemData.Task, String>("desc"));
        descColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ItemData.Task, String>>()
                                    {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<ItemData.Task, String> event)
                                        {
                                            ItemData.Task etask = event.getRowValue();
                                            etask.setDesc(event.getNewValue());
                                            etask.setComplete(finishButton.isSelected());
                                        }
                                    }
        );

        itemsList.setItems(parts);
        itemsList.setEditable(true);


        //add item button controls
        addItemButton.setOnAction(event -> {
            ItemData.Task task = new ItemData.Task();
            task.setTitle(itemTitleField.getText());
            task.setDate(String.valueOf(itemDateField.getValue()));
            task.setDesc(itemDescriptionField.getText());
            task.setComplete(finishButton.isSelected());
            itemsList.getItems().add(task);
            itemTitleField.clear();
            itemDescriptionField.clear();
            finishButton.setSelected(false);
        });

        //delete item button controls
        deleteButton.setOnAction(event -> {
            ObservableList<ItemData.Task> itemSelect, allItems;
            allItems = itemsList.getItems();
            itemSelect = itemsList.getSelectionModel().getSelectedItems();
            itemSelect.forEach(allItems::remove);
        });

        //clear table button controls
        deleteAllButton.setOnAction(event ->{
            for ( int i = 0; i<itemsList.getItems().size(); i++) {
                itemsList.getItems().clear();
            }
        });

        //save button controls
        saveButton.setOnAction(event->{
            Writer writer = null;
            try {
                File file = new File("C:\\temp\\list.csv");
                writer = new BufferedWriter(new FileWriter(file));
                for (ItemData.Task list : parts) {

                    String text = list.getTitle() + "," + list.getDate() + "," + list.getDesc() + ","+ list.getComplete()+ "\n";
                    writer.write(text);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {

                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //load button controls
        loadButton.setOnAction(event->{
            BufferedReader reader = null;
            String file = "C:\\temp\\list.csv";
            try{
                reader = new BufferedReader(new FileReader((file)));
                String line = "";
                String FieldDelimiter = ",";
                while ((line = reader.readLine()) != null)
                {
                    itemsList.setItems((ObservableList<ItemData.Task>) reader);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
