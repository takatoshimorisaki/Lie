package mori.Lie.factor;

import static mori.Lie.Node.*;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;
import mori.tools.Rational;

public class IntersectorMonoMono {

	public Node mExe(
			Node oneNode,
			Node anoNode
	) throws Exception{
		Node ans = new Node();
		
		ans.mCoef = mGreatestCommonNumber.mExe(
				oneNode.mCoef, 
				anoNode.mCoef);
		
		if(oneNode.mToken.equals(anoNode.mToken)){
			
			if(oneNode.mPower.mGreater(0)
			&& anoNode.mPower.mGreater(0)){

				ans.mNodeType = MONO_NODE;
			
				ans.mToken = new String(oneNode.mToken);
				
				if(oneNode.mPower.mLesser(anoNode.mPower)){
					
					ans.mPower = new Rational(oneNode.mPower);
					
				}else{
					
					ans.mPower = new Rational(anoNode.mPower);
				}
			}else
			if(oneNode.mPower.mLesser(0)
			&& anoNode.mPower.mLesser(0)){

				ans.mNodeType = MONO_NODE;

				ans.mToken = new String(oneNode.mToken);

				if(oneNode.mPower.mLesser(anoNode.mPower)){

					ans.mPower = new Rational(anoNode.mPower);
					
				}else{

					ans.mPower = new Rational(oneNode.mPower);
				}
			}else{

				ans.mNodeType = NUMBER_NODE;
			}
		}else{
			
			ans.mNodeType = NUMBER_NODE;
		}
		
		return ans;
	}
}
