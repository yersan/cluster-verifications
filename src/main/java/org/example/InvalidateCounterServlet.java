package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

/**
 * @author <a href="mailto:yborgess@redhat.com">Yeray Borges</a>
 */
@WebServlet({"/invalidate"})
public class InvalidateCounterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = Logger.getLogger(InvalidateCounterServlet.class);

    public InvalidateCounterServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = "Cluster Verification";
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        session.invalidate();
        LOGGER.info("Invalidate Session");
        PrintWriter out = response.getWriter();
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">";
        String head = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/table.css\"><title>Cluster Verification</title></head>";
        out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\"><html lang=\"en\" class><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/table.css\"><title>Cluster Verification</title></head><body>\n<div class=\"conatainer\">\n<div class=\"gold\">\n<h1>Session ID</h1>\n<h2>" + session.getId() + "<h2>\n<div class=\"price\"> times</div>\n<p>IP Address</p><span></span>\n<p>Hostname</p><span></span>\n<p>Last Accessed</p><span></span>\n<button><a href=\"invalidate\" class=\"button\">Invalidate</a></button></div></body></html>");
        response.sendRedirect(request.getContextPath() + "/counter");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
