package org.example;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.util.List;

public class SolrService {
    private final SolrClient solrClient;

    public SolrService(String solrUrl) {
        this.solrClient = new HttpSolrClient.Builder(solrUrl).build();
    }

    public void indexSpace(Space space) throws Exception {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("country", space.getCountry());
        document.addField("state", space.getState());
        document.addField("city", space.getCity());
        document.addField("space_type", space.getSpaceType());
        document.addField("timings", space.getTimings());
        document.addField("space_name", space.getSpaceName());
        document.addField("availability", space.isAvailable());
        document.addField("popularity", space.getPopularity());
        document.addField("category", space.getCategory());

        solrClient.add("shareyourspace.com", document);
        solrClient.commit("shareyourspace.com");
    }

    public List<Space> searchSpaces(String queryStr) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryStr);
        QueryResponse response = solrClient.query("shareyourspace.com", query);
        return response.getBeans(Space.class);
    }

    // Close the Solr client
    public void close() throws Exception {
        solrClient.close();
    }
}

// Space class definition

