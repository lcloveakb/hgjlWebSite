package com.senseId.social.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.DeveloperItemCategory;
import com.senseId.social.entity.New;
import com.senseId.social.entity.Product;
import com.senseId.social.service.ArticleService;
import com.senseId.social.service.DeveloperItemCategoryService;
import com.senseId.social.service.NewService;
import com.senseId.social.service.ProductService;

@Scope("prototype")
@Controller("indexAction")
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 4283449219947119584L;

	@Resource(name = "productService")
	private ProductService ps;

	@Resource(name = "newService")
	private NewService ns;

	@Resource(name = "articleService")
	private ArticleService as;

	@Resource(name = "developerItemCategoryService")
	private DeveloperItemCategoryService devCatService;

	private List<Product> products = new ArrayList<Product>();

	private List<New> news_us = new ArrayList<New>();

	private List<New> news_industry = new ArrayList<New>();

	private List<Article> articles = new ArrayList<Article>();

	private List<DeveloperItemCategory> cats;

	/* Ajax状态值1成功，0失败 */
	private Integer ajaxStatus;

	/* params list */
	private String id;
	
	/*瀑布分页相关*/
	private Integer pageno;
	private Integer pagesize;
	private Long pagecount;
	
	public String forward(){
		try {
			cats = devCatService.getAll();
			ActionContext.getContext().getSession().put("cats", cats); // 开发者分类
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 固定菜单栏
		return "index";
	}

	public String toIndex() {

		if(pageno==null){
			pageno=1;
		}
		if(pagesize==null){
			pagesize=6;
		}
		// ��������
		try {
			news_us = ns.searchByCategory(New.US, 1, 6, true);
			news_industry = ns.searchByCategory(New.INDUSTRY, 1, 6, true);
			articles = as.search(pageno, pagesize, null, null, new String[] { "shown" },
					new Object[] { true }, null, null, "top", false);
			pagecount = as.getTotalCount();
			
																		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ajax";
	}

	public String toError() {

		return "error";
	}
	
	public String toTop() {

		try{
			Long id_ = new Long(id);
			Article a = as.get(id_);
			Long level=as.max();
			a.setTop(level+1);
			if (as.update(a)) {
				ajaxStatus = 1;
			} else {
				ajaxStatus = 0;
			}
		}catch(Exception e){
			e.getStackTrace();
			return "error";
		}
		return "ajax";
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<New> getNews_us() {
		return news_us;
	}

	public void setNews_us(List<New> news_us) {
		this.news_us = news_us;
	}

	public List<New> getNews_industry() {
		return news_industry;
	}

	public void setNews_industry(List<New> news_industry) {
		this.news_industry = news_industry;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<DeveloperItemCategory> getCats() {
		return cats;
	}

	public void setCats(List<DeveloperItemCategory> cats) {
		this.cats = cats;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAjaxStatus() {
		return ajaxStatus;
	}

	public void setAjaxStatus(Integer ajaxStatus) {
		this.ajaxStatus = ajaxStatus;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Long getPagecount() {
		return pagecount;
	}

	public void setPagecount(Long pagecount) {
		this.pagecount = pagecount;
	}

	
	
	

}
