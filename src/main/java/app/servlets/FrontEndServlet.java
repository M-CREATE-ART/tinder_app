package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrontEndServlet extends HttpServlet {
    private final String subpath;

    public FrontEndServlet(String subpath) {
        this.subpath = subpath;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getPathInfo();
        String fileLoc = "src/main/resources/content";
        Path path = Paths.get(fileLoc, subpath, filename);

        try(OutputStream os = resp.getOutputStream()) {
            Files.copy(path, os);
        }
    }
}
