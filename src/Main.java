import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int readInt(Scanner sc,int lower, int upper){
        int choice;
        while (true){
            System.out.print("your choice");
            if(sc.hasNextInt()){
                choice=sc.nextInt();
                if (choice<lower | choice>upper){
                    System.out.println("error it has to be between: "+lower +" and "+upper);

                }else
                    return choice;
            }else{
                sc.next();
                System.out.println("error you have to put an integer!!!");
            }
        }
    }

    public static void saveFile(String filename,ArrayList<User> users){
        File f =new File(filename);
        try(ObjectOutputStream os=new ObjectOutputStream(new BufferedOutputStream (new FileOutputStream(f)))){
            for(int i=0; i<users.size(); i++)
                os.writeObject(users.get(i));
        }catch (IOException e){
            System.err.println(e);
        }
    }


    public static void main(String[] args){
        ArrayList<User > users= new ArrayList<>();

        final String filename="users.bin";

        loadFile(filename,users);

        try (Scanner sc= new Scanner(System.in)){

        boolean exit=false;

        while(!exit){
            //read
            System.out.println("Menu");
            System.out.println("1-Insert User");
            System.out.println("2-Delete user");
            System.out.println("3- Print all users");
            System.out.println("4-Exit");
            int choice=readInt(sc,1,4);
            //scanner

            switch(choice){
                case 1:

                    sc.nextLine();
                    System.out.println("insert new user");
                    System.out.print("insert fullname ");
                    String fullname=sc.nextLine();
                    System.out.print("give username ");
                    String username=sc.next();
                    System.out.print("give password ");
                    String password=sc.next();
                    System.out.println("give role: 1 for user, 2 for admin");
                    int role = readInt(sc,1,2);
                    users.add(new User(fullname,username,password,role));

                    System.out.println("User added!!!!");
                    break;

                case 2:
                    sc.nextLine();
                    System.out.println("Delete user");
                    System.out.println("give full name you want to delete: ");
                    String name=sc.nextLine();
                    boolean found = false;
                    for (int i=0; i< users.size(); i++){
                        if (users.get(i).getFullname().equals(name)){
                            found = true;
                            users.remove(i);
                            System.out.println("the user got deleted!!!! ");
                            break;

                        }
                    }
                    if (!found)
                        System.out.println("user doesn't exist! ");
                        break;
                case 3:
                    System.out.println("the current users are: "+users.size());
                    if (users.size()!=0){
                        for (int i=0; i<users.size(); i++)
                            System.out.println(users.get(i));
                        break;}
                    else {
                        System.out.println("there are no users nor admins!!!");
                        break;}
                case 4:
                    exit=true;
                    //save
                    saveFile(filename,users);
            }

        }

    }}
        public static void loadFile(String filename,ArrayList<User> users){
            File f =new File(filename);

            try(ObjectInputStream os=new ObjectInputStream(new BufferedInputStream (new FileInputStream(f)))){

                while(true){
                    users.add((User) os.readObject());


                }

            }catch (IOException |ClassNotFoundException e){

            }
    }

}
