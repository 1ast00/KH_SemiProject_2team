package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TableGeneratorServlet
 */
@WebServlet("/generateTable")
public class TableGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TableGeneratorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r = (String)request.getParameter("rows");
		String c = (String)request.getParameter("cols");
		
		int rows = Integer.parseInt(r);
		int cols = Integer.parseInt(c);
		
		request.setAttribute("numRows", rows);
		request.setAttribute("numCols", cols);		
		
		request.getRequestDispatcher("./tableResult.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
