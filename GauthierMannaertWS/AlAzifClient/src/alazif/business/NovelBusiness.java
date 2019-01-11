package alazif.business;

import java.util.ArrayList;
import java.util.List;

import alazif.dao.NovelDAO;
import alazif.javabean.Novel;
import alazif.javabean.Writer;

public class NovelBusiness {
	
	private Novel n;
	private NovelDAO ndao;
	
	public NovelBusiness() {
		n = new Novel();
		ndao = new NovelDAO();
	}

	public Novel getN() {
		return n;
	}

	public void setN(Novel n) {
		this.n = n;
	}
	
	public Novel[] getListNovel(){
		return ndao.getAll();
	}
	
	public Novel[] getFromWriter(int writerId)
	{
		return ndao.getFromWriter(writerId);
	}
	
	public void instanciate(int novelId)
	{
		n=ndao.find(Integer.toString(novelId));
	}
	
	public boolean addNovel(String t, int y, Writer w, String s)
	{
		n=new Novel(t,y,w,s);
		return ndao.create(n);
	}
	
	public boolean deleteNovel(int nId)
	{
		n.setNovelId(nId);
		return ndao.delete(n);
	}
}
