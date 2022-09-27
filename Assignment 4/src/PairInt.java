public class PairInt {
	private int x;
	private int y;

	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int get_X() {
		return this.x;
	}
	
	public int get_Y() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		PairInt test = (PairInt) obj;
		
		if(test.get_X() == this.x && test.get_Y() == this.y) 
		{
			return true;
		}
		
		return false;
	}
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	public PairInt copy() {	
		return new PairInt(this.x, this.y);
	}
}
