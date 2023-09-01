package com.canr.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabacerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod();
        String requestUri =  req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String esquema = req.getScheme();
        String host = req.getHeader("host");
        String url = esquema + "://" + host + contextPath + servletPath;

        try(PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Cabeceras Http Request</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Cabeceras Http Request</h1>");
            out.print("          <ul>");
            out.print("             <li>Método Http: " + metodoHttp + "</li>");
            out.print("             <li>Request Uri: " + requestUri + "</li>");
            out.print("             <li>Request Url: " + requestUrl + "</li>");
            out.print("             <li>Context Path: " + contextPath + "</li>");
            out.print("             <li>Servlet Http: " + servletPath + "</li>");
            out.print("             <li>Ip local: " + ip + "</li>");
            out.print("             <li>Puerto: " + port + "</li>");
            out.print("             <li>Esquema: " + esquema + "</li>");
            out.print("             <li>Host: " + host + "</li>");
            out.print("             <li>URL: " + url + "</li>");
            out.println("             <li>Ip Cliente: " + ipCliente + "</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.print("         <li>" + cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.print("          </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
}
