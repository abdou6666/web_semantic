package com.example.demo.controller;

import com.example.demo.tools.JenaEngine;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping(path = "/api/abdou",produces = "application/json")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class Abdou {
    @GetMapping("/admins")
    public String getAdmin() {
        String qexec = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
                "SELECT ?email ?mot_de_passe ?nom\n" +
                "WHERE {\n" +
                "  ?admin rdf:type project:Admin .\n" +
                "  ?admin project:Email ?email .\n" +
                "  ?admin project:Mot_de_passe ?mot_de_passe .\n" +
                "  ?admin project:Nom ?nom .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();
    }
    @GetMapping("/reclamations")
    public String getReclamations() {
        String qexec = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
              /*      "SELECT *\n" +
                "WHERE {\n" +
                "  ?admin rdf:type project:Admin .\n" +
                "  ?admin project:Email ?email .\n" +
                "  ?admin project:Mot_de_passe ?mot_de_passe .\n" +
                "  ?admin project:Nom ?nom .\n" +
                "}\n"; */
                "select?entité\n";

        Model model = JenaEngine.readModel("data/sem.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();
    }
    // gets reclamations and responses only
    @GetMapping("/getReclamationsAndResponses")
    public String getClientAndTheirOrders() {
        String qexec = "PREFIX projet: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
                "# Query to retrieve the descriptions of reclamations and their associated responses\n" +
                "SELECT ?reclamationDescription ?responseDescription\n" +
                "WHERE {\n" +
                "  ?reclamation a projet:Reclamation .\n" +
                "  ?reclamation projet:Description ?reclamationDescription .\n" +
                "  ?response projet:est_repondus_par ?admin .\n" +
                "  ?response projet:Description ?responseDescription .\n" +
                "}";

        Model model = JenaEngine.readModel("data/sem.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();
    }

    // get clients reclamation with their name and the admin who responded and the response
    @GetMapping("/getClientsReclamationsWithClienDataAndAdminData")
    public String getClientReclamation() {
        String qexec = "PREFIX projet: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
                "# Query to retrieve the descriptions of reclamations, their associated responses, client information, and admin information\n" +
                "SELECT ?reclamationDescription ?responseDescription ?clientName ?clientEmail ?adminName ?adminEmail\n" +
                "WHERE {\n" +
                "  ?reclamation a projet:Reclamation .\n" +
                "  ?reclamation projet:Description ?reclamationDescription .\n" +
                "  \n" +
                "  ?reclamation projet:estReclamer ?client .\n" +
                "  ?client projet:Nom ?clientName .\n" +
                "  ?client projet:Email ?clientEmail .\n" +
                "  \n" +
                "  ?response projet:est_repondus_par ?admin .\n" +
                "  ?response projet:Description ?responseDescription .\n" +
                "  ?admin projet:Nom ?adminName .\n" +
                "  ?admin projet:Email ?adminEmail .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();
    }

    //search reclamation based on description
    @GetMapping("/searchReclamation/{description}")
    public String searchReclamation(@PathVariable("description") String description) {

        String search = description; // Set the description you want to search for
        String queryString = "PREFIX projet: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
                "# Query to search for reclamations based on description\n" +
                "SELECT ?reclamation ?reclamationDescription\n" +
                "WHERE {\n" +
                "  ?reclamation a projet:Reclamation .\n" +
                "  ?reclamation projet:Description ?reclamationDescription .\n" +
                "  FILTER regex(?reclamationDescription, \"" + search + "\", \"i\")\n" +
                "}";


        String qexec = queryString;


        Model model = JenaEngine.readModel("data/sem.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();
    }

    @GetMapping("/getClientsReclamationsWithClienDataAndAdminData/{nom}")
    public String searchClientReclamationByClientName(@PathVariable("nom") String nom) {
        String qexec = "PREFIX projet: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
                "# Query to retrieve the descriptions of reclamations, their associated responses, client information, and admin information\n" +
                "SELECT ?reclamationDescription ?responseDescription ?clientName ?clientEmail ?adminName ?adminEmail\n" +
                "WHERE {\n" +
                "  ?reclamation a projet:Reclamation .\n" +
                "  ?reclamation projet:Description ?reclamationDescription .\n" +
                "  \n" +
                "  ?reclamation projet:estReclamer ?client .\n" +
                "  ?client projet:Nom ?clientName .\n" +
                "  ?client projet:Email ?clientEmail .\n" +
                "  FILTER (?clientName = \""+nom+ "\")\n"+
                "  \n" +
                "  ?response projet:est_repondus_par ?admin .\n" +
                "  ?response projet:Description ?responseDescription .\n" +
                "  ?admin projet:Nom ?adminName .\n" +
                "  ?admin projet:Email ?adminEmail .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();
    }

}
