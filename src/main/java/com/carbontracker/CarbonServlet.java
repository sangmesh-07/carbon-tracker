package com.carbontracker;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

@WebServlet("/CarbonServlet")
public class CarbonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double electricity = Double.parseDouble(request.getParameter("electricity"));
            double fuel = Double.parseDouble(request.getParameter("fuel"));
            double travel = Double.parseDouble(request.getParameter("travel"));
            double total = electricity * 0.85 + fuel * 2.3 + travel * 0.12;

            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO carbon_history(entry_date, electricity, fuel, travel, total) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ps.setDouble(2, electricity);
            ps.setDouble(3, fuel);
            ps.setDouble(4, travel);
            ps.setDouble(5, total);
            ps.executeUpdate();
            con.close();

            response.sendRedirect("DashboardServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
