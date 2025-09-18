package com.carbontracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet({"/dashboard", "/DashboardServlet"})
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // DB values
        double todayTotal = 0;
        double weeklyTotal = 0;
        double monthlyTotal = 0;

        try (Connection conn = DBUtil.getConnection()) {
            // Today
            PreparedStatement ps1 = conn.prepareStatement(
                "SELECT SUM(total) FROM carbon_history WHERE DATE(entry_date)=CURDATE()");
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) todayTotal = rs1.getDouble(1);

            // Last 7 days
            PreparedStatement ps2 = conn.prepareStatement(
                "SELECT SUM(total) FROM carbon_history WHERE entry_date >= CURDATE() - INTERVAL 7 DAY");
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) weeklyTotal = rs2.getDouble(1);

            // This month
            PreparedStatement ps3 = conn.prepareStatement(
                "SELECT SUM(total) FROM carbon_history WHERE MONTH(entry_date)=MONTH(CURDATE())");
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) monthlyTotal = rs3.getDouble(1);

        } catch (Exception e) {
            e.printStackTrace(out);
        }

        // HTML dashboard
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Dashboard - Carbon Tracker</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin:0; background:#f4f7fa; }");
        out.println("header { background:linear-gradient(135deg,#2ecc71,#27ae60); color:white; padding:1rem 2rem; }");
        out.println("nav a { color:white; margin:0 10px; text-decoration:none; font-weight:bold; }");
        out.println(".dashboard { display:grid; grid-template-columns:repeat(auto-fit,minmax(250px,1fr)); gap:20px; padding:2rem; }");
        out.println(".card { background:white; padding:1.5rem; border-radius:12px; box-shadow:0 4px 10px rgba(0,0,0,0.1); text-align:center; }");
        out.println(".card h2 { margin-bottom:1rem; }");
        out.println(".highlight { background:linear-gradient(135deg,#3498db,#2980b9); color:white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Header
        out.println("<header>");
        out.println("<h1>🌍 Carbon Tracker Dashboard</h1>");
        out.println("<nav>");
        out.println("<a href='index.html'>Home</a>");
        out.println("<a href='dashboard' style='text-decoration:underline'>Dashboard</a>");
        out.println("<a href='history.html'>History</a>");
        out.println("</nav>");
        out.println("</header>");

        // Dashboard content
        out.println("<main class='dashboard'>");

        out.println("<section class='card highlight'>");
        out.println("<h2>Today’s Emissions</h2>");
        out.println("<p style='font-size:2rem; font-weight:bold;'>" + todayTotal + " kg CO₂</p>");
        out.println("</section>");

        out.println("<section class='card'>");
        out.println("<h2>Weekly Total</h2>");
        out.println("<p style='font-size:1.5rem;'>" + weeklyTotal + " kg CO₂</p>");
        out.println("</section>");

        out.println("<section class='card'>");
        out.println("<h2>Monthly Total</h2>");
        out.println("<p style='font-size:1.5rem;'>" + monthlyTotal + " kg CO₂</p>");
        out.println("</section>");

        out.println("<section class='card'>");
        out.println("<h2>Eco Tip 💡</h2>");
        out.println("<p>Switch to LED lights and save up to 80% energy!</p>");
        out.println("</section>");

        out.println("</main>");
        out.println("</body></html>");
    }
}
