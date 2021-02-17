package librarymanagementsystem;
import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem 
{
    public static void main(String[] args)
    {
        System.out.println("Manan Hakani S080");
        System.out.println("Project Title: Library Management System");
        System.out.println("1- Admin Login");      
        System.out.println("0- Exit");
        Scanner sc0 = new Scanner(System.in);
        System.out.println("Enter you choice:");
        int choice = sc0.nextInt();
        switch(choice)
        {
            case 0:
                System.out.println("Thank you for visiting.");
                break;
                
            case 1:               
                System.out.println("Enter Special admin key:");                
                String key = sc0.next();
                if(key.equals("admin"))
                {
                    System.out.println("Admin Login Successfull!");
                    AdminPortal();
                }
                else
                {
                    System.out.println("Invalid Key!, Try Again");
                    AdminPortal();
                }
                break;            
        } 
    }
   
    static int issueid;
    static String issuedate;
    static String issuereturn;
    static int issueperiod;
    static int issuefine;
    static String studname;
    static int studrollno;
    static String status;
    
    //Book Table Static Variable:
    static int book_id;
    static String book_name;
    static String book_author;
    static int book_price;
    static Connection con = null;
    
    public static void AdminPortal()
    {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~    WELCOME TO LIBRARY MANAGEMENT SYSTEM!!!    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose your action:");
        System.out.println("1 : Enter New Book Details:");
        System.out.println("2 : Veiw Books");
        System.out.println("3 : Issue Books:");
        System.out.println("4 : View Issued Book");
        System.out.println("5 : Return Book By Student:");
        System.out.println("6 : Check Defaulters List");
        System.out.println("0 : Exit");
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter you choice:");
        int num = sc.nextInt();
        
        switch(num)
        {
            case 0:
                System.out.println("Thank you for using Library Management System!!");
                break;
                
            case 1:
                AddBook();
                break;
                
            case 2:
                ViewBook();
                break;
                
            case 3:
                IssueBook();
                break;
            
            case 4:
                ViewIssue();
                break;
                
            case 5:
                ReturnBook();
                break;
            
            case 6:
                DefaultList();
                break;    
        } 
    }
    
    public static void AddBook()
    {
        try
        {        
        Scanner sc1 = new Scanner(System.in);
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Librarydb");
        Statement st = con.createStatement();
        
        System.out.println("Add Book Record:");
        System.out.println("----------------------------------------------------");
        System.out.println("Enter books ID:");
        book_id = sc1.nextInt();
                
        System.out.println("Enter book name:");
        book_name = sc1.next();

        System.out.println("Enter book author name:");
        book_author = sc1.next();
        
        System.out.println("Enter book price:");
        book_price = sc1.nextInt();
                
        String str1 = "insert into entry values("+book_id+",'"+book_name+"','"+book_author+"',"+book_price+")";
        // TO get input in string into database the format to get data is : ' " + (name) + " '.
        st.executeUpdate(str1);
        System.out.println("\n"+"~~~~~~~~~~~~~~~~~~~~~   Book Record is inserted successfully !!!    ~~~~~~~~~~~~~~~~");
        con.close();
        AdminPortal();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            AdminPortal();
        }     
    }
    
    public static void ViewBook()
    {
        try
        {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Librarydb");
        System.out.println("All Book Entry:");
        Statement st = con.createStatement();
                
        String str = "select * from entry";                
        ResultSet rs = st.executeQuery(str);
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println(" Printing All book records: \n");
 
        System.out.println(rsmd.getColumnName(1) +"\t\t"+ rsmd.getColumnName(2) +"\t\t"+ rsmd.getColumnName(3) +"\t\t"+ rsmd.getColumnName(4));
        System.out.println("----------------------------------------------------------------------------------------------");
    
        while(rs.next())
        {
            System.out.println(rs.getString("bid") + "\t\t"+ rs.getString("bname") + "\t\t"+ rs.getString("bauthor") +"\t\t"+ rs.getString("bprice"));
        }
        System.out.println("\n");
        con.close();
        AdminPortal();                
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            AdminPortal();
        }               
    }    

    public static void IssueBook()
    {
        try
        {
        Scanner sc2 = new Scanner(System.in);
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Librarydb");
        Statement st = con.createStatement();
        
        System.out.println("Issue book to students:");
        System.out.println("----------------------------------------------------");
        System.out.println("Enter issue id:");
        issueid = sc2.nextInt();
        
        System.out.println("Enter book id:");
        book_id = sc2.nextInt();
                
        System.out.println("Enter issue date(DD-MM-YYYY):");
        issuedate = sc2.next();
        
        System.out.println("Enter period");
        issueperiod = sc2.nextInt();
        
        System.out.println("Enter Student Name:");
        studname = sc2.next();
        
        System.out.println("Enter Student Roll No:");
        studrollno = sc2.nextInt();
        
        System.out.println("Enter Issue Status:");
        status = sc2.next();
                
        String upd = "insert into issued values("+issueid+","+book_id+",'"+issuedate+"','"+issuereturn+"',"+issueperiod+",'"+studname+"',"+studrollno+",'"+status+"')";
        // To get input in string into database the format to get data is : ' " + (name) + " '.
        st.executeUpdate(upd);
        System.out.println("\n"+"~~~~~~~~~~~~~~~~~~~~~  Issue Records inserted successfully !!!    ~~~~~~~~~~~~~~~~");
        con.close();
        AdminPortal();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            AdminPortal();
        }     
    }
    
    public static void ViewIssue()
    {
        try
        {                
        Scanner sc3 = new Scanner(System.in);
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Librarydb");
        Statement st = con.createStatement();
        
        System.out.println("View all the issued books:");
        System.out.println("------------------------------------------------------------------------");
                
        String upd1 = "select * from issued";
        ResultSet rs1 = st.executeQuery(upd1);
        ResultSetMetaData rsmd1 = rs1.getMetaData();
        System.out.println(" Printing all book issued records: \n");
 
        System.out.println(rsmd1.getColumnName(1) +"\t\t"+ rsmd1.getColumnName(2) +"\t\t"+ rsmd1.getColumnName(3) +"\t\t"+ rsmd1.getColumnName(4) +"\t\t"+ rsmd1.getColumnName(5) +"\t\t"+ rsmd1.getColumnName(6) +"\t\t"+ rsmd1.getColumnName(7) +"\t\t"+ rsmd1.getColumnName(8));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    
        while(rs1.next())
        {
            System.out.println(rs1.getString("iid") + "\t\t"+ rs1.getString("bid") + "\t\t"+ rs1.getString("issued_date") +"\t\t"+ rs1.getString("return_date")+ "\t\t\t"+ rs1.getString("period") + "\t\t"+ rs1.getString("stud_name") + "\t\t\t"+ rs1.getString("stud_rollno") +"\t\t"+ rs1.getString("status"));
        }
        System.out.println("\n");
        con.commit();
        con.close();
        AdminPortal();
        }        
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            AdminPortal();
        }
    }
    
    public static void ReturnBook()
    {
        try
        {        
        Scanner sc4 = new Scanner(System.in);
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Librarydb");
        Statement st = con.createStatement();
        
        System.out.println("Returning book:");
        System.out.println("----------------------------------------------------");
        System.out.println("Enter Issue ID given to student:");
        issueid = sc4.nextInt();
        
        System.out.println("Enter Return Date(DD-MM-YYYY):");
        issuereturn = sc4.next();
                
        String rb = "update issued set return_date='"+issuereturn+"',status='returned' where iid="+issueid+"";
        st.executeUpdate(rb);
        System.out.println("\n"+"~~~~~~~~~~~~~~~~~~~~~   Book returned successfully !!!    ~~~~~~~~~~~~~~~~");
        con.close();
        AdminPortal();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            AdminPortal();
        }     
    }
    public static void DefaultList()
    {
        try
        {            
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Librarydb");
        Statement st = con.createStatement();
        
        System.out.println("Details of all the defaulters:");        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        String upd1 = "select * from issued where status = 'issued'";
        ResultSet rs1 = st.executeQuery(upd1);
        ResultSetMetaData rsmd1 = rs1.getMetaData();
 
        System.out.println(rsmd1.getColumnName(1) +"\t\t"+ rsmd1.getColumnName(2) +"\t\t"+ rsmd1.getColumnName(3) +"\t\t"+ rsmd1.getColumnName(4) +"\t\t"+ rsmd1.getColumnName(5) +"\t\t"+ rsmd1.getColumnName(6) +"\t\t"+ rsmd1.getColumnName(7) +"\t\t"+ rsmd1.getColumnName(8));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    
        while(rs1.next())
        {
            System.out.println(rs1.getString("iid") + "\t\t"+ rs1.getString("bid") + "\t\t"+ rs1.getString("issued_date") +"\t\t\t"+ rs1.getString("return_date")+ "\t\t\t"+ rs1.getString("period") + "\t\t"+ rs1.getString("stud_name") + "\t\t\t"+ rs1.getString("stud_rollno") + "\t\t\t"+ rs1.getString("status"));
        }
        System.out.println("\n");
        con.commit();
        con.close();
        AdminPortal();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            AdminPortal();
        }     
    }
}