package com.senseId.social.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.DeveloperItem;
import com.senseId.social.entity.New;
import com.senseId.social.entity.Product;
import com.senseId.social.service.ArticleService;
import com.senseId.social.service.DeveloperItemService;
import com.senseId.social.service.NewService;
import com.senseId.social.service.ProductService;
import com.senseId.social.util.TypeTransform;

@Scope("prototype")
@Controller("searchAction")
public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = 7596687596196361085L;

	@Resource(name = "articleService")
	private ArticleService articleService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "newService")
	private NewService newService;
	@Resource(name="developerItemService")
	private DeveloperItemService developerItemService;

	private String keyword; // 搜索的关键字
	private String id; // article的主键ID
	private Integer type; // article的子类型

	private String jsonReturn; // 搜索提示列表
	private List<Article> articleList; // 搜索结果列表
	private List<Article> lately=new ArrayList<Article>();//最新文章
	private List<Article> hot=new ArrayList<Article>();//最热文章
	
	/**
	 * 搜索提示
	 * @return
	 */
	public String autoComplete() {
		try {
			if (keyword == null || keyword.trim().length() == 0) {
				jsonReturn = "";
			}else{
				ArrayList<Article> list = (ArrayList<Article>) articleService
						.searchForAuto(keyword);
				jsonReturn = TypeTransform.list2Json(list);
				System.out.println(jsonReturn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "autoComplete";
	}

	/**
	 * 指定关键字的搜索结果页
	 * @return
	 */
	public String detailSearch() {
		try {
			if(keyword!=null && keyword.length()>0)
				articleList = articleService.searchByLucence(keyword);
			lately = articleService.search(1, 5, null, null, new String[]{"type"}, new Object[]{0}, null, null, "createTime", false);
			hot = articleService.search(1, 5, null, null, new String[]{"type"}, new Object[]{0}, null, null, "clickCount", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detailSearch";
	}

	/**
	 * 进入搜索结果详情页
	 * 
	 * @return
	 */
	public String itemSearch() {
		switch (type) {
		case Article.TYPE_PRODUCT: // 产品类
			try {
				Product product = productService.searchByProperties(new String[]{"parentId"}, new Object[]{Long.parseLong(id)});
				id = product.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "productDetail";
		case Article.TYPE_NEWS: // 新闻类
			try {
				New news = newService.searchByProperties(new String[]{"parentId"}, new Object[]{Long.parseLong(id)});
				id = news.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "newDetail";
		case Article.TYPE_CASE:
			try {
				List<DeveloperItem> list = (List<DeveloperItem>) developerItemService.about(1, 1, null, null, new String[]{"parentId"}, new Object[]{Long.parseLong(id)}, null, null, null, false);
				if(list!=null){
					DeveloperItem item=list.get(0);
					id=item.getId().toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			return "developerDetail";
		default:
			return "error";
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getJsonReturn() {
		return jsonReturn;
	}

	public void setJsonReturn(String jsonReturn) {
		this.jsonReturn = jsonReturn;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Article> getLately() {
		return lately;
	}

	public void setLately(List<Article> lately) {
		this.lately = lately;
	}

	public List<Article> getHot() {
		return hot;
	}

	public void setHot(List<Article> hot) {
		this.hot = hot;
	}

}
