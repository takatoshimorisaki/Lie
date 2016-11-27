package mori.Lie.factor;

import static mori.Lie.Node.*;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;

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
			
			if(oneNode.mPower > 0
			&& anoNode.mPower > 0){

				ans.mNodeType = MONO_NODE;
			
				ans.mToken = new String(oneNode.mToken);
				
				if(oneNode.mPower < anoNode.mPower){
					
					ans.mPower = oneNode.mPower;
					
				}else{
					
					ans.mPower = anoNode.mPower;
				}
			}else
			if(oneNode.mPower < 0
			&& anoNode.mPower < 0){

				ans.mNodeType = MONO_NODE;

				ans.mToken = new String(oneNode.mToken);

				if(oneNode.mPower < anoNode.mPower){

					ans.mPower = anoNode.mPower;
					
				}else{

					ans.mPower = oneNode.mPower;
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
