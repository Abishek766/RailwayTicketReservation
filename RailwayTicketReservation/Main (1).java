/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class Main
{
    static TicketBooker booker;
    public static void bookTicket(Passenger p){
        booker = new TicketBooker();
        if(TicketBooker.availableWaitingList ==0){
            System.out.println("No Tickets Available");
            return;
        }
        //check if preferred berth is Available
        if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0) || 
        (p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0) ||
        (p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0))
        {
            System.out.println("preference Berth Available");
            if(p.berthPreference.equals("L")){
                System.out.println("Lower Berth Given");
                booker.bookTickets(p,(TicketBooker.lowerBerthsPositions.get(0)),"L");
                TicketBooker.lowerBerthsPositions.remove(0);
                TicketBooker.availableLowerBerths--;
            }
            else if(p.berthPreference.equals("M")){
                System.out.println("Middle Berth Given");
                booker.bookTickets(p,(TicketBooker.middleBerthsPositions.get(0)),"M");
                TicketBooker.middleBerthsPositions.remove(0);
                TicketBooker.availableMiddleBerths--;
            }
            else if(p.berthPreference.equals("U")){
                System.out.println("Upper Berth Given");
                booker.bookTickets(p,(TicketBooker.lowerBerthsPositions.get(0)),"U");
                TicketBooker.upperBerthsPositions.remove(0);
                TicketBooker.availableUpperBerths--;
            }
        }
        //preference not available -> book the available berth
        else if(TicketBooker.availableLowerBerths > 0){
            System.out.println("Lower Berth Given");
            booker.bookTickets(p,(TicketBooker.lowerBerthsPositions.get(0)),"L");
            TicketBooker.lowerBerthsPositions.remove(0);
            TicketBooker.availableLowerBerths--;
        }
        else if(TicketBooker.availableMiddleBerths > 0){
            System.out.println("Middle Berth Given");
            booker.bookTickets(p,(TicketBooker.middleBerthsPositions.get(0)),"L");
            TicketBooker.lowerBerthsPositions.remove(0);
            TicketBooker.availableMiddleBerths--;
        }
        else if(TicketBooker.availableUpperBerths > 0){
            System.out.println("Upper Berth Given");
            booker.bookTickets(p,(TicketBooker.middleBerthsPositions.get(0)),"L");
            TicketBooker.upperBerthsPositions.remove(0);
            TicketBooker.availableUpperBerths--;
        }
        //if berth not available -> berth - RAC
        else if(TicketBooker.availableRacTickets > 0){
            System.out.println("RAC Available");
            booker.addToRac(p,(TicketBooker.racPositions.get(0)),"RAC");
        }
        //if RAC not available -> RAC - WL
        else if(TicketBooker.availableWaitingList > 0){
            System.out.println("Added to Waiting List");
            booker.addToWaitingList(p,(TicketBooker.waitingListPositions.get(0)),"WL");
        }
    }
    public static void cancelTicket(int id)
    {
        booker=new TicketBooker();
        if(!booker.passengers.containsKey(id))
        {
            System.out.println("Please enter the correct id");
        }
        else{
            booker.cancelTicket(id);
        }
    }
	public static void main(String[] args) {
	    booker=new TicketBooker();
		Scanner sc = new Scanner(System.in);
	    int choice=0;
		do{
		    System.out.println("1. Book Tickets ");
		    System.out.println("2. Cancel Tickets ");
		    System.out.println("3. Available Tickets ");
		    System.out.println("4. Booked Tickets ");
		    System.out.println("5. Exit ");
		    choice=sc.nextInt();
		    switch(choice){
		        case 1: 
		            System.out.println("Enter Passenger name,age and berth preference(L,M or U)");
		            String name=sc.next();
		            int age=sc.nextInt();
		            String berthPreference=sc.next();
		            Passenger p = new Passenger(name,age,berthPreference);
		            bookTicket(p);
		            break;
		         case 2:
		             System.out.println("Enter passenger id to cancel");
		             int id = sc.nextInt();
		             cancelTicket(id);
		             break;
		         case 3:
		             booker.printAvailable();
		             break;
		         case 4:
		             booker.printPassenger();
		             break;
		         case 5:
		             System.out.println("Thanking You");
		             break;
		         default:
		             System.out.println("Please choose the value from 1 to 5");
		             break;
		         }
		  }while(choice !=5);
	}
}