package com.canr.apiservlet.webapp.headers.controllers;

import com.canr.apiservlet.webapp.headers.models.Producto;
import com.canr.apiservlet.webapp.headers.services.ProductoService;
import com.canr.apiservlet.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html", "/productos"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }
        try(PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Listado de Productos</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Listado de Productos</h1>");
                out.print("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a XLS</a></p>");
                out.print("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar Json</a></p>");
            }
            out.println("        <table>");
            out.println("           <tr>");
            out.println("               <th>id</th>");
            out.println("               <th>nombre</th>");
            out.println("               <th>tipo</th>");
            out.println("               <th>precio</th>");
            out.println("           </tr>");
            productos.forEach(p ->{
                out.print("<tr>");
                out.print("<td>" + p.getId() + "</td>");
                out.print("<td>" + p.getNombre() + "</td>");
                out.print("<td>" + p.getTipo() + "</td>");
                out.print("<td>" + p.getPrecio() + "</td>");
                out.print("</tr>");
            });
            out.println("        </table>");
            if (!esXls) {
                out.println("    </body>");
                out.println("</html>");
            }
        }
    }
}
