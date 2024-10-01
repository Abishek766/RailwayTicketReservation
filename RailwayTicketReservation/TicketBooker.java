import java.util.*;
public class TicketBooker{
    
    // consider there are 63 berths(upper,mlower,middle) +(18 rac passengers)
    // 10 waiting list tickets ->21 L,21M,21U,18RAC,10WL
    static int availableLowerBerths=1; //21
    static int availableMiddleBerths=1;//21
    static int availableUpperBerths=1;//21
    static int availableRacTickets=1;//18
    static int availableWaitingList=1;//10
    
    static Queue<Integer> waitingList = new LinkedList();
    static Queue<Integer> racList = new LinkedList();
    static List<Integer> bookedTicketList = new ArrayList();
    
    static List<Integer> lowerBerthsPositions = new ArrayList(Arrays.asList(1));
    static List<Integer> middleBerthsPositions = new ArrayList(Arrays.asList(1));
    static List<Integer> upperBerthsPositions = new ArrayList(Arrays.asList(1));
    static List<Integer> racPositions = new ArrayList(Arrays.asList(1));
    static List<Integer> waitingListPositions = new ArrayList(Arrays.asList(1));
    
    static Map<Integer,Passenger> passengers = new HashMap();//map passenger ids to passengers
    
    //book tickets
    public void bookTickets(Passenger p,int berthInfo,String allotedBerth){
        //assign the seat numbers
        p.number=berthInfo;
        p.alloted=allotedBerth;
        
        // add Passengers
        passengers.put(p.passengerId,p);
        
        // add passenger id to the List of booked tickets
        bookedTicketList.add(p.passengerId);
        System.out.println("----Booked Successfully----");
        
    }
    
    // adding Rac
    public void addToRac(Passenger p,int racInfo,String allotedRac)
    {
        //assign seat Number to RAC
        p.number=racInfo;
        p.alloted=allotedRac;
        //add passenger to the map
        passengers.put(p.passengerId,p);
        //add passenger id to the queue of rac tickets
        racList.add(p.passengerId);
        //Reduce the Rac Seats 
        availableRacTickets--;
        //remove the postion that was alloted to the passenger
        racPositions.remove(0);
        
        System.out.println("------added to RAC Successfully--------");
    }
    
    //adding to Waiting List
    public void addToWaitingList(Passenger p,int waitingListInfo,String allotedWL){
        //assign seat number and type(WL)
        p.number=waitingListInfo;
        p.alloted=allotedWL;
        //add passenger to the map
        passengers.put(p.passengerId,p);
        //add passenger to the queue of waiting List
        waitingList.add(p.passengerId);
        //decrease availability
        availableWaitingList--;
        //remove the waiting List Positions
        waitingListPositions.remove(0);
        
        System.out.println("------_added to Waiting List Successfully--------");
        
    }
    
    //cancel tickets
    public void cancelTicket(int passengerId)
    {
        //remove the passenger from the map
        Passenger p = passengers.get(passengerId);
        passengers.remove(Integer.valueOf(passengerId));
        
        // remove the booked ticket from the List
        bookedTicketList.remove(Integer.valueOf(passengerId));
        
        //re assign the values
        int positionBooked=p.number;
        
        System.out.println("-------Cancelled Successfully-------");
        
        //add the free position to the corresponding type of list(either L,M,U)
        if(p.alloted.equals("L")){
            availableLowerBerths++;
            lowerBerthsPositions.add(positionBooked);
        }
        else if(p.alloted.equals("M")){
            availableMiddleBerths++;
            middleBerthsPositions.add(positionBooked);
        }
        else if(p.alloted.equals("U")){
            availableUpperBerths++;
            upperBerthsPositions.add(positionBooked);
        }
        
        //check if any RACis there
        if(racList.size()>0)
        {
            Passenger passengerFromRAC = passengers.get(racList.poll());
            int positionRac=passengerFromRAC.number;
            racPositions.add(positionRac);
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            availableRacTickets++;
            
            //check if any WL is there
            if(waitingList.size()>0){
                //take the passenger from waitingList and added to racList
                Passenger passengerFromWaitingList=passengers.get(waitingList.poll());
                int positionWL=passengerFromWaitingList.number;
                waitingListPositions.add(positionWL);
                waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));
                
                passengerFromWaitingList.number=racPositions.get(0);
                passengerFromWaitingList.alloted="RAC";
                racPositions.remove(0);
                racList.add(passengerFromWaitingList.passengerId);
                
                availableWaitingList++;
                availableRacTickets--;
            }
            
            // now we have a passenger from rac to whom we can book a ticket
            //so book the cancellled ticket to the RAC passenger
            Main.bookTicket(passengerFromRAC);
        }
    }
    
    //print all available Seats
    public void printAvailable()
    {
        System.out.println("Available Lower Berths " + availableLowerBerths);
        System.out.println("Available Middle Berths " + availableMiddleBerths);
        System.out.println("Available Upper Berths " + availableUpperBerths);
        System.out.println("Available RAC " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("--------------------------");
    }
    
    public void printPassenger()
    {
        if(passengers.size()==0){
            System.out.println("There is no Data of Passengers");
            return;
        }
        for(Passenger p : passengers.values()){
            System.out.println("PASSENGER ID "+p.passengerId);
            System.out.println(" Name "+p.name);
            System.out.println(" Age "+p.age);
            System.out.println(" Status "+p.number + p.alloted);
            System.out.println("-------------------------------");
            
        }
    }
    
}