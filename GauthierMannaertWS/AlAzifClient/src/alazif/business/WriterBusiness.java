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
	
	public Writer[] getListWriter(){
		
		return wdao.getAll();
	}
	
	public void instanciate(int i)
	{
		w=wdao.find(Integer.toString(i));
	}
	
	public boolean addWriter(String p, String n, String b)
	{
		w=new Writer(p,n,b);
		return wdao.create(w);
	}
	
	public boolean deleteWriter(int id)
	{
		w.setWriterId(id);
		return wdao.delete(w);
	}
}
