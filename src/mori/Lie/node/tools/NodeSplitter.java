package mori.Lie.node.tools;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.adder.Holder.mAdder;
import static mori.Lie.node.tools.Holder.mMultiplier;

import mori.Lie.I_Operator;
import mori.Lie.Node;

public class NodeSplitter {

	public Node[] mExe(
			int splitPos, 
			Node arg
	)throws Exception{
		I_Operator operator = null;
		
		Node[] ans = new Node[2];

		if(splitPos < 0
		|| splitPos > arg.mSubNodes.size() - 1){
			
			throw new Exception();
		}else
		if(arg.mSubNodes.size() == 1){
			throw new Exception();
		}

		if(arg.mNodeType == MULTI_NODE){
			
			operator = mMultiplier;
			
		}else
		if(arg.mNodeType == POLY_NODE){
				
			operator = mAdder;
			
		}else{
			throw new Exception();
		}
		
		if(splitPos != 0){
			
			ans[0] = arg.mGetSubNode(0);
			
			ans[0] = mMultiplier.mExe(ans[0], arg.mCoef);
			
			for(int id = 1; id < splitPos; id++){
				
				Node node = arg.mGetSubNode(id);
				
				ans[0] = operator.mExe(ans[0], node);
			}
		}
		
		if(splitPos != (arg.mSubNodes.size()-1)){
			
			ans[1] = arg.mGetSubNode(splitPos + 1);
			
			if(ans[0] == null){
				ans[1] = mMultiplier.mExe(ans[1], arg.mCoef);
			}
			
			for(int id = (splitPos + 2); id < arg.mSubNodes.size(); id++){

				Node node = arg.mGetSubNode(id);
				
				ans[1] = operator.mExe(ans[1], node);
			}
		}
		
		return ans;
	}
}
