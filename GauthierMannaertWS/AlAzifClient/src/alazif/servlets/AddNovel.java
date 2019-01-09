package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.NovelBusiness;
import alazif.business.WriterBusiness;
import alazif.javabean.Writer;

@WebServlet("/AddNovel")
public class AddNovel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String erreur;
       
    public AddNovel() {
        super();
        erreur="";
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriterBusiness wBusi=new WriterBusiness();
		Writer[] all=wBusi.getListWriter();
		request.setAttribute("all", all);
		getServletContext().getRequestDispatcher("/views\\newNovel.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		erreur="";
		Writer w=null;
		NovelBusiness nBusi=new NovelBusiness();
		String t=request.getParameter("title");
		int y=0;
		try
		{
			y=Integer.parseInt(request.getParameter("year"));
		}
		catch(NullPointerException n) {erreur+="L'année est nulle, ";}
		catch(NumberFormatException n) {erreur+="L'année n'est pas un entier, ";}
		
		try
		{
			int writerId=Integer.parseInt(request.getParameter("writer"));
			WriterBusiness wBusi=new WriterBusiness();
			wBusi.instanciate(writerId);
			w=wBusi.getW();
		}
		catch(NullPointerException n) {erreur+="L'id auteur est nul, ";}
		catch(NumberFormatException n) {erreur+="L'id auteur n'est pas entier, ";}
		if(w==null)
			erreur+="La référence auteur est nulle, ";
		
		String s=request.getParameter("syn");
		
		if(t == null) {
			erreur += "Le titre est null, ";
		}
		else if(t.isEmpty()) {
			erreur += "Le titre est vide, ";
		}
		
		if(s == null) {
			erreur += "Le synopsis est null, ";
		}
		else if(s.isEmpty()) {
			erreur += "Le synopsis est vide, ";
		}
		
		if(erreur.equals("")&&nBusi.addNovel(t,y,w,s))
			response.sendRedirect("/AlAzifClient/novellist");
		else
		{
			erreur += "Erreur de requête : ("+t+"/"+y+"/"+w.getLastName()+")";
			request.setAttribute("erreur", erreur);
			WriterBusiness wBusi=new WriterBusiness();
			Writer[] all=wBusi.getListWriter();
			request.setAttribute("all", all);
			getServletContext().getRequestDispatcher("/views\\newNovel.jsp").forward(request, response);
		}
	}

}
