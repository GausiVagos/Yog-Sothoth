package alazif.business;

import java.util.ArrayList;
import java.util.List;

import alazif.dao.NovelDAO;
import alazif.javabean.Novel;

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
	
	public List<Novel> getListNovel(){
		Novel[] tabNov = ndao.getAll();
		List<Novel> lNov = new ArrayList<Novel>();
		
		for(Novel nov : tabNov) {
			lNov.add(nov);
		}
		
		return lNov;
	}
}
