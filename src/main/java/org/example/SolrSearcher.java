package org.example;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;

public class SolrSearcher {
    private static final String SOLR_URL = "http://localhost:8983/solr/shareyourspace.com";

    public static void main(String[] args) throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("space_name:Cozy Room");
        query.addFilterQuery("country:USA");
        query.setFields("id", "space_name", "country", "city", "availability");
        query.setStart(0);
        query.setRows(10);

        QueryResponse response = solrClient.query(query);
        SolrDocumentList docs = response.getResults();

        for (int i = 0; i < docs.getNumFound(); i++) {
            System.out.println(docs.get(i));
        }

        solrClient.close();
    }
}

