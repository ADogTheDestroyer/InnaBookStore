# LookInnaBook Final Project for COMP3005 Winter 2022
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
3. Open the project using IntelliJ and add a new database connection by clicking "+" then clicking "Data Source". This is located in the "Database" tab located on the right of the IDE. You may be prompted to download a driver for postgreSQL, go ahead with this.
4. Enter "postgres" in the user field, your pgAdmin password in the password field, and "LookInnaBook" in the database field. Test you connection, then click apply and okay.
5. Open Config.java and change connectionURL to match your pgAdmin db (should be the same as connection for IntelliJ), ensure your username is "postgres" and password is your pgAdmin password.
6. Run "InitDatabase.sql" in either an IntelliJ postgreSQL console or a pgAdmin query console of the database LookInnaBook.
7. Run the program from the main method point located in InnaBookStore.java
8. Test and enjoy :)

## Additional Notes:
1. If you are running the sql scripts from your pgAdmin, you will need to copy and paste the sql from InitDatabase.txt

2. Each class aside from Config, InnaBookStore and StoreStats exist to Abstract/encapsulate complicated connection and sql code from the InnaBookStore (our main class)

3. If you are not running this code form IntelliJ Ultimate, you will need to
- download the connector driver from https://jdbc.postgresql.org/ then add it to the modules from File > Project Structure > Modules > Dependencies
- add a pluggin called "Database Navigator" from File > Settings > Pluggins
