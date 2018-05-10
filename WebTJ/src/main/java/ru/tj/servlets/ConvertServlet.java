package ru.tj.servlets;

import ru.tj.models.NodeObject;
import ru.tj.utils.NodeObjUtil;
import ru.tj.utils.StaxParser;
import ru.tj.utils.StaxParserDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(urlPatterns = "/convert")
@MultipartConfig
public class ConvertServlet extends HttpServlet {
    private StaxParser parser;
    @Override
    public void init() throws ServletException {
        parser = new StaxParserDemo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("json", "Please load XML file to convert...");
        request.getServletContext().getRequestDispatcher("/jsp/convert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "";
        if (request.getParameter("submit") != null) {
            Part filePart = request.getPart("file");

            if (filePart.getSize() > 0 && filePart.getContentType().equals("text/xml")) {
                InputStream fileContent = filePart.getInputStream();

                NodeObject node = parser.parse(fileContent);

                NodeObjUtil.setValuesToParents(node);
                json = NodeObjUtil.convert(node);

                fileContent.close();
            }
        }
        request.setAttribute("json", json);
        request.getServletContext().getRequestDispatcher("/jsp/convert.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
