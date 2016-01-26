package com.senseId.social.dao.impl;

import org.springframework.stereotype.Repository;

import com.senseId.social.dao.ArticleDao;
import com.senseId.social.entity.Article;

@Repository("articleDao")
public class ArticleDaoImpl extends HibernateGenericDaoImpl<Article, Long>
		implements ArticleDao {

//	DataBaseManager db = null;
//	ResultSet set = null;
//	Logger logger = Logger.getLogger(ArticleDaoImpl.class);
//	static IndexSearcher searcher = null;

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Article> searchForAuto(String keyword) {
//
//		List<Article> find = null;
//		try {
////			String sql = "select * from article where title like '%"+keyword+"%' order by clickCount";
////			Query query = getSession().createSQLQuery(sql);
////			find = query.list();
////			find = find(-1, 1, new String[]{"title"}, new String[]{keyword}, null, null, null, null, "clickCount", true);
//			String sql = "from "+Article.class.getName()+" as model where model.title like '%"+keyword+"%' order by model.clickCount desc";
//			Query query = getSession().createQuery(sql);
//			query.setCacheable(true);
//			find = query.list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return find;
//	}

//	@Override
//	public List<Article> searchByLucence(String keyword) {
//
//		db = new DataBaseManager();
//		List<Article> list = new ArrayList<Article>();
//		set = db.getResult("select * from article where title like '%"
//				+ keyword + "%' order by clickCount");
//		int num = ArticleDaoImpl.createIndex(set);
//		logger.info("maxDoc==" + num);
//		try {
//			TopDocs topDocs = searchLucence(keyword);
//			ScoreDoc[] scoreDoc = topDocs.scoreDocs;
//			ArrayList<Article> list1 = addHits2List(scoreDoc);
//			for (Object obj : list1) {
//				Article ar = (Article) obj;
//				list.add(ar);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} finally {
//			db.closeConnection();
//		}
//		return list;
//	}

//	static ArrayList<Article> addHits2List(ScoreDoc[] scoreDoc) {
//
//		ArrayList<Article> list = new ArrayList<Article>();
//		for (int i = 0; i < scoreDoc.length; i++) {
//			int docid = scoreDoc[i].doc;
//			try {
//				Document doc = searcher.doc(docid);
//				Article ar = new Article(new Long(doc.get("id")), new Integer(
//						doc.get("type")), new Integer(doc.get("clickCount")),
//						new Integer(doc.get("shareCount")),
//						TypeTransform.string2Date(doc.get("createTime")),
//						doc.get("creater"), doc.get("title"));
//
//				list.add(ar);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}

//	public static Integer createIndex(ResultSet set) {
//
//		Integer no = null;
//		IndexWriter write = null;
//
//		try {
//			write = new IndexWriter(FSDirectory.open(new File(
//					Constants.INDEX_FILE_PATH).toPath()),
//					new IndexWriterConfig(new StandardAnalyzer()));
//			while (set.next()) {
//				Document doc = new Document();
//				Field id = new Field("id", String.valueOf(set.getLong("id")),
//						Field.Store.YES, Field.Index.ANALYZED);
//				Field type = new Field("type", String.valueOf(set
//						.getInt("type")), Field.Store.YES, Field.Index.ANALYZED);
//				Field clickCount = new Field("clickCount", String.valueOf(set
//						.getInt("clickCount")), Field.Store.YES,
//						Field.Index.ANALYZED);
//				Field shareCount = new Field("shareCount", String.valueOf(set
//						.getInt("shareCount")), Field.Store.YES,
//						Field.Index.ANALYZED);
//				Field createTime = new Field("createTime", String.valueOf(set
//						.getDate("createTime")), Field.Store.YES,
//						Field.Index.ANALYZED);
//				Field creater = new Field("creater", String.valueOf(set
//						.getString("creater")), Field.Store.YES,
//						Field.Index.ANALYZED);
//				Field title = new Field("title", String.valueOf(set
//						.getString("title")), Field.Store.YES,
//						Field.Index.ANALYZED);
//				doc.add(id);
//				doc.add(type);
//				doc.add(clickCount);
//				doc.add(shareCount);
//				doc.add(createTime);
//				doc.add(creater);
//				doc.add(title);
//				write.addDocument(doc);
//			}
//
//			no = write.maxDoc();
//			write.close();
//		} catch (IOException e) {
//			e.getStackTrace();
//		} catch (SQLException e1) {
//			e1.getSQLState();
//		} catch (Exception e2) {
//			e2.getStackTrace();
//		}
//		return no;
//	}

//	public static TopDocs searchLucence(String keyword) throws IOException,
//			ParseException {
//
//		TopDocs topDocs = null;
//		try {
//			IndexReader reader = DirectoryReader.open(FSDirectory
//					.open(new File(Constants.INDEX_FILE_PATH).toPath()));
//			searcher = new IndexSearcher(reader);
//
//			QueryParser paser = new QueryParser("title", new StandardAnalyzer());
//			Query query = paser.parse(keyword);
//			topDocs = searcher.search(query, 10);
//		} catch (IOException e1) {
//			e1.getStackTrace();
//		} catch (ParseException e2) {
//			e2.getStackTrace();
//		} catch (Exception e3) {
//			e3.getStackTrace();
//		}
//		return topDocs;
//
//	}

}
