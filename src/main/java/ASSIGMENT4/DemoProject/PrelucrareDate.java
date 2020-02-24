package ASSIGMENT4.DemoProject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrelucrareDate {
	public ArrayList<MonitoredData> date;
	public Map<String, Long> actiune;
	public Map<String, Long> afisare;
	PrelucrareDate(){
		date=new ArrayList<MonitoredData>();
		actiune=new HashMap<String,Long>();
		afisare=new HashMap<String,Long>();
	}
	
   public void readFile()
   {
	   String name="Date.txt";
	   String s="";
	   try {
		   Stream<String> stream=Files.lines(Paths.get(name));
			stream
			.forEach(x->date.add(new MonitoredData(x.substring(0,19),x.substring(21,40),x.substring(42,x.length()))));
			stream.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   public void ziledistincte() throws Exception
   {
	int contor=0;
	 String start_date="";
	 String end_date="";
	 String com1="";
	 String com2="";
	 int ziend,zistart, lunastart,lunaend;
	  /* for (int i=0;i<date.size();i++)
	   {
		   start_date=date.get(i).getStart_time();
		   end_date=date.get(i).getEnd_time();  
		   com1=start_date.substring(0,10);
		   com2=end_date.substring(0,10);
			 System.out.print(com1+"   "+com2+"\n");
			 if (!(com1.equals(com2)))
				 contor++;*/
	 Long ziledistincte=date.stream()
	  .map(s->(s.getEnd_time().substring(0,10)))
	  .distinct()
	  .count();
	  System.out.println("Numarul de zile distincte este:"+ziledistincte+"\n\n");
   }
   public void activitatidiferite()
   {
	   System.out.println("\nDe cate ori apare fiecare actiune pe intreaga perioada de monotorizare\n");
	   actiune=date.stream()
	   .collect(Collectors.groupingBy(p->p.getActivity(),Collectors.counting()));
	   actiune.forEach((String actiune,Long numar)->{
		   System.out.println("Activitatea:    "+actiune+"apare de :"+numar +" ori");
	   });
	   /*actiune=date.stream()
			    .map(p->p.getActivity())
			   .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
			   actiune.forEach((String actiune,Long numar)->{
				   System.out.println("Activitatea:    "+actiune+"apare de :"+numar +" ori");
			   });*/
	   System.out.println("\n\n");
   }
   public void activitateperzi()
   {
	   System.out.println("\nDe cate ori aparae o actiune intr-o anumita data!\n");
	    actiune=date.stream()
	    .collect(Collectors.groupingBy(p->("Data: "+p.getStart_time().substring(0,10)+" activitatea: "+p.getActivity()),Collectors.counting()));
	    actiune.forEach((String actiune,Long numar)->{
			   System.out.println(actiune+" numar aparitii :"+numar);
		   });   
   }
   public void perioada()
   {
	   System.out.println("\nDurata activitatii pentru fiecare linie:\n");
	 List<Long> a =date.stream()
	  .map(s->(s.modificareData()))
	  .collect(Collectors.toList());
	for (int i=0;i<a.size();i++) {
	System.out.print("Activitatea: "+date.get(i).getActivity()+"    durata: ");
	int nr=Integer.parseInt(a.get(i).toString());
	double ore=(double)nr/60;
	double rez=ore-(int)ore;
	rez=rez*60;
	double secunde=rez-(int)rez;
	secunde*=60;
	System.out.print(" Ore "+(int)ore);
	System.out.print(" minute: "+(int)rez+" secunde:"+(int)secunde+"\n");
	//System.out.println("ore: "+nr/60+" minute: "+nr+" secunde: "+nr/3600);
	}
   }
public void perioadatotala()
{
	System.out.println("\nDurata pentru fiecare activitate\n");
	afisare=date.stream()
	.collect(Collectors.groupingBy(MonitoredData::getActivity,Collectors.summingLong(p->(p.modificareData()))));
    afisare.forEach((String actiune,Long numar)->{
		   System.out.print("Actiunea:"+actiune);
			Long nr=numar;
			double ore=(double)nr/60;
			double rez=ore-(int)ore;
			rez=rez*60;
			double secunde=rez-(int)rez;
			secunde*=60;
			System.out.print("Durata:Ore: "+(int)ore);
			System.out.print(" minute: "+(int)rez+" secunde:"+(int)secunde+"\n");
	   });  
}
public void duratamaimica()
{
	System.out.println("\nActiunile care au in 90% din cazuri timpul mai mic decat 5 minute!\n");
	actiune=date.stream().collect(Collectors.groupingBy(p->p.getActivity(), Collectors.counting()));
	
	afisare=(Map<String, Long>) date.stream().filter(p->p.modificareData()<=5)
			.collect(Collectors.groupingBy(p->p.getActivity(),Collectors.counting()));
	
	List date=afisare.entrySet().stream()
			.filter(a->a.getValue()>=0.9*actiune.get(a.getKey()))
			.map(x->x.getKey())
			.collect(Collectors.toList());
	for (int i=0;i<date.size();i++) {
		 System.out.println(date.get(i).toString());
		}		
}
public ArrayList<MonitoredData> getLista()
   {
	   return this.date;
   }
}
