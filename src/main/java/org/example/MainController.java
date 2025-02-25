package org.example;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainController {

    @FXML
    private TextField Destname;
    @FXML
    private TextField originename;
    @FXML
    private TextField routeid_field;
    @FXML
    private VBox mainLayout; // Inject the main layout container



    private Firestore firestore;

    public void initialize() throws Exception {
        FirestoreService firestoreService = new FirestoreService();
        firestore = firestoreService.getFirestore();
        loadMap(); // Call this if you want to load the map on initialization




    }

    @FXML
    private void loadMap() {
        try {
            if (mainLayout == null) {
                System.err.println("mainLayout is null! Cannot load map.");
                return;
            }

            System.out.println("Loading map.fxml from: " + getClass().getResource("/map.fxml"));
            FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("/map.fxml"));
            VBox mapView = mapLoader.load();
            MapController mapController = mapLoader.getController();

            if (mapController != null) {
                System.out.println("MapController loaded successfully!");
                mapController.loadmapDock();
            } else {
                System.err.println("MapController is null!");
            }

            mainLayout.getChildren().add(mapView);
        } catch (IOException e) {
            System.err.println("Failed to load map.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @FXML
    private void AjouterRoute() {
        try {
            String routeid = routeid_field.getText();
            String Dest = Destname.getText();
            String Origine = originename.getText();

            Map<String, Object> routeData = new HashMap<>();
            routeData.put("Dest", Dest);
            routeData.put("Origine", Origine);

            firestore.collection("routes").document(routeid).set(routeData).get();

            System.out.println("Route ajoutee !");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erreur lors de l'ajout de la route: " + e.getMessage());
        }
    }



    @FXML
    private void goAjouterRoute(ActionEvent event) {
        Node source = (Node) event.getSource();
        loadPage("ajout-route.fxml", source);
    }

    @FXML
    private void goBackToHome(ActionEvent event) {
        try {


            loadPage("home.fxml", (Node) event.getSource());
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture de Firestore : " + e.getMessage());
        }
    }
    @FXML
    private void goBackToMain(ActionEvent event) {
        try {


            loadPage("Main.fxml", (Node) event.getSource());
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture de Firestore : " + e.getMessage());
        }
    }
    private void loadPage(String fxmlFile, Node node) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlFile));
            Parent root = loader.load();


            MainController controller = loader.getController();
            controller.setFirestore(firestore);

            Scene currentScene = node.getScene();

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(fxmlFile.replace(".fxml", ""));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la page : " + e.getMessage());
        }
    }

   @FXML
   private void goToRoutePage(ActionEvent event) {
       try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
           Parent root = loader.load();


           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    @FXML
    private void goToUserPage(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/users.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFirestore(Firestore firestore) {
        this.firestore = firestore;
    }
}