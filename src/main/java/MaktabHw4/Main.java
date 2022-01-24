package MaktabHw4;

import Exceptions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Methods methods = new Methods();
        ExceptionsClass exceptionsClass = new ExceptionsClass();
        Connection connection =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        Scanner scanner = new Scanner(System.in);
        Boolean loginStatus = true;
        Integer loginOrSignUp = null;
        methods.createCustomerTable(connection);
        methods.createCinemaTable(connection);
        methods.createMainAdminTable(connection);
        String firstName;
        String lastName;
        String userName;
        String showTime;
        String movieName;
        String password;
        String cinemaName;
        Boolean adminLogin = false;
        Boolean customerLogin = false;
        Boolean cinemaLogin = false;
        Boolean situation = false;
        Integer order = null;
        int loginCustomerOrCinemaOrAdmin = 0;
        int whatAreYou = 0;
        int id;


        while (loginStatus) {
            try {
                System.out.println("1.Login \n2.SignUp ");
                loginOrSignUp = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("invalid input!  must enter number ");
            } catch (NumberFormatException e) {
                System.out.println("invalid format input! ");
            }
            if (loginOrSignUp == 1) {
                try {
                    System.out.println("What are you ? \n1.Customer \n2.Cinema \n3.Admin ");
                    loginCustomerOrCinemaOrAdmin = scanner.nextInt();
                    scanner.nextLine();

                } catch (InputMismatchException o) {
                    System.out.println("invalid input!  must enter number ");
                } catch (NumberFormatException a) {
                    System.out.println("invalid format input! ");
                }
                if (loginCustomerOrCinemaOrAdmin == 1) {
                    System.out.println("Please enter your username: ");
                    userName = scanner.nextLine();

                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    try {
                        situation = methods.customerLogin(userName, password);
                    } catch (LoginProblemException e) {
                        e.printStackTrace();
                    }

                    if (situation) {
                        customerLogin = true;
                        situation = false;
                    } else System.out.println("wrong username or password! ");

                } else if (loginCustomerOrCinemaOrAdmin == 2) {
                    System.out.println("Please enter your username: ");
                    userName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    try {
                        situation = methods.adminLogin(userName, password);

                    } catch (LoginProblemException e) {
                        e.printStackTrace();
                    }
                    if (situation) {
                        adminLogin = true;
                        situation = false;
                    } else System.out.println("wrong username or password! ");

                } else if (loginCustomerOrCinemaOrAdmin == 3) {
                    System.out.println("Please enter your username: ");
                    userName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();

                    try {
                        situation = methods.cinemaLogin(userName, password);
                    } catch (LoginProblemException e) {
                        e.printStackTrace();
                    }
                    if (situation) {
                        cinemaLogin = true;
                        situation = false;
                    } else System.out.println("wrong username or password! ");

                } else System.out.println("wrong number! ");
            } else if (loginOrSignUp == 2) {
                try {
                    System.out.println("what are you? \n1.Customer \n2.Cinema");
                    whatAreYou = scanner.nextInt();
                } catch (InputMismatchException a) {
                    System.out.println("invalid input!  must enter number ");
                } catch (NumberFormatException o) {
                    System.out.println("invalid format input! ");
                }
            } else System.out.println("wrong number! ");
            scanner.nextLine();
            if (whatAreYou == 1) {
                System.out.println("Please enter your first name: ");
                firstName = scanner.nextLine();
                System.out.println("Please enter your last name:  ");
                lastName = scanner.nextLine();
                System.out.println("Please enter your username: ");
                userName = scanner.nextLine();
                System.out.println("Please enter your password: ");
                password = scanner.nextLine();
                try {
                    methods.customerRegister(firstName, lastName, userName, password);
                    System.out.println("Done! ");
                } catch (FailedToRegisterTheCustomerException e) {
                    e.printStackTrace();
                }


            } else if (whatAreYou == 2) {
                System.out.println("Please enter Cinema name:  ");
                cinemaName = scanner.nextLine();
                System.out.println("Please enter your username: ");
                userName = scanner.nextLine();
                System.out.println("Please enter your password: ");
                password = scanner.nextLine();
                try {
                    methods.cinemaRegister(cinemaName, userName, password);
                    System.out.println("Done! ");
                } catch (FailedToRegisterTheCinemaException e) {
                    e.printStackTrace();
                }


            } else System.out.println("wrong number! ");
        }


        while (adminLogin) {
            System.out.println("1.change cinema's status \n2.Back");
            try {
                order = scanner.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("invalid input!  must enter number ");
            }

            if (order == 1) {
                System.out.println("please enter cinema name: ");
                cinemaName = scanner.nextLine();
                System.out.println("please enter cinema username:");
                userName = scanner.nextLine();
                System.out.println("please enter cinema password:");
                password = scanner.nextLine();
                System.out.println("please enter cinema's status (Allow) or (Deni)");
                String situationInMain = scanner.nextLine();
                try {
                    methods.updateCinema(cinemaName, userName, password, situationInMain);
                } catch (NotInserException e) {
                    e.printStackTrace();
                }
            } else System.out.println("wrong number! ");
        }
        while (customerLogin) {
            System.out.println("1.View tickets \n2.Reserve ticket \n3.Search for movie by name and date \n");
            try {
                order = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("must enter a Digit! ");
            }

            if (order == 1)
                methods.viewTickets();
            else if (order == 2) {
                try {
                    System.out.println("enter number of ticket you want buy: ");
                    int ticketNumber = scanner.nextInt();
                    System.out.println("Enter ticket id:");
                    int ticketId = scanner.nextInt();
                    System.out.println("Enter your username: ");
                    userName = scanner.nextLine();
                    methods.ticketReserving(ticketNumber, ticketId, userName);
                    System.out.println("Done! ");
                } catch (InputMismatchException e) {
                    System.out.println("must enter a Digit! ");
                } catch (FaildeToReservedException e) {
                    e.printStackTrace();
                }

            } else if (order == 3) {
                System.out.println("What is the movie name: ");
                movieName = scanner.nextLine();
                System.out.println("Enter the date you want: (yyyy/mm/dd) ");
                String movieDate = scanner.nextLine();
                try {
                    methods.movieSearchByNameAndDate(movieName, movieDate);
                } catch (FaildeToReservedException e) {
                    e.printStackTrace();
                }


            } else System.out.println("wrong number! ");
        }
        while (cinemaLogin) {
            System.out.println("1.Add ticket \n2.Delete ticket ");
            try {
                order = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("enter only digit! ");
            }

            if (order == 1) {
                System.out.println("enter ticket id: ");
                id = scanner.nextInt();
                System.out.println("enter movie name: ");
                movieName = scanner.nextLine();
                System.out.println("enter cinema name: ");
                cinemaName = scanner.nextLine();
                System.out.println("enter movie show time: ");
                showTime = scanner.nextLine();
                try {
                    methods.addTicket(id, movieName, cinemaName, showTime);
                } catch (FaildeToReservedException e) {
                    e.printStackTrace();
                }
            } else if (order == 2) {
            }
            System.out.println("please enter movie Id: ");
            id = scanner.nextInt();
            try {
                methods.deleteTicket(id);
            } catch (FailedToDeleteTheTicketException e) {
                e.printStackTrace();
            }

        }

        connection.close();
    }
}

