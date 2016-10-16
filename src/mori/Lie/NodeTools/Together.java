package mori.Lie.NodeTools;

import static java.lang.System.out;
import static mori.Lie.Node.*;

import mori.Lie.Node;

public class Together extends mori.Lie.Lie{

	private static Factory mFactory = new Factory();
	
	public boolean mExe(
			Node aDestNode,
			Node arg)throws Exception{

		boolean togethered = false;
		
		if(arg.mNodeType == NULL_NODE
		|| arg.mNodeType == NUMBER_NODE
		|| arg.mNodeType == MONOMIAL_NODE){
			
			mFactory.mCopy(aDestNode, arg);
			
		}else
		if(arg.mNodeType == MULTINOMIAL_NODE){

			mFactory.mCopy(aDestNode, arg);
			
			boolean arranged = false;
			
			do{
				arranged = false;

				for(int oneCnt = 0; oneCnt < (aDestNode.mSubNodes.size() - 1); oneCnt++){
					
					Node oneNode = aDestNode.mGetSubNode(oneCnt);
					
					Node anoNode = aDestNode.mGetSubNode(oneCnt + 1);
					
					if(oneNode.mToken.equals(anoNode.mToken)){
						
						oneNode.mPower++;
						
						aDestNode.mSubNodes.remove(oneCnt+1);
						
						arranged = true;
						
						togethered = true;
					}
				}
				
			}while(arranged);
			
			if(aDestNode.mSubNodes.size() == 1){
				
				Node node = arg.mGetSubNode(0);

				aDestNode.mToken = new String(node.mToken);
				
				aDestNode.mNodeType = MONOMIAL_NODE;
								
				aDestNode.mPower = node.mPower;
				
				aDestNode.mSubNodes = null;
			}
			
		}else
		if(arg.mNodeType == POLYNOMIAL_NODE
		|| arg.mNodeType == EQU_NODE
		|| arg.mNodeType == BRACKET_NODE){

			mFactory.mCopy(aDestNode, arg);

			for(int oneCnt = 0; oneCnt < aDestNode.mSubNodes.size(); oneCnt++){

				Node oneNode = aDestNode.mGetSubNode(oneCnt);
							
				Node expNode = new Node();
				
				togethered = mExe(expNode, oneNode);
				
				expNode.mSet(oneCnt, expNode);
			}
			
		}else{
		
			throw new Exception(arg.toString());
		}
		
		return togethered;
	}
}
