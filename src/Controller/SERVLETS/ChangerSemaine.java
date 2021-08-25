package Controller.SERVLETS;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Servlet implementation class ChangerSemaine
 */
@WebServlet("/ChangerSemaine")
public class ChangerSemaine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangerSemaine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    LocalDate ceLundi = LocalDate.now().with(previousOrSame(MONDAY));
		LocalDate lundi = (LocalDate.parse(request.getParameter("jour"))).plusDays(Integer.parseInt(request.getParameter("jours")));
		if ( DayOfWeek.from(LocalDate.now()).getValue() <= 5)
		{
			if(lundi.compareTo(ceLundi) < 0) {
				response.getWriter().write("");
				return;
			}
		}
		else 
		{
			if((lundi).compareTo(ceLundi.plusDays(7)) < 0) {
				response.getWriter().write("");
				return;
			}
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM YYYY");
		LocalDate mardi 	= lundi.plusDays(1);
		LocalDate mercredi 	= mardi.plusDays(1);
		LocalDate jeudi 	= mercredi.plusDays(1);
		LocalDate vendredi 	= jeudi.plusDays(1);
		String text = lundi+";"+formatter.format(lundi)+";"+mardi+";"+formatter.format(mardi)+";"+mercredi+";"+formatter.format(mercredi)+";"+jeudi+";"+formatter.format(jeudi)+";"+vendredi+";"+formatter.format(vendredi);
		
	    response.getWriter().write(text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
