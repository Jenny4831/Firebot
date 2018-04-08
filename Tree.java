/**
 * info1103 - assignment 3
 * <Naimen Zhen Liang>
 * <nzhe4831>
 */

public class Tree implements Cloneable{
	
	private int height;
	private int fireLev;
	private boolean onFire;	
	private boolean isBurnt;
	
	
	public Tree(){
		
		this.height = 0;
		this.fireLev = 0;
		this.onFire = false;
		this.isBurnt = false;

	}
	
	public Tree(int initialHeight) {	
	    this.height = initialHeight;	
		this.fireLev = 0;
		this.onFire = false;
		this.isBurnt = false;
	
	}
	
	//*******    Setters ******
	
	public void setHeight(int newHeight){
        height = newHeight;
	 }
	public void setFireLev(int newFire){
		fireLev += newFire;
	}
	
	//******** Getters  *****
	public int getHeight(){
		return this.height;
	}
	public int getFireLev(){
		if(fireLev > 9){
			this.fireLev = 9;
		    if(this.isBurnt){
			    fireLev = 0;
		    }
		}
		return this.fireLev;
	}
	
	public void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}

	//  ****** booleans *****
	public boolean isOnFire(){
		if (fireLev != 0 && height != 0 ){
			onFire = true;
		}else{
			onFire = false;
		}
		return onFire;
	}
	
	public boolean isBurnt(){
		if(fireLev == 9 && height == 0){
			isBurnt = true;
		}
		return isBurnt;
	}
	
	//methods
	
	public void startFire(){
		if(this.height != 0){
			fireLev = 1;
		}
	}
	public void extinFire(){
			this.fireLev = 0;
	}

	public void burn(){
		if (this.fireLev != 0 && this.isBurnt()==false){
			fireLev = fireLev + 1;
			if(fireLev > 9){
				height = height - 1;
				this.fireLev = 9;
			}
		}
	
	}
	
	
	public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
	}  
	
}



