package no.hvl.dat108;

import static no.hvl.dat108.UrlMappings.LOGIN_URL;
import static no.hvl.dat108.UrlMappings.WEBSHOP_URL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name ="LoggInnServlet", urlPatterns = "/" + LOGIN_URL)
public class LoggInnServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String riktigPassord;
    int loginTime;

    @Override
    public void init() throws ServletException {
        riktigPassord = getServletConfig().getInitParameter("RiktigPassord");
        loginTime = Integer.parseInt(getServletConfig().getInitParameter("LoginTime"));
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Inn noe kode her i forbindelse med evt. feilmeldinger?
        String invalidPassword = request.getParameter("invalidPassword");
        String feilmelding = "";
        if (invalidPassword != null) {
            feilmelding = "Feil passord";
        }

        response.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();

        //html head
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        
        // Inn noe kode her i forbindelse med evt. feilmeldinger?
        out.println("<p>" + feilmelding + "</p>");

        //html for login
        out.println("<form action=\"" + LOGIN_URL + "\" method=\"post\">");
        out.println("  <fieldset>");
        out.println("    <legend>Login</legend>");
        out.println("    <p>Passord: <input type=\"password\" name=\"passord\" /></p>");
        out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
        out.println("  </fieldset>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Inn noe kode her i forbindelse med innlogging av bruker?
        String passord = request.getParameter("passord");
        if(passord == null || !passord.equals(riktigPassord)) {
            response.sendRedirect(LOGIN_URL + "?invalidPassword");

        }else {

            HttpSession sesjon = request.getSession(false);
            if(sesjon != null) {
                sesjon.invalidate();
            }
            sesjon = request.getSession(true);
            sesjon.setMaxInactiveInterval(loginTime);
            sesjon.setAttribute("bruker",passord );

            // Inn noe kode her i forbindelse med oppretting av sesjonsdata?
            sesjon.setAttribute("cart", new Cart());


            response.sendRedirect(WEBSHOP_URL);
        }

    }
}
