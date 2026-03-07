================================================================
  DeliveryAutoAlert — Maven Web Application
  JDK 8 | Tomcat 9 | SQL Server 2019 | NetBeans 13
================================================================

BEFORE OPENING IN NETBEANS — do this ONE TIME in a terminal:
----------------------------------------------------------------

1. Install sqljdbc4.jar into your local Maven repository.
   Open Command Prompt / Terminal and run:

   mvn install:install-file ^
     -Dfile="src\main\webapp\WEB-INF\lib\sqljdbc4.jar" ^
     -DgroupId=com.microsoft.sqlserver ^
     -DartifactId=sqljdbc4 ^
     -Dversion=4.0 ^
     -Dpackaging=jar

   (Linux/Mac — replace ^ with \ for line continuation)

2. Run the SQL schema in SSMS:
   Open DeliveryAutoAlert_Schema.sql → Execute (F5)

3. Insert the first admin user (password = admin123):

   INSERT INTO Roles(role_name, description)
   VALUES ('ADMIN','Administrator');

   INSERT INTO Users(username,password_hash,full_name,email,role_id,is_active)
   VALUES ('admin',
           '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
           'System Admin','admin@local.com',1,1);

================================================================
OPENING IN NETBEANS 13
================================================================

1. File → Open Project → select this folder (DeliveryAutoAlert)
   NetBeans will recognise it as a Maven Web Application automatically.

2. Right-click project → Properties → Run
   Server: Apache Tomcat 9.x
   (Add Tomcat if not listed: Tools → Servers → Add Server)

3. Right-click project → Run  (or F6)
   NetBeans will download dependencies, compile, deploy, and open:
   http://localhost:8080/DeliveryAutoAlert/

4. Log in with:  admin / admin123

================================================================
PROJECT STRUCTURE
================================================================

DeliveryAutoAlert/
├── pom.xml                          ← Maven build file
├── README.txt                       ← This file
└── src/
    └── main/
        ├── java/                    ← All Java source packages
        │   ├── DAO/                 ← Data Access Layer
        │   ├── DTO/                 ← Data Transfer Objects
        │   │   ├── accounting/
        │   │   ├── alert/
        │   │   ├── devlivery/       ← note: intentional typo
        │   │   ├── master/
        │   │   └── warehouse/
        │   ├── controller/          ← Servlets (@WebServlet)
        │   │   └── filter/          ← AuthFilter
        │   ├── service/             ← Business logic
        │   └── utils/               ← DBUtils (JDBC connection)
        ├── resources/               ← (empty, reserved)
        └── webapp/                  ← Web root
            ├── WEB-INF/
            │   ├── web.xml
            │   └── lib/
            │       └── sqljdbc4.jar
            ├── assets/css/style.css
            ├── login.jsp
            ├── nav.jsp
            ├── accounting/
            ├── alert/
            ├── auth/
            ├── catalog/
            ├── delivery/
            ├── search/
            └── warehouse/

================================================================
JDBC CONNECTION (src/main/java/utils/DBUtils.java)
================================================================

  URL:      jdbc:sqlserver://localhost:1433;databaseName=DeliveryAutoAlert;
  Username: sa
  Password: 12345

  Change these if your SQL Server uses different credentials.

================================================================
