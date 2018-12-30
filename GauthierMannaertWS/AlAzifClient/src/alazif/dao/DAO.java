package alazif.dao;
//import java.sql.Connection;

public abstract class DAO<T> 
{	
	protected static String baseUrl = "http://localhost:9090/NecroWEB/api/";
	
	public DAO(){}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(String search);

}