package mori.tools;

public class Arithmetic {

	public final static double THRESHOLD = 1.0E-07;
	
	public boolean mIsInteger(double arg){
		int intValue = (int)arg;
		
		double dblValue = (double)intValue;
		
		double diff = arg - dblValue;
		
		if(Math.abs(diff) < THRESHOLD){
			
			return true;
			
		}else{
			
			return false;
		}
	}
	
	public int mGcd(int a, int b){
		
		if(a == b){
			
			return a;
			
		}else
		if(a < b){
			
			return mGcd(b, a);
			
		}else
		if(b == 0){
			
			return a;
			
		}else{
			
			double div = (double)a / (double)b;
			
			int q = (int)div;
			
			int r = a - b * q;
			
			return mGcd(b, r);
		}
	}
	
	public static void main(String[] args){
		Arithmetic at = new Arithmetic();
		
		int ans = at.mGcd(48,  36);
		
		System.out.printf("ans %d\n", ans);
	}
}
