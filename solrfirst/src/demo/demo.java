package demo;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class demo {
	
	
	public static void main(String[] args) throws Exception {
//	 	����HttpSolrServer����ͨ������Solr�������������ӡ�
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
//		����SolrInputDocument����Ȼ��ͨ�����������
		SolrInputDocument document =new SolrInputDocument();
		document.addField("id", "0023");
		document.addField("content", "���");
//		ͨ��HttpSolrServer����SolrInputDocument��ӵ������⡣
		server.add(document);
//			�ύ��
		server.commit();
	}
	
	@Test
	public void testCreateUpdateIndex23() throws Exception{
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
		SolrInputDocument document =new SolrInputDocument();
		document.addField("id", "0023");
		document.addField("content", "���");
		server.add(document);
		server.commit();
	}
	
	@Test
	public void testDeleteIndex() throws SolrServerException, IOException{
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
		server.deleteById("0023");
		server.commit();
	}
	
	@Test
	public void testSearchIndex() throws Exception{
		
		//������������
		SolrQuery query = new SolrQuery();
		//������������
		query.setQuery("*:*");
		
		
		//������������
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
		QueryResponse response = server.query(query);	
		//�����������
		SolrDocumentList results = response.getResults();
		
		System.out.println("�������Ľ�������� ��"+results.getNumFound());
		
		//�����������
		for (SolrDocument solrDocument : results) {
		
			System.out.println("id: "+solrDocument.get("id"));
			System.out.println("content: "+solrDocument.get("content"));
		}
		
	}


}
