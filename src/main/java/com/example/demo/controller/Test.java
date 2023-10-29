package com.example.demo.controller;

import java.io.ByteArrayOutputStream;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.tools.JenaEngine;

//import com.example.demo.tools.JenaEngine;


 




@RestController
@RequestMapping(path = "/api",produces = "application/json")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class Test {
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
    @GetMapping("/GetCommandesMontantProduit")
    public String GetCommandesMontantProduit() {
        String qexec =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                        "\n" +
                        "# Query to retrieve the names of admins and their associated to-do lists\n" +
                        "# Query to retrieve all products with their associated categories\n" +
                        "SELECT ?NumCommande ?montant ?Nom ?Description\n" +
                        "WHERE {\n" +
                        "  ?commande rdf:type project:Commande .\n" +
                        "  ?commande project:NumCommande ?NumCommande .\n" +
                        "  ?commande project:doit_etre_payer ?paiment .\n" +
                        "  ?paiment project:montant ?montant .\n" +
                        "  ?commande project:estProduitDe ?produit .\n" +
                        "  ?produit project:Description ?Description .\n" +
                        "  ?produit project:Nom ?Nom .\n" +
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
    @GetMapping("/getClientAndTheirOrders")
    public String getClientAndTheirOrders() {
        String qexec = "PREFIX projet: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "\n" +
                "# Query to retrieve the names of admins and their associated to-do lists\n" +
                "# Query to retrieve all products with their associated categories\n" +
               "SELECT ?description\n" +
                "WHERE {\n" +
                "  ?client a projet:Client ;\n" +
                "          projet:estReclamer ?produit .\n" +
                "  ?produit projet:Description ?description .\n" +
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

}
