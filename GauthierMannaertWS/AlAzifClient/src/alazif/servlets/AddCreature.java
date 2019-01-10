package alazif.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.CreatureBusiness;
import alazif.business.NovelBusiness;
import alazif.business.WriterBusiness;
import alazif.javabean.CreatureName;
import alazif.javabean.Novel;
import alazif.javabean.Writer;

@WebServlet("/AddCreature")
public class AddCreature extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String erreur;
    
    public AddCreature() {
        super();
        erreur="";
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer[] allW=new WriterBusiness().getListWriter();
		request.setAttribute("allW", allW);
		Novel[] allN=new NovelBusiness().getListNovel();
		request.setAttribute("allN", allN);
		getServletContext().getRequestDispatcher("/views\\newCreature.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		erreur="";
		CreatureBusiness cBusi=new CreatureBusiness();
		
		Writer w=null;
		Set<Novel> ns=new HashSet<Novel>();
		Set<CreatureName> na=new HashSet<CreatureName>();
		
		String cn=request.getParameter("name");
		if(cn == null) {
			erreur += "Le nom est null, ";
		}
		else if(cn.isEmpty()) {
			erreur += "Le nom est vide, ";
		}
		else
		{
			na.add(new CreatureName(cn));
		}
		
		String desc=request.getParameter("desc");
		if(desc == null) {
			erreur += "La description est null, ";
		}
		else if(desc.isEmpty()) {
			erreur += "La description est vide, ";
		}
		
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
		
		try
		{
			String novels[]=request.getParameterValues("novels");
			NovelBusiness nBusi=new NovelBusiness();
			for(String s : novels)
			{
				int novelId=Integer.parseInt(s);
				nBusi.instanciate(novelId);
				ns.add(nBusi.getN());
			}
			
		}
		catch(NullPointerException n) {erreur+="Le tableau d'id novel est nul, ";}
		catch(NumberFormatException n) {erreur+="L'id novel n'est pas entier, ";}		
		
		if(erreur.equals(""))
		{
			erreur+="Erreur vide, jusqu'ici tout va bien...";
			if(cBusi.addCreature(desc, w, ns, na))
			{
				response.sendRedirect("/AlAzifClient/creaturelist");
			}
			else
			{
				erreur+="Erreur de requête :/";
				request.setAttribute("erreur", erreur);
				Writer[] allW=new WriterBusiness().getListWriter();
				request.setAttribute("allW", allW);
				Novel[] allN=new NovelBusiness().getListNovel();
				request.setAttribute("allN", allN);
				getServletContext().getRequestDispatcher("/views\\newCreature.jsp").forward(request, response);
			}
		}	
		else
		{
			erreur += "Params : ("+cn+"/"+desc+") Names : (";
			for(CreatureName cname : na)
			{
				if(cname!=null)
					erreur+=cname.getName();
			}
			erreur+=") Novels : ";
			for(Novel nov : ns)
			{
				if(nov!=null)
					erreur+=nov.getTitle()+" ";
			}
			erreur+="Ecivain : ";
			if(w!=null)
			{
				erreur+=w.getWriterId()+" / "+w.getLastName()+";";
			}
			request.setAttribute("erreur", erreur);
			Writer[] allW=new WriterBusiness().getListWriter();
			request.setAttribute("allW", allW);
			Novel[] allN=new NovelBusiness().getListNovel();
			request.setAttribute("allN", allN);
			getServletContext().getRequestDispatcher("/views\\newCreature.jsp").forward(request, response);
		}
	}
}
