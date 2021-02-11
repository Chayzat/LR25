package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    @FXML
    TableView<Numbers> tbl;
    @FXML
    private TableColumn<Numbers, String> k;
    @FXML
    private TableColumn<Numbers, String> y;
    @FXML
    private TextArea t1;
    @FXML
    private TextArea t2;
    @FXML
    private Button b4;
    private final int row = 10;

    ObservableList<Numbers> items = FXCollections.observableArrayList();
    public void b1_ch(ActionEvent actionEvent) {
        cleanUpTable();
        addRandomNums ();
        tbl.refresh();
    }

    public void b2_ch(ActionEvent actionEvent) {
        ArrayList<Double> listNum = new ArrayList<>();
        double result = 0.0;
        double sum = 0;
        double multiplication = 1.0;
        double a = 0.0;
        double b = 0.0;
        double three = 3;
        double resultOperations = 0;
        double theFirstOperation = 0;
        double theSecondOperation = 0;
        double theThirdOperation = 0;
            for (int i = 0; i < items.size(); i++) {
                listNum.add(Double.parseDouble(items.get(i).getN1()));
            }
            a = Double.parseDouble(t1.getText());
            b = Double.parseDouble(t2.getText());
            for (int j = 1; j < listNum.size()+1; j++) {
                sum += j;
                multiplication *= listNum.get(j - 1);
                theFirstOperation = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 3) * Math.pow(Math.cos(listNum.get(j - 1)), 2);
                theSecondOperation = sum - multiplication;
                if (theSecondOperation == 0) {
                    items.get(j-1).setN2("-");
                } else {
                    theThirdOperation = theFirstOperation / theSecondOperation;
                    if (theThirdOperation < 0) {
                        resultOperations = Math.pow(Math.abs(theThirdOperation), 1 / three);
                        result = resultOperations * (-1);
                    } else {
                        resultOperations = Math.pow(theThirdOperation, 1 / three);
                        result = resultOperations;
                    }
                    items.get(j-1).setN2(String.valueOf(result));
                }
            }
        tbl.refresh();
    }
    public void initialize() {
        tbl.itemsProperty().setValue(items);
        tbl.setEditable(true);
        addRandomNums();
        k.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getN1()));
        k.setCellFactory(TextFieldTableCell.forTableColumn());
        k.setOnEditCommit(event -> ((Numbers) event.getTableView().getItems().get(event.getTablePosition().getRow())).setN1(event.getNewValue()));
        y.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getN2()));
    }
    public void b3_ch(ActionEvent actionEvent) {
        for (int i = 0; i < row; i++) {
            items.get(i).setN1("0");
            items.get(i).setN2("");
        }
        t1.setText("0");
        t2.setText("0");
        tbl.refresh();
    }
    public void b4_ch(ActionEvent actionEvent) {
        Stage stage = (Stage) b4.getScene().getWindow();
        stage.close();
    }
    public void cleanUpTable () {
        if (items.size() != 0) {
            for (int i = 0; i < row; i++) {
                items.remove(0);
            }
        }
    }
    public void addRandomNums () {
        double sum = 0.0;
        double multiplication = 1.0;
        Random random1 = new Random();
        Random random2 = new Random();
        int a = random1.nextInt(20)-10;
        int b = random2.nextInt(20)-10;
        t1.setText(String.valueOf(a));
        t2.setText(String.valueOf(b));
        for (int i = 1; i < row+1; i++) {
            double randomNum = Math.random()*100;
            sum += i;
            multiplication *= randomNum;
            items.add(new Numbers(String.valueOf(randomNum), solveTheTaskRandom(randomNum,sum, multiplication, a, b)));
        }
        if (items.get(0).getN1().equals(1)) {
            items.get(0).setN2("-");
        }
    }
    public String solveTheTaskRandom (double num, double sum, double multiplication, int a, int b) {
        String result = "";
        double aNum = a;
        double bNum = b;
        double three = 3;
        double resultOperations = 0;
        double theFirstOperation = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 3) * Math.pow(Math.cos(num), 2);
        double theSecondOperation = sum - multiplication;
        double theThirdOperation = theFirstOperation/theSecondOperation;
        if (theThirdOperation < 0) {
            resultOperations = Math.pow(Math.abs(theThirdOperation), 1/three);
            result = String.valueOf(resultOperations*(-1));
        } else {
            resultOperations = Math.pow(theThirdOperation, 1/three);
            result = String.valueOf(resultOperations);
        }
        return result;
    }
}
