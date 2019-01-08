package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.CreatureBusiness;
import alazif.javabean.Creature;

@WebServlet("/CreatureModel")
public class CreatureModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreatureModel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String creatureIndex=request.getParameter("id");
		int in;
		CreatureBusiness cBusi=new CreatureBusiness();
		try
		{
			in=Integer.parseInt(creatureIndex);
		}
		catch(NumberFormatException e)
		{
			in=1;
		}
		cBusi.instanciate(in);
		Creature c=cBusi.getC();
		request.setAttribute("creature", c);
		
		getServletContext().getRequestDispatcher("/views\\creature.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
