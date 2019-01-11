package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.CreatureBusiness;
import alazif.business.NovelBusiness;
import alazif.business.WriterBusiness;

@WebServlet("/DeleteElement")
public class DeleteElement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int id=0;
	private char type;
       
    public DeleteElement() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		type=request.getParameter("type").charAt(0);
    	String index=request.getParameter("id");
		try
		{
			id=Integer.parseInt(index);
			if(id!=0)
			{
				switch(type)
				{
				 	case 'c': 	CreatureBusiness cBusi=new CreatureBusiness();
				 				cBusi.deleteCreature(id);
				 				break;
				 	case 'n': 	NovelBusiness nBusi=new NovelBusiness();
				 				nBusi.deleteNovel(id);
				 				break;
				 	case 'w':	WriterBusiness wBusi=new WriterBusiness();
				 				wBusi.deleteWriter(id);
				 				break;
				}	
			}
		}
		catch(NumberFormatException e) {}
		String url="/AlAzifClient/connection";;
		switch(type)
		{
			case 'c': 	url="/AlAzifClient/creaturelist";
						break;
			case 'n': 	url="/AlAzifClient/novellist";
						break;
			case 'w':	url="/AlAzifClient/writerlist";
						break;
		}
		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
