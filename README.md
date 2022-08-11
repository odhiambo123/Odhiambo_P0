# Odhiambo_P0
P0 Is a console App that will be extended to a full Java-Angular Web App in P1 and P2


# JDBC EXAMPLE

Java Database Connectivity
1. Register the driver
2. create connection
3. create statement
4. execute queries
5. close the connection


# DriverManager Class
- It act as an interface between the user and driver
- it keeps track of the drivers that are available
- handle eastablising the connection between the dataabse and appropriate drivers

> DriverManager class method
- registerDriver(Driver)
- deregisterDriver(Driver)
- getConnection(url)
- getConnection(url, username, password

# Connection Interface
- A connection is the session between the java application and dataabse
- the connection interface is a factory of the statement, prepareStatement and DatabaseMetaData

# Connection interface common methods
- createConnection()
- createConnection(resultSetType, resultSetConcurrency)
- setAuthCommit(boolean: status)  // default true
- commit()
- rollback()
- close()


# Statement interface
- it provides method to execute queries with the database
- a statement interface is a factory of resultSet which provide a factory method to get the object of ResultSet

> statement interface common method
- ResultSet executeQuery(String query) - is used to execute the select query and return the object of result
- int executeUpdate(String query) - is used to execute the specified query, create, drop, alter, update, delete, insert etc
- boolean execute(String query) - it is used to execute queries that may return multiple result
- int[] executeBatch() - it is used to execute batch of commands


# ResultSet interface
- it maintain a cursor pointing to a row of a table
- initially the cursor point to the first row

> ResultSet interface common method
- next()
- previous()
- first()
- last()

# PrepareStatement interface
- it is a sub interface of statement
- it is used to execute parameterized query

"insert into employee values (1, 'mark', 'm@gmail.com')"
"insert into employee values (?, ?, ?)"

we can pass parameters (?) for the value

# Why use prepareStatement
1. improve performance - as it will be faster because the query is compiled only once
2. Security - it avoid SQL injection


# Create a console application using MySQL and JDBC
1. Employee
create a class as Employee which represent table and class field represent column
Employee
 - Id
 - Name
 - Email

2. EmployeeDao (Data Access Object)
it is an interface that lists all the methods we want to implement in our application

3. EmployeeDaoImpl
this is a class that implement our EmployeeDao interface and provide the implementation of all the method
we want to write JDBC code to perform database opeartion

4. EmployeeDaoFactory
this is a class that contain a method and return EmployeeDao interface, and the idea is to decouple interface and 
implementation class so that if we change the implmentation we can easily do it

5. dbConfig.properties
this is a particular file in java used to define configuration in a simple formart and also this can be central place
which can be accessed from anywhere to get configuration

6. ConnectionFactory
this is a class responsable for returning connection class object

7. main
in the main class, get the instance of the Dao and call the appropritate method to perform database opeartion


Step:
1. create new java project
2. add JDBC jar file
3. create employee class
4. create employeeDao
5. create EmployeeDaoImpl
6. create EmployeeDaoFactory
7. create dbConfig
8. create ConnectionFactory
9. perform opeartion in main class

