public class Passenger
{
    static int id=1;//create id for every Passenger
    String name;
    int age;
    String berthPreference;//L Or U or M
    int passengerId; //created automattically
    String alloted;// alloted Type(L,U,M,RAC,WL)
    int number;//seat Number
    
    public Passenger(String name,int age,String berthPreference){
        this.name=name;
        this.age=age;
        this.berthPreference=berthPreference;
        this.passengerId=id++; // whenever the Passenger enter the details its id incremented
        alloted="";
        number=-1;
    }
    
}