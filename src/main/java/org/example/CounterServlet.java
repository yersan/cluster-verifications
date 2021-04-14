package org.example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;

@WebServlet({"/counter"})
public class CounterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = Logger.getLogger(CounterServlet.class);

    public CounterServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int visitCount;
        if (session.isNew()) {
            LOGGER.info("New Session");
            visitCount = 1;
        } else if (session.getAttribute("counter") != null) {
            visitCount = (Integer)session.getAttribute("counter");
            ++visitCount;
            LOGGER.info("Increment visitCount to " + visitCount);
        } else {
            LOGGER.info("Session is invalidated");
            visitCount = 1;
        }

        session.setAttribute("counter", visitCount);
        InetAddress ip = InetAddress.getLocalHost();
        String hostname = ip.getHostName();
        Date now = new Date(session.getLastAccessedTime());
        LOGGER.info("Session:" + session.getId() + ", IP :" + ip + ", Hostname: " + hostname + ", Visit Count:" + visitCount);
        String title = "Cluster Verification";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">";
        String head = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/table.css\"><title>Cluster Verification</title></head>";
        out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\"><html lang=\"en\" class><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/table.css\"><title>Cluster Verification</title></head><body>\n<div class=\"conatainer\">\n<div class=\"gold\">\n<h1>Session ID</h1>\n<h2>" + session.getId() + "<h2>\n<div class=\"price\">" + visitCount + " times</div>\n<p>IP Address</p><span>" + ip + "</span>\n<p>Hostname</p><span>" + hostname + "</span>\n<p>Last Accessed</p><span>" + now + "</span>\n<button><a href=\"counter\" class=\"button\">Increment</a></button><button><a href=\"invalidate\" class=\"button\">Invalidate</a></button></div></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
