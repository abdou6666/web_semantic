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
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/one/comment")
    public String getOneComment() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "SELECT ?comment ?property ?value\n" +
                "WHERE {\n" +
                "  project:Comment01 ?property ?value .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        String jsonResult = new String(outputStream.toByteArray());
        System.out.println(jsonResult); // Add this line for debugging

        return jsonResult;
    }

    @GetMapping("/all/comments")
    public String getAllCommentDescriptions() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "SELECT ?description\n" +
                "WHERE {\n" +
                "  ?comment project:Desc ?description .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        String jsonResult = new String(outputStream.toByteArray());
        System.out.println(jsonResult); // Add this line for debugging

        return jsonResult;
    }


    @GetMapping("/comments/products")
    public String getCommentsProducts() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "SELECT ?description ?name\n" +
                "WHERE {\n" +
                " project:Comment01 project:Desc ?description .\n" +
                " project:Produit01 project:Name ?name .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        String jsonResult = new String(outputStream.toByteArray());
        System.out.println(jsonResult); // Add this line for debugging

        return jsonResult;
    }

    @GetMapping("/produitByComment")
    public String getProduitNameByComment() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "SELECT ?comment ?description ?name\n" +
                "WHERE {\n" +
                " ?comment project:hasComment project:Produit01 ;\n" +
                "  project:Desc ?description .\n" +
                "  ?product project:Name ?name .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        String jsonResult = new String(outputStream.toByteArray());
        System.out.println(jsonResult); // Add this line for debugging

        return jsonResult;
    }

    @GetMapping("/allcomments/product")
    public String getAllCommentsForProduct() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "SELECT ?comment ?description\n" +
                "WHERE {\n" +
                "  project:Produit01 project:hasComment ?comment .\n" +
                "  ?comment project:Desc ?description .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        String jsonResult = new String(outputStream.toByteArray());
        System.out.println(jsonResult); // Add this line for debugging

        return jsonResult;
    }
    @GetMapping("/client/comment")
    public String getClientByComment() {
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX project: <http://www.semanticweb.org/anis/ontologies/2023/9/projet#>\n" +
                "SELECT ?name\n" +
                "WHERE {\n" +
                "   ?client project:ajouter_commentaire project:Comment01 ;\n" +
                "   project:Nom ?name .\n" +
                "}\n";

        Model model = JenaEngine.readModel("data/sem.owl");
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        String jsonResult = new String(outputStream.toByteArray());
        System.out.println(jsonResult); // Add this line for debugging

        return jsonResult;
    }












}
