package ASSIGMENT4.DemoProject;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
	PrelucrareDate date=new PrelucrareDate();
	date.readFile();
    try {
		date.ziledistincte();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 	date.activitatidiferite();
	date.activitateperzi();
	date.perioada();
	date.perioadatotala();
	date.duratamaimica();
	}

}
