package org.example;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class MapController {

    @FXML
    private WebView mapWebView;

    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyB6gkH-xLGcSHf6fOKW7SdXkRlJxCG72n0";

    @FXML
    public void initialize() {
        loadmapDock();
    }

    public void loadmapDock() {
        // Google Maps Embed API URL
        String MapsUrl = "http://localhost:8080";

        // Create an HTML string with an iframe
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <style>\n" +
                "      html, body, iframe {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        width: 100%;\n" +
                "        height: 100%;\n" +
                "        border: none;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <iframe src=\"" + MapsUrl + "\"></iframe>\n" +
                "  </body>\n" +
                "</html>";

        // Load the HTML into the WebView
        if (mapWebView != null) {
            mapWebView.getEngine().loadContent(htmlContent);
        } else {
            System.err.println("mapWebView is null!");
        }
    }
}