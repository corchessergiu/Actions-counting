package ASSIGMENT4.DemoProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MonitoredData {
private String start_time;
private String end_time;
private String activity;
public MonitoredData(String start_time, String end_time,String activity)
{
	this.start_time=start_time;
	this.end_time=end_time;
	this.activity=activity;
}
public String getStart_time() {
	return start_time;
}
public void setStart_time(String start_time) {
	this.start_time = start_time;
}
public String getEnd_time() {
	return end_time;
}
public void setEnd_time(String end_time) {
	this.end_time = end_time;
}
public String getActivity() {
	return activity;
}
public void setActivity(String activity) {
	this.activity = activity;
}
public Long modificareData()
{

	   String data1=this.getEnd_time();
	   String data2=this.getStart_time();
	   try {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modificat=formatter.parse(data1);
		Date modificat2=formatter.parse(data2);
		long diferenta= modificat.getTime()-modificat2.getTime();
		long rez=TimeUnit.MINUTES.convert(diferenta, TimeUnit.MILLISECONDS);
		return rez;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
}
public String toString()
{
	return "Data inceput: "+this.start_time+" data final: "+this.end_time+" activitate: "+this.activity+"\n";
}
}
