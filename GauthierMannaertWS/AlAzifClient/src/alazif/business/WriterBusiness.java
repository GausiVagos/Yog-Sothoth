package alazif.business;

import java.util.ArrayList;
import java.util.List;

import alazif.dao.WriterDAO;
import alazif.javabean.Writer;

public class WriterBusiness {
	
	private Writer w;
	private WriterDAO wdao;
	
	public WriterBusiness() {
		w = new Writer();
		wdao = new WriterDAO();
	}

	public Writer getW() {
		return w;
	}

	public void setW(Writer w) {
		this.w = w;
	}
	
	public List<Writer> getListWriter(){
		
		Writer[] tabWri = wdao.getAll();
		List<Writer> lWri = new ArrayList<Writer>();
		
		for(Writer wri : tabWri) {
			lWri.add(wri);
		}
		
		return lWri;
	}
}
