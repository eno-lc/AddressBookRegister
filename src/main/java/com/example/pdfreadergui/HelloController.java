package com.example.pdfreadergui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import java.text.AttributedString;


public class HelloController extends HelloApplication {
    public TextField name;
    public TextField lastname;
    public TextField street;
    public TextField city;
    public TextField state;
    public TextArea displayArea;
    String user = "root";
    String password = "133708enis";
    String url = "jdbc:mysql://localhost:3306/addressbook";

    private double x= 0;
    private double y= 0;

    @FXML
    Button xBtn1;

    @FXML
    AnchorPane background;

    @FXML
    AnchorPane titlebar;




    public void createInfo(ActionEvent event) {
        try{
            Connection connector = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connector.prepareStatement(
                    "INSERT INTO addressadder (name,lastname,street,city,state) VALUE (?,?,?,?,?)");
            preparedStatement.setString(1,name.getText());
            preparedStatement.setString(2,lastname.getText());
            preparedStatement.setString(3, street.getText());
            preparedStatement.setString(4, city.getText());
            preparedStatement.setString(5, state.getText());

            preparedStatement.executeUpdate();
            System.out.println("succes");
            connector.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void readInfo(){
        try {
            Connection connector = DriverManager.getConnection(url, user, password);
            String read = "select * from addressadder";
            Statement statement = connector.createStatement();
            ResultSet result = statement.executeQuery(read);

            while(result.next()) {
                displayArea.appendText("\n Name: ");
                displayArea.appendText(result.getString("name"));

                displayArea.appendText("\n Last Name: ");
                displayArea.appendText(result.getString("lastname"));

                displayArea.appendText("\n Street: ");
                displayArea.appendText(result.getString("street"));

                displayArea.appendText("\n City: ");
                displayArea.appendText(result.getString("city"));

                displayArea.appendText("\n State: ");
                displayArea.appendText(result.getString("State"));
                displayArea.appendText("\n----------------------------------------");
            }
            connector.close();
        }catch (SQLException e) {
                e.printStackTrace();
        }

    }

    public void updateInfo(){
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            try {
                String updateCase = "UPDATE addressadder SET " +
                        "name = '" + name.getText() + "', lastname = '" + lastname.getText()
                        + "', street = '" + street.getText()
                        + "', city = '" + city.getText()
                        + "', state = '" + state.getText()
                        + "' WHERE name = '" + name.getText() + "'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(updateCase);
                readInfo();

            }catch(SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e1){
            e1.printStackTrace();
        }
    }

    public void deleteInfo(){
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            try {
                String deleteInfo = "delete from addressadder where name = '" +name.getText() + "'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(deleteInfo);
               displayArea.appendText("\nAfter Delete Process: ");
               displayArea.appendText("\n----------------------------------------");
                updateInfo();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e1){
            e1.printStackTrace();
        }

    }

    public void clearArea(){
        displayArea.setText("");
    }


    @FXML
    private void anchorpane_dragged(MouseEvent event){
        Stage stage =  (Stage) titlebar.getScene().getWindow();
        stage.setY(event.getScreenY() - y);
        stage.setX(event.getScreenX() - x);
    }
    @FXML
    private void anchorpane_pressed(MouseEvent event){
        x = event.getSceneX();
        y = event.getSceneY();
    }


    @FXML
    private void exit(){
        Stage stage = (Stage) background.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Minimize_clicked(MouseEvent event) {
        Stage stage = (Stage) xBtn1.getScene().getWindow();
        stage.setIconified(true);
    }








}