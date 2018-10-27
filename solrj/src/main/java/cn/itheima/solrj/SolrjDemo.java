package cn.itheima.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * solrj入门案例程序
 */
public class SolrjDemo {

    /**
     * 添加和更新索引
     */
    @Test
    public void addOrUpdateIndex() throws Exception{

        // 1.创建HttpSolrServer服务对象，连接solr服务
        // baseURL:指定solr服务的地址
        HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8082/solr");

        // 2.创建文档对象（SolrInputDocument）
        SolrInputDocument doc = new SolrInputDocument();

        doc.addField("id","9527");
        //doc.addField("name","solrj is a good things");

        // 更新
        doc.addField("name","solr and solrj are good things");

        // 3.执行添加（更新）
        server.add(doc);

        // 4.提交
        server.commit();
    }

    /**
     * 根据id删除索引
     */
    @Test
    public void deleteIndexById() throws Exception{
        // 1.创建HttpSolrServer服务对象，连接solr服务
        HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8082/solr");
        // 2.执行删除
        server.deleteById("9527");
        // 3.提交
        server.commit();
    }

    /**
     * 根据条件删除索引
     */
    @Test
    public void deleteIndexByQuery() throws Exception{
        // 1.创建HttpSolrServer服务对象，连接solr服务
        HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8082/solr");
        // 2.执行删除
        server.deleteByQuery("name:solr");
        // 3.提交
        server.commit();
    }

    /**
     * 搜索索引
     */
    @Test
    public void queryIndex() throws Exception{
        // 1.创建HttpSolrServer服务对象，连接solr服务
        HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8082/solr");

        // 2.建立查询对象（SolrQuery）
        SolrQuery sq = new SolrQuery("*:*");

        // 3.执行搜索，返回搜索响应对象（QueryResonpse）
        QueryResponse queryResponse = server.query(sq);

        // 4.从QueryResonpse对象中，取出搜索数据
        SolrDocumentList results = queryResponse.getResults();

        // 5.处理结果集
        // 5.1.打印实际搜索到的结果数量：
        System.out.println("实际搜索到的结果数量："+results.getNumFound());

        // 5.2.取出数据
        for(SolrDocument doc:results){
            System.out.println("----------------------华丽丽分割线--------------------");
            System.out.println("id域："+doc.get("id"));
            System.out.println("name域："+doc.get("name"));

        }

    }









}
