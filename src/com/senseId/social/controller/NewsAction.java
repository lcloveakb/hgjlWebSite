package com.senseId.social.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.New;
import com.senseId.social.service.ArticleService;
import com.senseId.social.service.CommentService;
import com.senseId.social.service.NewService;

@Scope("prototype")
@Controller("newsAction")
public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = -1421496603753108072L;

	@Resource(name = "newService")
	private NewService newsService;
	
	@Resource(name="articleService")
	private ArticleService as;
	
	@Resource(name = "commentService")
	private CommentService commentService;

	/* 分页相关 */
	private Integer pageno = 1;
	private Integer pagesize = 10;
	private Long pagecount;

	/* 查询结果 */
	private List<New> newsList;
	private New news = new New();
	private List<Article> indexImg=new ArrayList<Article>();
	
	/* 各个字段 */
	private String id;
	private String content;
	private String parentId;
	private Integer category;
	private String source;
	private String title;
	private Integer shown;
	private String imgs;
	private String brief;
	private String shown_;
	private String indeximg;
	private Integer clickCount;
	private Integer shareCount;
	private String createTime;
	private String creator;
	private Integer type;
	private Long top;
	private String articleId;

	private MultipartFile upfile;

	/* Ajax状态值1成功，0失败 */
	private Integer ajaxStatus;
	
	/*图片上传*/
	private File attachment;
	private String attachmentContentType;
	private String attachmentFileName;
	private String filename;
	private String upload;

	private File index; // 首页轮换大图
	private String indexFileName;
	private String indexContentType;
	
	private List<Article> lately=new ArrayList<Article>();//最新文章
	private List<Article> hot=new ArrayList<Article>();//最热文章
	
	
	/**
	 * 主页
	 * 
	 * @return
	 */
	public String toIndex() {
		if (category == null)
			category = New.INDUSTRY;
		try {
			newsList = newsService.searchByCategory(category, pageno, pagesize,
					true);
//			System.out.println(newsList.get(0).getImgs());
//			System.out.println(newsList.size() + "条新闻--->newsAction_toIndex");
//			System.out.println(newsList.get(0).getBrief());
			
			//获得轮换图
			List<Article> list_=as.getList();
			for(int i=0;i<list_.size();i++){
				if(list_.get(i).getType()==0&&list_.get(i).getIndexImg().length()>0){
					indexImg.add(list_.get(i));
				}else{
					continue;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	/**
	 * 详情页
	 * 
	 * @return
	 */
	public String detail() {
		if(pageno==null)
			pageno = 1;
		pagesize = 10;
		try {
			news = newsService.get(id, true);
			lately = as.search(1, 5, null, null, new String[]{"type"}, new Object[]{0}, null, null, "createTime", false);
			hot = as.search(1, 5, null, null, new String[]{"type"}, new Object[]{0}, null, null, "clickCount", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}

	/**
	 * 去新闻分类显示页
	 * 
	 * @return
	 */
	public String listInCategory() {
		if (category == null)
			category = New.INDUSTRY;
		try {
			newsList = newsService.searchByCategory(category, pageno, pagesize,
					true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	public String listInCategoryBackend() {
		if(pageno==null)
			pageno = 1;
		if(pagesize==null)
			pagesize=5;
		try {
			if(category!=null && category>0) {
				newsList = newsService.searchByCategory(category, pageno, pagesize,	true);
				pagecount = newsService.countByCategory(category);
			} else {
				newsList = newsService.searchByCategory(null, pageno, pagesize,	true);
				pagecount = (newsService.getTotalCount()+pagesize-1)/pagesize;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newsList";
	}

	/**
	 * 去更新页
	 * 
	 * @return
	 */
	public String toUpdate() {
		try {
			news = newsService.get(id, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		if (newsService.delete(id)) {
			ajaxStatus = 1;
		} else {
			ajaxStatus = 0;
		}
		return "ajax";
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		ajaxStatus = 0;
		news.setId(id);
		news.setContent(content);
		news.setParentId(new Long(parentId));
		news.setCategory(category);
		news.setSource(source);
		news.setClickCount(clickCount);
		news.setShareCount(shareCount);
		news.setIndexImg(indeximg);
//		news.setCreateTime(TypeTransform.string2Date(createTime));
		news.setCreateTime(new Date(createTime));
		news.setCreator(creator);
		boolean shown2=shown_.equals("true")?true:false;
		news.setShown(shown2);
		news.setBrief(brief);
		news.setArticleId(new Long(articleId));
		news.setType(type);
		news.setTop(top);
		
		news.setTitle(title);
		if (newsService.update(news)) {
			ajaxStatus = 1;
		} else {
			ajaxStatus = 0;
		}
		return "ajax";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {

		//第一张图片处理
		if(imgs==null||imgs.length()<=0){
			imgs="";
		}/*else{
			imgs = imgs.substring(9, imgs.length());
		}*/
		
		news.setImgs(imgs);
		news.setTitle(title);
		news.setCategory(category);
		news.setContent(content);
		news.setBrief(brief);
		news.setIndexImg(indeximg);
		boolean shown_=shown==0?true:false;
		news.setShown(shown_);
		news.setId(UUID.randomUUID().toString());
		if (category == 0) {
			news.setCreator("Sense-Maker");
			news.setCreateTime(new Date());
		} else {
			news.setCreator("");
			news.setCreateTime(new Date());
		}
		try {
			id = newsService.add(news);
			ajaxStatus = 1;
		} catch (Exception e) {
			ajaxStatus = 0;
			e.printStackTrace();
		}
		return "ajax";
	}
	
	/**
	 * 删除服务器图片
	 * 
	 * @return
	 */
	public String delIndexImg() {

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/images/article");
		String img_url = realPath += "\\indeximg\\" + indeximg;
		
		File file = new File(img_url);
		if (file.exists()) {
			boolean op = file.delete();
			ajaxStatus = op == true ? 0 : 1;
		}
		return "ajax";
	}
	
	
	/**
	 * 首页轮换图片上传
	 * 
	 */
	public String uploadIndexImg() throws Exception {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/images/article/indeximg");
		if (index != null) {
			File saveFile = new File(new File(realPath), indexFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			if (!saveFile.exists())
				FileUtils.copyFile(index, saveFile);
		}
		return "ajax2";
	}
	
	/**************************************** Getter and Setter Methods ************************************************/
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

	public New getNews() {
		return news;
	}

	public void setNews(New news) {
		this.news = news;
	}

	public List<New> getNewsList() {
		return newsList;
	}
	@JSON(serialize=false)
	public void setNewsList(List<New> newsList) {
		this.newsList = newsList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAjaxStatus() {
		return ajaxStatus;
	}

	public void setAjaxStatus(Integer ajaxStatus) {
		this.ajaxStatus = ajaxStatus;
	}

	public Integer getShown() {
		return shown;
	}

	public void setShown(Integer shown) {
		this.shown = shown;
	}

	public MultipartFile getUpfile() {
		return upfile;
	}
	@JSON(serialize=false)
	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getShown_() {
		return shown_;
	}

	public void setShown_(String shown_) {
		this.shown_ = shown_;
	}

	public String getIndeximg() {
		return indeximg;
	}

	public void setIndeximg(String indeximg) {
		this.indeximg = indeximg;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Article> getIndexImg() {
		return indexImg;
	}

	public void setIndexImg(List<Article> indexImg) {
		this.indexImg = indexImg;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public File getIndex() {
		return index;
	}

	public void setIndex(File index) {
		this.index = index;
	}

	public String getIndexFileName() {
		return indexFileName;
	}

	public void setIndexFileName(String indexFileName) {
		this.indexFileName = indexFileName;
	}

	public String getIndexContentType() {
		return indexContentType;
	}

	public void setIndexContentType(String indexContentType) {
		this.indexContentType = indexContentType;
	}

	public Long getTop() {
		return top;
	}

	public void setTop(Long top) {
		this.top = top;
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
