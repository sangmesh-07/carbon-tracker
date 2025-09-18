package com.carbontracker;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>History</title>");
        out.println("<link rel='stylesheet' href='style.css'></head><body>");

        out.println("<header><h1>📖 Carbon Footprint History</h1>");
        out.println("<nav><a href='index.html'>Home</a><a href='DashboardServlet'>Dashboard</a><a href='HistoryServlet'>History</a></nav></header>");

        out.println("<main><section class='card'><h2>Your Records</h2>");
        out.println("<table><thead><tr><th>Date</th><th>Electricity</th><th>Fuel</th><th>Travel</th><th>Total(kg)</th></tr></thead><tbody>");

        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT entry_date, electricity, fuel, travel, total FROM carbon_history");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getDate("entry_date") + "</td>");
                out.println("<td>" + rs.getDouble("electricity") + "</td>");
                out.println("<td>" + rs.getDouble("fuel") + "</td>");
                out.println("<td>" + rs.getDouble("travel") + "</td>");
                out.println("<td>" + rs.getDouble("total") + "</td>");
                out.println("</tr>");
            }
            con.close();
        } catch (Exception e) {
            out.println("<tr><td colspan='5'>Error: " + e.getMessage() + "</td></tr>");
        }

        out.println("</tbody></table></section></main>");
        out.println("</body></html>");
    }
}
