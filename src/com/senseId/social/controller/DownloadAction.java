package com.senseId.social.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.DownloadItem;
import com.senseId.social.entity.DownloadItemCategory;
import com.senseId.social.service.DownloadItemCategoryService;
import com.senseId.social.service.DownloadItemService;

@Scope("prototype")
@Controller("downloadAction")
public class DownloadAction extends ActionSupport {

	private static final long serialVersionUID = -8714346312972320322L;

	@Resource(name = "downloadItemCategoryService")
	private DownloadItemCategoryService downloadItemCategoryService;
	@Resource(name = "downloadItemService")
	private DownloadItemService downloadItemService;
	
	/*查询结果*/
	private List<DownloadItemCategory> catsList;
	private List<DownloadItem> itemList;
	private DownloadItemCategory cat;
	private DownloadItem item;

	private String title;
	private Integer id;
	private Integer category;
	
	/*文件上传相关*/
	private File fileUp;
	private String fileUpFileName;
	private String fileUpContentType;
	
	/*文件下载相关*/
	private String fileName;	//用户请求的文件名
	/**
	 * struts.xml中配置的资源根目录
	 */
	private String inputPath;
	private String url;
	private InputStream inputStream;

	/*分页相关*/
	private Integer pageno;
	private Integer pagesize;
	private Integer pagecount;
	
	
	public String downloadFile() throws Exception {
		item = downloadItemService.get(id);
		File f = new File(item.getUrl());
		fileName = URLEncoder.encode(f.getName(),"utf-8");
		if(!f.exists()) {
			//TODO 应做特别处理
			throw new Exception("请求的资源\""+item.getTitle()+"\"不存在");
		}
		inputStream = new FileInputStream(f);
		return "download_success";
	}
	
	public String toIndex() {
		try {
			catsList = downloadItemCategoryService.getAllCascade();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	/**
	 * 去添加分类页
	 * @return
	 */
	public String toCatAdd() {
		return "toAddCat";
	}
	
	/**
	 * 添加分类
	 * @return
	 */
	public String catAdd() {
		try {
			downloadItemCategoryService.add(cat.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	/**
	 * 去更新分类页
	 * @return
	 */
	public String toCatUpdate() {
		try {
			cat = downloadItemCategoryService.get(cat.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update";
	}

	/**
	 * 更新分类
	 * @return
	 */
	public String catUpdate() {
		try {
			downloadItemCategoryService.update(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	/**
	 * 删除分类
	 * @return
	 */
	public String catDelete() {
		downloadItemCategoryService.delete(cat.getId());
		return "show";
	}

	/**
	 * 去添加下载页
	 * @return
	 */
	public String toAddItem() {
		 try {
			 catsList = downloadItemCategoryService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toAddItem";
	}

	/**
	 * 添加下载	资源路径格式：/download/类别ID/文件名称
	 * @return
	 * @throws IOException
	 */
	public String itemAdd() throws IOException {

		String url = ServletActionContext.getServletContext().getRealPath(inputPath) + File.separator + category;
		if (fileUp != null) {
			File saveFile = new File(new File(url), fileUpFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(fileUp, saveFile);
			downloadItemService.add(title, url + File.separator + fileUpFileName, category);
			try {
				itemList = downloadItemService.getAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "showItem";
		} else {
			return "error";
		}

	}

	/**
	 * 去更新下载页
	 * @return
	 */
	public String toItemUpdate() {
		try {
			item = downloadItemService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toItemUpdate";
	}

	/**
	 * 更新下载
	 * @return
	 */
	public String itemUpdate() {
		downloadItemService.update(item);
		return "showItem";
	}

	/**
	 * 删除下载
	 * @return
	 */
	public String itemDelete() {
		try {
			item = downloadItemService.get(id);
			boolean deleteFromDb = downloadItemService.delete(id);
			if(deleteFromDb) {
				File file = new File(item.getUrl());
				if(file.exists())
					file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showItem";
	}

	/**
	 * 下载列表页
	 * @return
	 */
	public String itemList() {
		try {
			catsList = downloadItemCategoryService.getAll();
			if(category!=null) {
				itemList= downloadItemService.getByCategory(category);
			} else {
				itemList=downloadItemService.getAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toItemList";
	}
	
	/**
	 * 分类列表页
	 * @return
	 */
	public String catsList() {
		catsList = downloadItemCategoryService.getAll();
		return "toCatsList";
	}
	
	/*********************************Getter and Setter Methods*************************************/
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public List<DownloadItemCategory> getCatsList() {
		return catsList;
	}

	public void setCatsList(List<DownloadItemCategory> catsList) {
		this.catsList = catsList;
	}

	public DownloadItemCategory getCat() {
		return cat;
	}

	public void setCat(DownloadItemCategory cat) {
		this.cat = cat;
	}

	public List<DownloadItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<DownloadItem> itemList) {
		this.itemList = itemList;
	}

	public DownloadItem getItem() {
		return item;
	}

	public void setItem(DownloadItem item) {
		this.item = item;
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

	public File getFileUp() {
		return fileUp;
	}

	public void setFileUp(File fileUp) {
		this.fileUp = fileUp;
	}

	public String getFileUpFileName() {
		return fileUpFileName;
	}

	public void setFileUpFileName(String fileUpFileName) {
		this.fileUpFileName = fileUpFileName;
	}

	public String getFileUpContentType() {
		return fileUpContentType;
	}

	public void setFileUpContentType(String fileUpContentType) {
		this.fileUpContentType = fileUpContentType;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

}
