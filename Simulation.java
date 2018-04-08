/**
 * info1103 - assignment 3
 * <Naimen Zhen Liang>
 * <nzhe4831>
 */

public class Simulation {
	
   
	private final int seed;
	private final int width;
	private final int height;
	private int day;
	private int pollu;
	private String wind;
	private Tree[][] trees;

	
	
	
	public Simulation(int s,int w, int h){
		this.wind = "none";
		this.day = 1;
		this.width = w;
		this.height = h;
		this.pollu = 0;
		this.seed = s;
		this.trees = new Tree[this.height][this.width];
		this.generateTerrain(this.seed);
		
	}
	
	//********************* Accessors ********
	
    //setters
	public void setWind(String in){
		this.wind = in;
	}
	
	public void setDay(int daysAdd){
		this.day += daysAdd;
	}

	//getters
	public int getDay(){
		return this.day;
	}
	public String getWind(){
		return this.wind;
	}
	public int getPollu(){
		return this.pollu;
	}
	//********************   Methods *****************//
	
	//*****  Methods for data ****//
	
	public void showDamage(){
		int burntNo = 0;
		int numofT = 0;
		for (int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				Tree thisTree = trees[i][j];
				if(thisTree.getHeight() != 0 || thisTree.isBurnt() == true){
					numofT++;//counts number of trees
				}
			}
		}
		for (int w = 0; w < height; w++){
	        for(int q = 0; q < width; q++){
			    Tree thisTree = trees[w][q];
				    if(thisTree.isBurnt() == true){
					    burntNo++;//counts number of burnt trees
					}
		     }
		}
		double dama = 0;
		    if(burntNo != 0){
		        dama = (double)Math.round(10000.0 / numofT * burntNo)/100;		
		        System.out.print(String.format("%.2f", dama) + "%\n");
		    }else{
			    System.out.print("0.00%\n");
		    }
	}
	public void showPollution(){
		System.out.println(pollu);
	}
	
	public void calculatePollu(){
		for (int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				Tree thisTree = trees[i][j];
				if(thisTree.isBurnt()==true){
					continue;
				}
				pollu += thisTree.getFireLev() - thisTree.getHeight();
				}
			}
		if(pollu < 0){
			pollu = 0;
		}
	}

	//*********** Methods for showing simulation *********
	
	public void showHeight(){
		System.out.print("+");
		for(int j = 0; j < ((width * 2) - 1); j++){
			System.out.print("-");
		}
		System.out.printf("+\n");
		for (int i = 0; i < height; i++){
			System.out.print("|");
			for(int k = 0; k < width; k++){
				Tree thisTree = trees[i][k];
				int tall = thisTree.getHeight(); 
				if (k == width - 1){
					if(thisTree.isBurnt()==true){
						System.out.print("x");
						continue;//does not print tree height
					}else if(tall == 0){
					    System.out.print(" ");
					    continue;//does not print tree height
					}
					System.out.print(tall);
				}else{
					if(thisTree.isBurnt()==true){
						System.out.print("x ");
						continue;
					}else if(tall == 0){
					    System.out.print("  ");
					    continue;
					}
				    System.out.print(tall + " ");
				}
			}
			System.out.print("|\n");
		}
		System.out.print("+");
		for(int j = 0; j < ((width * 2) - 1); j++){
			System.out.print("-");
		}
		System.out.printf("+\n");
	}
	
	
	public void showFire(){   
	   System.out.print("+");
		for(int j = 0; j < ((width * 2) - 1); j++){
			System.out.print("-");
		}
		System.out.printf("+\n");	
		for (int i = 0; i < height; i++){
			System.out.print("|");
			for(int k = 0; k < width; k++){
				Tree thisTree = trees[i][k];
				int tall = thisTree.getHeight();
				int intensity = thisTree.getFireLev();            
				if (k == width - 1){
					if(thisTree.isBurnt() == true){
						System.out.print("x");
						continue;
					}else if(tall == 0){
					    System.out.print(" ");
					    continue;
					}else if(intensity != 0){
						System.out.print(intensity);
						continue;
					}
					if(intensity == 0){
				        System.out.print(".");
					}
				}else{
					if(thisTree.isBurnt() == true){
						System.out.print("x ");
						continue;
					}else if(tall == 0){
					    System.out.print("  ");
					    continue;
					}else if(intensity != 0){
						System.out.print(intensity + " ");
						continue;
					}
				    System.out.print(". ");
				}
			}
			System.out.print("|\n");
		}
		System.out.print("+");
		for(int j = 0; j < ((width * 2) - 1); j++){
			System.out.print("-");
		}
		System.out.printf("+\n");
	}
	
	// ************ Methods for fire ***********
	
	public void setaFire(int a, int b){
		Tree thisTree = trees[b][a];
		if(thisTree.getHeight() == 0 || thisTree.isOnFire() == true){
		    System.out.println("No fires were started");
		}else{
			System.out.println("Started a fire");
		}
		thisTree.startFire();
	}
	
	public void setSomeFire(int a, int b, int c, int d){
		int treestoFire = 0;
		int treesonFire = 0;
		for(int i = a; i < a + c; i++){
			for(int j = b; j < b + d; j++){
				Tree thisTree = trees[j][i];
				if(thisTree.getHeight() == 0){
				    treestoFire++;
				}
				if(thisTree.getFireLev() != 0){
					treesonFire++;
				}
				if(thisTree.getHeight()!= 0 || thisTree.isOnFire()){
				    thisTree.startFire();
				}
			}
		}
		if(treestoFire == 0 || treesonFire != 0){
			 System.out.println("No fires were started");
		}else{
			System.out.println("Started a fire");
		}
		
	}
	
	public void setFireAll(){
		int allTreesOnFire = 0;
		int treestoFire = 0;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				Tree thisTree = trees[i][j];
				if(thisTree.isOnFire()){
					allTreesOnFire++;
				}
				if(thisTree.getHeight() != 0){
				    treestoFire++;
				}
	            if(thisTree.getHeight()!= 0 || thisTree.isOnFire()){
					thisTree.startFire();
				}
			}
		}
		if(allTreesOnFire == treestoFire){
			System.out.println("No fires were started");
		}else{
		    System.out.println("Started a fire");
		}
	}
	
	//*********** Methods to Extinguish Fire *****************
	
	public void extinAFire(int a, int b){

		Tree thisTree = trees[b][a];
		if(thisTree.isOnFire()== false || thisTree.isBurnt() == true){
		    System.out.println("No fires to extinguish");
		}else{
			System.out.println("Extinguished fires");
		}
		thisTree.extinFire();
	}
	public void extinSomeFire(int a, int b, int c, int d){
		int treesBurnt = 0;
		int numOfTrees = 0;
		for(int i = a; i < a + c; i++){
			for(int j = b; j < b + d; j++){
				Tree thisTree = trees[j][i];
				if(thisTree.getFireLev() == 0){
				    numOfTrees++;
				}
				if(thisTree.isBurnt() == true){
					treesBurnt++;
				}
				thisTree.extinFire();
			}
		}
		if(numOfTrees == 0 || treesBurnt == numOfTrees){
			 System.out.println("No fires to extinguish");
		}else{
			System.out.println("Extinguished fires");
		}
		
	}
	public void extinAllFire(){
		int cannotExtin = 0;
		int numOfTrees = 0;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				Tree thisTree = trees[i][j];
				if(thisTree.getHeight()!=0){
					numOfTrees++;
				}
				if(thisTree.getFireLev()==0 && thisTree.getHeight()!=0){//trees that not on fire
					cannotExtin++;
				}
				thisTree.extinFire();
			}
		}
		if(cannotExtin == numOfTrees){
			System.out.println("No fires to extinguish");
		}else{
		    System.out.println("Extinguished fires");
		}
	}
	
	//***************** Mehthods for wind **************
	
	public void windDirection(){

		Tree[][] cloneTree;
		switch(wind){
				case"none":
					for(int w = 0; w < width; w++){
						for(int h = 0; h < height; h++){
							if(trees[h][w].isOnFire()){
								trees[h][w].burn();
							}							
						}
					}
				break;
				case"north":
					cloneTree = CopyTrees(trees);
					for(int w = 0; w < width; w++){
						for(int h = 0; h < height; h++){
							if(cloneTree[h][w].isOnFire()){
								trees[h][w].burn();
								if(h-1 >=0 && !cloneTree[h-1][w].isOnFire()){
									trees[h-1][w].startFire();
								}
							}							
						}
					}
					
				break;
				case"south":
					cloneTree = CopyTrees(trees);
					for(int w = 0; w < width; w++){
						for(int h = 0; h < height; h++){
							if(cloneTree[h][w].isOnFire()){
								trees[h][w].burn();
								if(h+1 < height && !cloneTree[h+1][w].isOnFire()){
									trees[h+1][w].startFire();
								}
							}							
						}
					}
				break;
				case"east":
					cloneTree = CopyTrees(trees);
					for(int w = 0; w < width; w++){
						for(int h = 0; h < height; h++){
							if(cloneTree[h][w].isOnFire()){
								trees[h][w].burn();
								if(w+1 < width && !cloneTree[h][w+1].isOnFire()){
									trees[h][w+1].startFire();
								}
							}							
						}
					}
				break;
				case"west":
					cloneTree = CopyTrees(trees);
					for(int w = 0; w < width; w++){
						for(int h = 0; h < height; h++){
							if(cloneTree[h][w].isOnFire()){
								trees[h][w].burn();
								if(w-1 >=0 && !cloneTree[h][w-1].isOnFire()){
									trees[h][w-1].startFire();
								}
							}							
						}
					}
				break;
				case"all":
					cloneTree = CopyTrees(trees);
					for(int w = 0; w < width; w++){
						for(int h = 0; h < height; h++){
							if(cloneTree[h][w].isOnFire()){
								trees[h][w].burn();
								if(w-1 >=0 && !cloneTree[h][w-1].isOnFire()){
									trees[h][w-1].startFire();
								}
								if(h-1 >=0 && !cloneTree[h-1][w].isOnFire()){
									trees[h-1][w].startFire();
								}
								if(w+1 < width && !cloneTree[h][w+1].isOnFire()){
									trees[h][w+1].startFire();
								}
								if(h+1 < height && !cloneTree[h+1][w].isOnFire()){
									trees[h+1][w].startFire();
								}
							}							
						}
					}
				break;
						
			default:
				System.out.println("Invalid command\n");
		}
	}
	
	//************ Methods for next day and spread fire ********
	public void nextDay(){
		windDirection();
	}
	
	//copy of original array for fire spreading in wind method
	public Tree[][] CopyTrees(Tree[][] treeToCopy){
		int h = treeToCopy.length;
		int w = treeToCopy[0].length;
		Tree[][] copy = new Tree[h][w];
		try{		    
		    for (int i = 0; i < h; i++){
			    for(int j = 0; j < w; j++){
				    Tree prevTrees = trees[i][j];
				    copy[i][j] = (Tree)prevTrees.clone();
			    }
		    }
		    return copy;
	    }catch(CloneNotSupportedException c){
	    	return null;
		}  
  
}  

	private void generateTerrain(int seed) {

		// ###################################
		// ### DO NOT MODIFY THIS FUNCTION ###
		// ###################################

		Perlin perlin = new Perlin(seed);
		double scale = 10.0 / Math.min(width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double height = perlin.noise(x * scale, y * scale, 0.5);
				height = Math.max(0, height * 1.7 - 0.7) * 10;
				trees[y][x] = new Tree((int) height);
			}
		}
	}
}



