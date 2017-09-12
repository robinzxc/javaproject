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
//	 	创建HttpSolrServer对象，通过它和Solr服务器建立连接。
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
//		创建SolrInputDocument对象，然后通过它来添加域。
		SolrInputDocument document =new SolrInputDocument();
		document.addField("id", "0023");
		document.addField("content", "你好");
//		通过HttpSolrServer对象将SolrInputDocument添加到索引库。
		server.add(document);
//			提交。
		server.commit();
	}
	
	@Test
	public void testCreateUpdateIndex23() throws Exception{
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
		SolrInputDocument document =new SolrInputDocument();
		document.addField("id", "0023");
		document.addField("content", "你好");
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
		
		//创建搜索对象
		SolrQuery query = new SolrQuery();
		//设置搜索条件
		query.setQuery("*:*");
		
		
		//发起搜索请求
		HttpSolrServer server=new HttpSolrServer("http://localhost:8080/solr");
		QueryResponse response = server.query(query);	
		//处理搜索结果
		SolrDocumentList results = response.getResults();
		
		System.out.println("搜索到的结果总数是 ："+results.getNumFound());
		
		//遍历搜索结果
		for (SolrDocument solrDocument : results) {
		
			System.out.println("id: "+solrDocument.get("id"));
			System.out.println("content: "+solrDocument.get("content"));
		}
		
	}


}
