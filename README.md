# 🌱 Carbon Footprint Tracker

A Java web application that helps users track and monitor their daily carbon footprint by recording electricity usage, fuel consumption, and travel distance.
The application calculates total CO₂ emissions and provides historical data visualization.

## ✨ Features

- **Daily Carbon Tracking**: Record electricity (kWh), fuel (liters), and travel (km) usage
- **Automatic CO₂ Calculation**: Uses industry-standard emission factors to calculate total carbon footprint
- **Dashboard View**: Real-time display of today's, weekly, and monthly emissions
- **Historical Data**: View past entries in a tabular format
- **Responsive Design**: Clean, modern UI with mobile-friendly interface
- **Database Storage**: Persistent data storage using MySQL

## 🚀 Technology Stack

- **Backend**: Java Servlets (Jakarta EE)
- **Frontend**: HTML5, CSS3, JavaScript
- **Database**: MySQL 8.0
- **Server**: Apache Tomcat (or any Jakarta EE compatible server)
- **Build Tool**: Maven (implied from structure)

## 📋 Prerequisites

- Java 11 or higher
- MySQL 8.0 or higher
- Apache Tomcat 10+ (or any Jakarta EE compatible server)
- Maven (for building)

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/carbon-tracker.git
cd carbon-tracker
```

### 2. Database Setup
1. Start your MySQL server
2. Run the provided SQL script to create the database and table:
```bash
mysql -u root -p < database.sql
```

### 3. Database Configuration
Update the database connection details in `src/main/java/com/carbontracker/DBUtil.java`:
```java
// Update these values according to your MySQL setup
String url = "jdbc:mysql://localhost:3306/carbon_tracker";
String username = "your_username";
String password = "your_password";
```

### 4. Build the Application
```bash
mvn clean compile package
```

### 5. Deploy to Tomcat
1. Copy the generated WAR file to your Tomcat's `webapps` directory
2. Start Tomcat server
3. Access the application at `http://localhost:8080/carbon-tracker`

## 📖 Usage

### Recording Daily Usage
1. Navigate to the home page
2. Enter your daily consumption:
   - **Electricity**: Enter usage in kWh
   - **Fuel**: Enter fuel consumption in liters
   - **Travel**: Enter distance traveled in kilometers
3. Click "Submit" to save the data

### Viewing Dashboard
- Access the dashboard to see:
  - Today's total emissions
  - Weekly total emissions
  - Monthly total emissions
  - Eco-friendly tips

### Viewing History
- Check the history page to see all your past entries in a table format

## 🧮 Carbon Calculation Formula

The application uses the following emission factors:
- **Electricity**: 0.85 kg CO₂ per kWh
- **Fuel**: 2.3 kg CO₂ per liter
- **Travel**: 0.12 kg CO₂ per kilometer

Total CO₂ = (Electricity × 0.85) + (Fuel × 2.3) + (Travel × 0.12)

## 📁 Project Structure

```
carbon-tracker/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── carbontracker/
│       │           ├── CarbonServlet.java      # Handles form submission
│       │           ├── DashboardServlet.java   # Dashboard data and display
│       │           ├── HistoryServlet.java     # Historical data display
│       │           └── DBUtil.java            # Database connection utility
│       └── webapp/
│           ├── index.html                      # Main entry form
│           ├── history.html                    # History page
│           ├── style.css                       # Styling
│           └── WEB-INF/
│               ├── web.xml                     # Servlet configuration
│               └── lib/                        # JAR dependencies
├── database.sql                                # Database schema
└── README.md
```

## 🔧 Configuration

### Database Connection
The application uses a simple database utility class. For production use, consider:
- Using connection pooling
- Environment variables for database credentials
- Proper error handling and logging

### Servlet Mappings
The application uses both annotation-based and XML-based servlet configuration:
- `/CarbonServlet` - Form submission handler
- `/dashboard` or `/DashboardServlet` - Dashboard display
- `/HistoryServlet` - Historical data display

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## 🙏 Acknowledgments

- Emission factors based on industry standards
- UI design inspired by modern web applications
- Built with Jakarta EE for enterprise-grade reliability



**Make a difference, track your carbon footprint! 🌍**
