package org.example;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String SOLR_URL = "http://localhost:8983/solr/shareyourspace.com";
    public static void main(String[] args) throws SolrServerException, IOException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).build();

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "space1");
        doc.addField("country", "USA");
        doc.addField("state", "California");
        doc.addField("city", "San Francisco");
        doc.addField("space_type", "Room");
        doc.addField("timings", "Hourly");
        doc.addField("space_name", "Cozy Room");
        doc.addField("availability", true);
        doc.addField("popularity", 5);
        doc.addField("category", "Premium");

        solrClient.add(doc);
        solrClient.commit();
        solrClient.close();
//        SolrService solrService = new SolrService("http://localhost:8983/solr");
//
//        try {
//            Space space1 = new Space();
//            space1.setCountry("USA");
//            space1.setState("California");
//            space1.setCity("San Francisco");
//            space1.setSpaceType("Office");
//            space1.setTimings("Hourly");
//            space1.setSpaceName("Downtown Office");
//            space1.setAvailable(true);
//            space1.setPopularity("popular");
//            space1.setCategory("Premium");
//
//            solrService.indexSpace(space1);
//
//            // Index more spaces as needed
//
//            List<Space> results = solrService.searchSpaces("space_name:Downtown Office");
//            for (Space space : results) {
//                System.out.println("Found space: " + space.getSpaceName());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                solrService.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
