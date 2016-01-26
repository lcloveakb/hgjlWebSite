package com.senseId.social.controller;

import java.io.File;
import java.text.ParseException;
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

import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.Product;
import com.senseId.social.service.ArticleService;
import com.senseId.social.service.ProductService;

@Scope("prototype")
@Controller("productAction")
public class ProductAction extends ActionSupport {

	private static final long serialVersionUID = -1421496603753108072L;

	@Resource(name = "productService")
	private ProductService proService;

	@Resource(name = "articleService")
	private ArticleService articleService;

	/* 分页相关 */
	private Integer pageno;
	private Integer pagesize;
	private Long pagecount;

	/* 查询结果 */
	private List<Product> proList;
	private Product product = new Product();
	private List<String> imgList; // 图片路径列表
	private List<String> params;
	private List<Product> readers;
	private List<Product> tags;
	private List<Product> others;
	
	/* 产品各个字段 */
	private String id;
	private Integer category;
	private String parentId;
	private String img;
	private String content;
	private String title;
	private String brief;
	private Long top;

	private String articleId;
	private Integer type;
	private Integer clickCount;
	private Integer shareCount;
	private String createTime;
	private Integer shown;
	private String shown_;
	private String creator;
	private String indexImg;

	/* 图片上传相关 */
	private File attachment;
	private String attachmentContentType;
	private String attachmentFileName;
	private String upload;
	private String filename;
	private File index; // 首页轮换大图
	private String indexFileName;
	private String indexContentType;
//	private String showParams;

	/* Ajax状态值1成功，0失败 */
	private Integer ajaxStatus;

