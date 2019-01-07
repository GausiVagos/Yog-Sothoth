package alazif.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.CreatureBusiness;
import alazif.business.CriticBusiness;
import alazif.business.CriticRow;
import alazif.business.NovelBusiness;
import alazif.javabean.Creature;
import alazif.javabean.Novel;

@WebServlet("/NovelModel")
public class NovelModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NovelModel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String novelIndex=request.getParameter("id");
		int in;
		NovelBusiness nBusi = new NovelBusiness();
		try
		{
			in=Integer.parseInt(novelIndex);
		}
		catch(NumberFormatException e)
		{
			in=1;
		}
		nBusi.instanciate(in);
		Novel n=nBusi.getN();
		request.setAttribute("novel", n);
		CreatureBusiness cBusi=new CreatureBusiness();
		Creature[] creatures=cBusi.getCreaturesFromNovel(in);
		request.setAttribute("creatures", creatures);
		Set<CriticRow> critics=new CriticBusiness().getCriticsFromNovel(in);
		request.setAttribute("critics", critics);
		
		getServletContext().getRequestDispatcher("/views\\novel.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
