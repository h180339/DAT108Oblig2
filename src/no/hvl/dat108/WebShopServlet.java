package no.hvl.dat108;



import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static no.hvl.dat108.UrlMappings.LOGIN_URL;
import static no.hvl.dat108.UrlMappings.WEBSHOP_URL;

@WebServlet(WEBSHOP_URL)
public class WebShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);


		if (sesjon!= null) {

			Cart cart = (Cart) sesjon.getAttribute("cart");

			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Web Shop</title>");
			out.println("</head>");
			out.println("<body>");


			out.println("<h2>Min handlekurv:</h2>");
			out.println("<form action=\"" + WEBSHOP_URL + "\" method=\"post\">");
			out.println("<input style=\"margin-right:20px\" type=\"submit\" value=\"Legg i handlekurv\" />" + "<input type=\"text\" name=\"vareInput\" />");
			out.println("</form>");

			out.println("<form action=\"" + WEBSHOP_URL + "\" method=\"post\">");
			List<CartItem> list = cart.getItems();
			for (CartItem item : list) {
				out.println("<p>" + "<button style=\"margin-right:20px\" type=\"submit\" value=\"" + item.getName() + "\" name=\"sletting\"/>slett </button>" + item.getName() + "</p>");
			}

			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} else {
			response.sendRedirect("/" + LOGIN_URL + "?requiresLogin");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);

		if(sesjon == null) {
			response.sendRedirect(LOGIN_URL + "?requiresLogin");

		}else {
			Cart cart = (Cart) sesjon.getAttribute("cart");

			if (request.getParameter("sletting") != null) {
				String sletteItemNavn = StringEscapeUtils.escapeHtml4(request.getParameter("sletting"));
				CartItem sletteItem = (CartItem) sesjon.getAttribute(sletteItemNavn);
				cart.removeItem(sletteItem);
			}else {
				String itemNavn = StringEscapeUtils.escapeHtml4(request.getParameter("vareInput"));
				if(!itemNavn.equals("")) {
					CartItem newCartItem = new CartItem(itemNavn);
					cart.addItem(newCartItem);
					if(itemNavn != null) {
						sesjon.setAttribute(itemNavn, newCartItem);
					}
				}
			}
			response.sendRedirect(WEBSHOP_URL);
		}
}
}