	public String toIndex() {
		try {
			readers = proService.searchByCategory(Product.READER, -1, 0, true);
			tags = proService.searchByCategory(Product.TAG, -1, 0, true);
			others = proService.searchByCategory(Product.OTHER, -1, 0, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	/**
	 * 产品3张图片上传
	 * 
	 * @throws Exception
	 */
	public void upload() throws Exception {

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/images/article/products");
		if (attachment != null) {
			File saveFile = new File(new File(realPath), attachmentFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(attachment, saveFile);
		}
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

	/**
	 * 修改产品时图片上传
	 * 
	 */
	public String uploadProductImg() throws Exception {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/images/article/products");
		if (attachment != null) {
			File saveFile = new File(new File(realPath), attachmentFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			if (!saveFile.exists())
				FileUtils.copyFile(attachment, saveFile);
		}
		return "ajax3";
	}

	/**
	 * 去产品列表页（后台）
	 * 
	 * @return
	 */
	public String listInCategoryBackend() {
		if (pageno == null) // 默认显示第一页
			pageno = 1;
		if (pagesize == null) // 默认分页大小为10
			pagesize = 10;
		try {
			if (category != null && category > 0) {
				proList = proService.searchByCategory(category, pageno,
						pagesize, true);
				pagecount = (proService.countByCategory(category) + pagesize - 1)
						/ pagesize;
			} else {
				proList = proService.searchByCategory(null, pageno, pagesize,
						true);
				pagecount = (proService.getTotalCount() + pagesize - 1)
						/ pagesize;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toProList";
	}

	/**
	 * 去产品分类显示页
	 * 
	 * @return
	 */
	public String listInCategory() {
		if (category != null) {
			try {
				proList = proService.searchByCategory(category, -1, 0, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "listInCategory";
	}

	/**
	 * 去产品更新页
	 * 
	 * @return
	 */
	public String toUpdate() {

		try {
			product = proService.get(id, true);
			if (product.getImgs() == null) {
				System.out.println("img==null");
			} else {
				String[] arr = product.getImgs().split(";");
				imgList = new ArrayList<String>();
				for (int i = 0; i < arr.length; i++) {
					String str = "<img alt='' src=images/" + arr[i] + "/>";
					imgList.add(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}

	/**
	 * 产品详情
	 * 
	 * @return
	 */
	public String detail() {
		try {
			product = proService.get(id, true);
			// 分割图片路径
			if (product.getImgs() == null) {
				System.out.println("detail img==null");
			} else {
				String[] arr = product.getImgs().split(";");
				imgList = new ArrayList<String>();
				for (int i = 0; i < arr.length; i++) {
					imgList.add(arr[i]);
				}
			}
			
			// 分割显示前6条数据
			String[] arr2 = product.getParams().split("</p>");
			params=new ArrayList<String>();
			for(int i=0;i<arr2.length;i++){
				params.add(arr2[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "detail";

	}

	/**
	 * 添加产品
	 * 
	 * @return
	 */
	public String add() {

		product.setTitle(title);
		product.setParams(content);
		product.setImgs(img);
		product.setCategory(category);
		product.setType(Article.TYPE_PRODUCT);
		product.setBrief(brief);
		product.setIndexImg(indexImg);
		boolean shown_ = shown == 0 ? true : false;
		product.setShown(shown_);

		product.setCreateTime(new Date());
		product.setCreator("Sense-Maker");
		product.setId(UUID.randomUUID().toString());

		// System.out.println("product==" + product);
		try {
			id = proService.add(product);
			// System.out.println("id==" + id);
			ajaxStatus = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxStatus = 0;
		}
		return "ajax";
	}

	public String toAdd() {
		return "add";
	}

	/**
	 * 删除服务器图片
	 * 
	 * @return
	 */
	public String ftpImgDel() {

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/images/article");
		String img_url = "";
		if (type == 0) { // 删除产品图片
			img_url = realPath += "\\products\\" + img;
		} else { // 删除首页大图
			img_url = realPath += "\\indeximg\\" + img;
		}

		File file = new File(img_url);
		if (file.exists()) {
			boolean op = file.delete();
			ajaxStatus = op == true ? 0 : 1;
		}

		return "ajax";
	}

	/**
	 * 更新产品
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String update() throws Exception {
		ajaxStatus = 0;
		product.setId(id);
		product.setCategory(new Integer(category));
		product.setParentId(new Long(parentId));
		product.setImgs(img);
		product.setIndexImg(indexImg);
		product.setParams(content);
		product.setTitle(title);
		product.setArticleId(new Long(articleId));
		product.setType(type);
		product.setClickCount(clickCount);
		product.setShareCount(shareCount);
		product.setCreateTime(new Date(createTime));
		product.setCreator(creator);
		product.setBrief(brief);
		product.setTop(top);
		boolean shown2 = shown_.equals("true") ? true : false;
		product.setShown(shown2);
		ajaxStatus = proService.update(product) ? 1 : 0;
		return "ajax";
	}

	/**
	 * 删除产品
	 * 
	 * @return
	 */
	public String delete() {
		ajaxStatus = proService.delete(id) ? 1 : 0;
		return "ajax";
	}

	/********************************** Getter Setter Methods ****************************************/

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

	public List<Product> getProList() {
		return proList;
	}

	@JSON(serialize = false)
	public void setProList(List<Product> proList) {
		this.proList = proList;
	}

	public Product getProduct() {
		return product;
	}

	@JSON(serialize = false)
	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
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

	public File getAttachment() {
		return attachment;
	}

	@JSON(serialize = false)
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

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Product> getReaders() {
		return readers;
	}

	@JSON(serialize = false)
	public void setReaders(List<Product> readers) {
		this.readers = readers;
	}

	public List<Product> getTags() {
		return tags;
	}

	@JSON(serialize = false)
	public void setTags(List<Product> tags) {
		this.tags = tags;
	}

	public List<Product> getOthers() {
		return others;
	}

	@JSON(serialize = false)
	public void setOthers(List<Product> others) {
		this.others = others;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public Integer getShown() {
		return shown;
	}

	public void setShown(Integer shown) {
		this.shown = shown;
	}

	public String getShown_() {
		return shown_;
	}

	public void setShown_(String shown_) {
		this.shown_ = shown_;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getIndexImg() {
		return indexImg;
	}

	public void setIndexImg(String indexImg) {
		this.indexImg = indexImg;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public Long getTop() {
		return top;
	}

	public void setTop(Long top) {
		this.top = top;
	}


}
