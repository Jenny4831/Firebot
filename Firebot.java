/**
 * info1103 - assignment 3
 * <Naimen Zhen Liang>
 * <nzhe4831>
 */
import java.util.*;
import java.util.Scanner;

public class Firebot {
	
	static Integer numofSeed;
	static Integer landWidth;
	static Integer landHeight;

    private static Scanner scan;
    private static Simulation sim;
	
	
	public static void initial(String[] args){
		
		if(args.length != 3){
			System.out.printf("Invalid command line arguments\n");
			System.out.printf("Usage: java Firebot <seed> <width> <height>\n");
			System.exit(0);
		}
		try{
			numofSeed = Integer.parseInt(args[0]);
			landWidth = Integer.parseInt(args[1]);
			landHeight = Integer.parseInt(args[2]);
		}catch (NumberFormatException e){
			System.out.printf("Invalid input: input must be integers\n");
			System.exit(0);
		}
		
		if(numofSeed < 0 || landWidth < 1 || landHeight < 1){
			System.out.printf("Invalid input: input must be positive non zero integer\n");
			System.exit(0);
		}
	
	}
    
	public static void exitCommand(){
		System.out.printf("bye\n");
		System.exit(0);
	}
	
	public static void helpCommand(){
		System.out.println("BYE");
		System.out.println("HELP\n");
		System.out.println("DATA");
		System.out.println("STATUS\n");
		System.out.println("NEXT <days>");
		System.out.println("SHOW <attribute>\n");
		System.out.println("FIRE <region>");
		System.out.println("WIND <direction>");
		System.out.println("EXTINGUISH <region>");
	}
	
	public static void statusCommand(){
		System.out.println("Day: " + sim.getDay());
		System.out.println("Wind: " + sim.getWind());	
	}
	
	public static void dataCommand(){
		System.out.print("Damage: ");
		sim.showDamage();

		System.out.print("Pollution: " );
		sim.showPollution();
	}
	public static void nextCommand(String in){
		
		int daysAdd = 0;
		if(in.length() == 4 && in.equals("next")){
			sim.setDay(1);
		    sim.nextDay();
			sim.calculatePollu();
			
		}else{
			in = in.replaceAll("[^\\d.]", "");
			try{
				daysAdd = Integer.parseInt(in);
			}catch(NumberFormatException e){
			    System.out.printf("Invalid input\n");
			    return;
			}
			sim.setDay(daysAdd);
			for(int i = 0; i < daysAdd; i++){
				sim.nextDay();
				sim.calculatePollu();
			}
		}
		System.out.println("Day: " + sim.getDay());
		System.out.println("Wind: "+ sim.getWind());
		
	}
	public static void showCommand(String in){
			
		if (in.equals("height")){
		    sim.showHeight();
		}else if(in.equals("fire")){
			sim.showFire();
		}else{
		    System.out.printf("Invalid command\n");
			return;
		}
		
	}
	public static void fireCommand(String in){
		if (in.equals("fire all")){
			sim.setFireAll();
		}
		String[] args = in.split(" ");
		
		int x = 0;
		int y = 0;
		int xArea = 0;
		int yArea = 0;
		if(args.length == 5){
			try{
			
				x = Integer.parseInt(args[1]);				
				y = Integer.parseInt(args[2]);
				xArea = Integer.parseInt(args[3]);
				yArea = Integer.parseInt(args[4]);
				if(x < 0 || x >= landWidth || y < 0 || y >= landHeight){
					System.out.printf("Invalid command\n");
					return;
				}
			}catch(NumberFormatException e){
				System.out.printf("Invalid command\n");
				return;
			}
			sim.setSomeFire(x,y,xArea,yArea);
		}else if (args.length == 3){
			try{
				x = Integer.parseInt(args[1]);
				y = Integer.parseInt(args[2]);
				if(x < 0 || x >= landWidth || y < 0 || y >= landHeight){
					System.out.printf("Invalid command\n");
					return;
				}
				
			}catch(NumberFormatException e){
				System.out.printf("Invalid command\n");
				return;
			}
			sim.setaFire(x, y);
		}
		
	}
	public static void windCommand(String in){
		in = in.substring(4).trim();
		System.out.println("Set wind to " + in);
		sim.setWind(in);
	}
	
	public static void extinCommand(String in){
		if (in.equals("extinguish all")){
			sim.extinAllFire();
		}
		in = in.replaceAll("[^\\d.]", "");
		int x = 0;
		int y = 0;
		int xArea = 0;
		int yArea = 0;
		if (in.length() == 4){
			try{
				x = Character.getNumericValue(in.charAt(0));
			    y = Character.getNumericValue(in.charAt(1));
				xArea = Character.getNumericValue(in.charAt(2));
				yArea = Character.getNumericValue(in.charAt(3));
				if(x >= landWidth || y >= landHeight){
					System.out.printf("Invalid command\n");
					return;
				}
			}catch(NumberFormatException e){
				System.out.printf("Invalid command\n");
				return;
			}
			sim.extinSomeFire(x,y,xArea,yArea);
			
		}else if (in.length() == 2){
			try{
				x = Character.getNumericValue(in.charAt(0));
			    y = Character.getNumericValue(in.charAt(1));
				if(x >= landWidth || y >= landHeight){
					System.out.printf("Invalid command\n");
					return;
				}
				
			}catch(NumberFormatException e){
				System.out.printf("Invalid command\n");
				return;
			}
			sim.extinAFire(x, y);
		}
		
		
		
	}
	
	public static void proceedCommand(){
		
		System.out.println("Day: 1");
		System.out.println("Wind: none\n");
		
		System.out.printf("> ");
		scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String input = scan.nextLine();
			String order = input.split(" ")[0].toLowerCase();
			switch (order){
					case"":
					    break;
					case"bye":
					    exitCommand();
					    return;
					case"help":
					    helpCommand();
					    break;
					case"status":
					    statusCommand();
					    break;
					case"data":
						dataCommand();
					    break;
					case"next":
					    nextCommand(input);
					    break;
					case"show":
					    String line = input.split(" ")[1].toLowerCase();
					    showCommand(line);
					    break;
					case"fire":
					    if(input.length() == 4){
							System.out.printf("Invalid command\n");
							break;
						}
					    fireCommand(input);
					    break;
					case"wind":
					    windCommand(input);
					    break;
					case"extinguish":
					    if(input.length() == 10){
							System.out.printf("Invalid command\n");
							break;
						}
					    extinCommand(input);
					    break;
					default:
					    System.out.printf("Invalid command\n");
			}
			System.out.printf("\n> ");
		}
	}
	
	public static void main(String[] args) {
		initial(args);
		sim = new Simulation(numofSeed,landWidth,landHeight);
		proceedCommand();
		
	}
}



