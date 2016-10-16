package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Power {

	private static Multiplier mMultiplier = new Multiplier();

	private static Factory mFactory = new Factory();
	
	public Node mExe(Node arg, int aPower)throws Exception{
		Node ans = null;
		
		if(aPower == 0){
			ans = new Node();
			
			ans.mNodeType = NUMBER_NODE;
			
			ans.mCoef = 1.0;
			
			return ans;
		}else
		if(aPower < 0){
			throw new Exception();
		}else
		{
			int power = aPower;
			
			ans = mFactory.mExe(arg);
			
			power--;
			
			while(power > 0){
				
				ans = mMultiplier.mExe(ans, arg);
				
				power--;
			}
			
			return ans;
		}
	}
}
