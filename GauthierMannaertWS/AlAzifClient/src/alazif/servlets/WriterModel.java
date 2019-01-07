package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.NovelBusiness;
import alazif.business.WriterBusiness;
import alazif.javabean.Novel;
import alazif.javabean.Writer;

/**
 * Servlet implementation class WriterModel
 */
@WebServlet("/WriterModel")
public class WriterModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriterModel() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writerIndex=request.getParameter("id");
		int in;
		WriterBusiness wBusi = new WriterBusiness();
		try
		{
			in=Integer.parseInt(writerIndex);
		}
		catch(NumberFormatException e)
		{
			in=1;
		}
		wBusi.instanciate(in);
		Writer w=wBusi.getW();
		request.setAttribute("writer", w);
		NovelBusiness nBusi=new NovelBusiness();
		Novel[] novels=nBusi.getFromWriter(in);
		request.setAttribute("novels", novels);
		
		getServletContext().getRequestDispatcher("/views\\writer.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
