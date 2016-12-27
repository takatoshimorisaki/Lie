package mori.tools;

import static mori.tools.Holder.mArithmetic;

public class Rational {

	public int mNumerator = 1;
	
	public int mDenominator = 1;
	
	public Rational(){
	}
	
	public Rational(String arg){
	
		this.mNumerator = Integer.parseInt(arg);
	}
	
	public Rational(int arg){
		this.mNumerator = arg;
	}
	
	public Rational(Rational arg){
		
		this.mNumerator = arg.mNumerator;
		
		this.mDenominator = arg.mDenominator;
	}
	
	public void mInit(){
		
		mNumerator = 1;
		
		mDenominator = 1;
	}
	
	public Rational mMinus(){
		Rational ans = new Rational();
		
		ans.mNumerator = - this.mNumerator;
		
		ans.mDenominator = this.mDenominator;
		
		return ans;
	}
	
	public boolean mGreater(int arg){
		
		int det = mNumerator - arg * mDenominator;
		
		if(det > 0){
			return true;
		}else{
			return false;
		}
	}
	public boolean mLesser(int arg){

		int det = mNumerator - arg * mDenominator;
		
		if(det < 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean mGreaterEqual(Rational arg){
		
		int det = mNumerator * arg.mDenominator - arg.mNumerator * mDenominator;

		if(det >= 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean mGreater(Rational arg){
		
		int det = mNumerator * arg.mDenominator - arg.mNumerator * mDenominator;

		if(det > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean mLesserEqual(Rational arg){
		
		int det = mNumerator * arg.mDenominator - arg.mNumerator * mDenominator;

		if(det <= 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean mLesser(Rational arg){
		
		int det = mNumerator * arg.mDenominator - arg.mNumerator * mDenominator;

		if(det < 0){
			return true;
		}else{
			return false;
		}
	}

	public Rational mMultiply(Rational arg){
		Rational ans = new Rational();
		
		ans.mNumerator = this.mNumerator * arg.mNumerator;
		
		ans.mDenominator = this.mDenominator * arg.mDenominator;

		int gcd = mArithmetic.mGcd(
				Math.abs(ans.mNumerator), 
				Math.abs(ans.mDenominator));
		
		ans.mNumerator /= gcd;
		
		ans.mDenominator /= gcd;
		
		return ans;
	}
	
	public Rational mAdd(Rational arg){
		Rational ans = new Rational();
		
		ans.mNumerator = this.mNumerator * arg.mDenominator
				       - arg.mNumerator * this.mDenominator;
		
		ans.mDenominator = this.mDenominator * arg.mDenominator;
		
		int gcd = mArithmetic.mGcd(ans.mNumerator, ans.mDenominator);
		
		ans.mNumerator /= gcd;
		
		ans.mDenominator /= gcd;
		
		return ans;
	}
	
	public Rational mSubtract(Rational arg){
		Rational ans = new Rational();
		
		ans.mNumerator = this.mNumerator * arg.mDenominator
				       - arg.mNumerator * this.mDenominator;
		
		ans.mDenominator = this.mDenominator * arg.mDenominator;
		
		int gcd = mArithmetic.mGcd(ans.mNumerator, ans.mDenominator);
		
		ans.mNumerator /= gcd;
		
		ans.mDenominator /= gcd;
		
		return ans;
	}
	
	public Rational mIncrement(){
		Rational ans = new Rational();
		
		ans.mNumerator = this.mNumerator + this.mDenominator;
		
		ans.mDenominator = this.mDenominator;

		int gcd = mArithmetic.mGcd(ans.mNumerator, ans.mDenominator);
		
		ans.mNumerator /= gcd;
		
		ans.mDenominator /= gcd;
		
		return ans;
	}
	
	public boolean mEquals(int arg){
		
		if(this.mDenominator == 1){
		
			if(this.mNumerator == arg){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean mEquals(Rational arg){
		Rational sub = this.mSubtract(arg);
		
		if(sub.equals(0)){
			return true;
		}else{
			return false;
		}
	}
	
	public String mToString(){
	
		if(this.mDenominator == 1){

			return String.format("%d", this.mNumerator);
			
		}else{
		
			return String.format("%d/%d", this.mNumerator, this.mDenominator);
		}
	}
	
	public static void main(String[] args){
		Rational rt = new Rational();
				
		boolean ls = rt.mLesser(0);
		
		System.out.printf("ls = %b\n",  ls);
		
		ls = rt.mLesser(2);

		System.out.printf("ls = %b\n",  ls);
	}
}
