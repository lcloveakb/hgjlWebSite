package com.senseId.social.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.DeveloperItem;
import com.senseId.social.entity.DeveloperItemCategory;
import com.senseId.social.service.ArticleService;
import com.senseId.social.service.DeveloperItemCategoryService;
import com.senseId.social.service.DeveloperItemService;

@Scope("prototype")
@Controller("developerAction")
public class DeveloperAction extends ActionSupport {

	private static final long serialVersionUID = -3886883676992321910L;

	@Resource(name = "developerItemService")
	private DeveloperItemService devService;
	@Resource(name = "developerItemCategoryService")
	private DeveloperItemCategoryService devCatService;
	@Resource(name = "articleService")
	private ArticleService articleService;

	private List<DeveloperItemCategory> cats;
	private List<Article> lately = new ArrayList<Article>();
	private List<Article> hot = new ArrayList<Article>();
	private DeveloperItem item = new DeveloperItem();
	private DeveloperItemCategory cat = new DeveloperItemCategory();

	private Integer id;
	private String content;
	private String title;
	private Integer categoryid;
	private String createtime;
	private Boolean ajaxStatus;
	private List<DeveloperItem> items;
	private Integer pageno;
	private Integer pagesize;
	private Integer pagecount;
	private String brief;
	private Integer shown;
	private String indeximg;
	private String imgs;
	private String parentid;
	private Integer clickCount;
	private Integer shareCount;
	private String articleId;
	private Integer type;
	private Long top;
	private String creator;

	public DeveloperAction() {
		pageno = 1;
		pagesize = 5;
	}

	public String toIndex() {
		try {
			cats = devCatService.getAll();
			items = devService.getAll();
			for (DeveloperItem i : items) {
				int index = i.getContent().indexOf("title=");
				int begin, end;
				String name, path;
				if (index > 0) {
					begin = index + 7;
					end = begin + 24;
					name = i.getContent().substring(begin, end);
					path = name.substring(0, 8);
					i.setName(name);
					i.setPath("images/article/news/" + path);
				} else {
					i.setName("not_found.jpg");
					i.setPath("lib/images");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	public String add() {

		item = new DeveloperItem();
		item.setTitle(title);
		item.setContent(content);
		item.setCreateTime(new Date());
		item.setCategoryId(new Integer(categoryid));
		item.setBrief(brief);
		item.setCreateTime(new Date());
		boolean shown_ = shown == 0 ? true : false;
		item.setShown(shown_);
		item.setCreator("Sense-Maker");
		item.setIndexImg(indeximg);
		try {
			id = devService.add(item);
			ajaxStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxStatus = false;
		}
		return "ajax";
	}

	public String toAdd() throws Exception {
		cats = devCatService.getAll();
		return "add";
	}

	public String detail() {
		if (id != null) {
			try {
				item = devService.get(id);
				item.setCategory(devCatService.get(item.getCategoryId()));
				lately = articleService.search(1, 5, null, null, new String[]{"type"}, new Object[]{1}, null, null, "createTime", false);
				hot = articleService.search(1, 5, null, null, new String[]{"type"}, new Object[]{1}, null, null, "clickCount", false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "detail";
	}

	public String toCatsList() {
		try {
			cats = devCatService.getAll();
			pagecount = (devService.getTotalCount() + pagesize - 1) / pagesize;
			items = devService.search_(pageno, pagesize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toCatsList";
	}

	public String toCatsListByType() {
		try {
			cats = devCatService.getAll();
			items = devService.search(pageno, pagesize, categoryid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toCatsList";
	}

	public String toUpdate() {

		try {
			item = devService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}

	public String update() {
		// DeveloperItemֻ���Ը���title��content
		item.setId(id);
		item.setCategoryId(categoryid);
		item.setParentId(new Long(parentid));
		item.setContent(content);
		item.setTitle(title);
		item.setArticleId(new Long(articleId));
		item.setClickCount(clickCount);
		item.setShareCount(shareCount);
		item.setCreateTime(new Date(createtime));
		boolean shown_ = shown == 0 ? true : false;
		item.setShown(shown_);
		item.setCreator(creator);
		item.setType(type);
		item.setBrief(brief);
		item.setTop(top);
		item.setIndexImg(indeximg);
		ajaxStatus = devService.update(item);
		return "ajax";
	}

	public String deleteItem() {

		ajaxStatus = devService.delete(id);
		return "ajax";
	}

	public String preview() {
		if (id != null) {
			try {
				item = devService.get(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "preview";
	}

	public String toCates() {

		try {
			cats = devCatService.getAll();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "cats";
	}

	public String deleteCat() {

		ajaxStatus = devCatService.delete(cat.getId());
		return "ajax";
	}

	public String toCatUpdate() {

		try {
			cat = devCatService.get(cat.getId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "update";
	}

	public String catUpdate() {

		ajaxStatus = devCatService.update(cat);
		if (!ajaxStatus) {
			return "error";
		} else {
			return "show";
		}
	}

	// 类别添加
	public String catAdd() {

		try {
			devCatService.add(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public List<DeveloperItemCategory> getCats() {
		return cats;
	}

	public void setCats(List<DeveloperItemCategory> cats) {
		this.cats = cats;
	}

	public Boolean getAjaxStatus() {
		return ajaxStatus;
	}

	public void setAjaxStatus(Boolean ajaxStatus) {
		this.ajaxStatus = ajaxStatus;
	}

	public DeveloperItem getItem() {
		return item;
	}

	public void setItem(DeveloperItem item) {
		this.item = item;
	}

	public List<DeveloperItem> getItems() {
		return items;
	}

	public void setItems(List<DeveloperItem> items) {
		this.items = items;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public Integer getPagecount() {
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public DeveloperItemCategory getCat() {
		return cat;
	}

	public void setCat(DeveloperItemCategory cat) {
		this.cat = cat;
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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getShown() {
		return shown;
	}

	public void setShown(Integer shown) {
		this.shown = shown;
	}

	public String getIndeximg() {
		return indeximg;
	}

	public void setIndeximg(String indeximg) {
		this.indeximg = indeximg;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getTop() {
		return top;
	}

	public void setTop(Long top) {
		this.top = top;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
