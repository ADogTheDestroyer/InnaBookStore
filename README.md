# InnaBookStore

## LookInnaBook Final Project for COMP3005 Winter 2022
A Simple command-line book store application with a PostgreSQL database.

## Tools
- [IntelliJ IDEA Ultimate Edition](https://www.jetbrains.com/idea/download/) (necessary for databse connection)
- [pgAdmin4](https://www.postgresql.org/download/) (necessary for database creation)

## Using the App
1. Clone the Project, this can be done from terminal using the following command:
   ```
   git clone https://github.com/ADogTheDestroyer/InnaBookStore.git
   ```
2. Using pgAdmin create a new database called "LookInnaBook"
3. Open the project using IntelliJ and add a new database connection by clicking "+" then clicking "Data Source". This is located in the "Database" tab located on the right of the IDE
4. Enter "postgres" in the user field, your pgAdmin password in the password field, and "LookInnaBook" in the database field. Test you connection, then click apply and okay.
5. Run "InitDatabase.sql" in either an IntelliJ postgreSQL console or a pgAdmin query console of the database LookInnaBook.
6. Run the program from the main method point located in InnaBookStore.java
7. Test and enjoy :)

