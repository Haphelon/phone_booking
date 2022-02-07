The application is designed with a mariadb database with hibernate therefore so long as database details are configured
correctly on the application.yml file the tables will auto generate.

The application functions include:

- Users
    - Add user
    - Get user booking history
    - Get user details
    - Get all users
- Devices
    - Add device
    - Get devices
    - Get device
    - Get device booking history
- Booking
    - Book device
    - Get booking details
    - Return device

Note:- All device details are stored in the database and therefore need to be added

A sample SQL file that can be used to generate the database has also been provided.

Device details are fetched from a mock server designed by me as fonoapi was down during this exercise