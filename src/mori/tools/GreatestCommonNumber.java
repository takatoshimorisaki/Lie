package mori.tools;

import static mori.tools.Holder.mArithmetic;

public class GreatestCommonNumber {

	public double mExe(
			double oneValue,
			double anoValue
	)throws Exception{
		
		if(Math.abs(oneValue) > Math.abs(anoValue)){
			
			double div = oneValue / anoValue;
			
			if(mArithmetic.mIsInteger(div)){
				
				return div;
			}else
			if(mArithmetic.mIsInteger(oneValue)
			&& mArithmetic.mIsInteger(anoValue)){
				
				return (double)mArithmetic.mGcd(
						(int)oneValue,
						(int)anoValue);
				
			}else{
				
				return 1.0;
			}
		}else{
			
			return mExe(anoValue, oneValue);
		}
	}
}
