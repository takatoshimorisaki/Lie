package mori.Lie.node.tools;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mRemover;
import static mori.Lie.node.translator.Holder.mNodeTranslator;
import mori.Lie.Node;

public class Remover {

	public Node[] mExe(
			Node aDestNode,
			Node aLargerNode,
			Node aSmallerNode,
			int aPos
	)throws Exception{
		Node[] splitedNode = new Node[2];
		
		aDestNode.mInit();

		int startPos = aPos;
		int endPos   = aPos + aSmallerNode.mSubNodes.size();
		
		if(aLargerNode.mNodeType == MULTI_NODE
		&& aSmallerNode.mNodeType == MULTI_NODE){

			aDestNode.mCoef = aLargerNode.mCoef / aSmallerNode.mCoef;
						
			for(int id = 0; id < aLargerNode.mSubNodes.size(); id++){
				
				if(id < startPos){
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));
					
					aDestNode.add(destSubNode);

					if(splitedNode[0] == null){
						splitedNode[0] = new Node();
					}
					
					splitedNode[0].add(destSubNode);
				}else
				if(startPos <= id && id < endPos){
					
					Node largerSubNode = aLargerNode.mGetSubNode(id);
					
					Node smallerSubNode = aSmallerNode.mGetSubNode(id - startPos);
					
					Node destSubNode = new Node();
					
					destSubNode.mNodeType = MONO_NODE;
					
					destSubNode.mToken = new String(largerSubNode.mToken);
					
					destSubNode.mPower = largerSubNode.mPower.mSubtract(smallerSubNode.mPower);
					
					if(destSubNode.mPower.mEquals(0) != true){
						
						aDestNode.add(destSubNode);

						if(splitedNode[0] == null){
							splitedNode[0] = new Node();
						}
						
						splitedNode[0].add(destSubNode);
					}
				}else
				if(endPos <= id){
					
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));

					aDestNode.add(destSubNode);
					
					if(splitedNode[1] == null){
						splitedNode[1] = new Node();
					}
					
					splitedNode[1].add(destSubNode);
					
				}else{
					throw new Exception();
				}
			}// for id

			if(splitedNode[0] != null){
				
				splitedNode[0].mCoef = aLargerNode.mCoef / aSmallerNode.mCoef;
				
			}else
			if(splitedNode[1] != null){

				splitedNode[1].mCoef = aLargerNode.mCoef / aSmallerNode.mCoef;
				
			}else{
				throw new Exception();
			}
			
			aDestNode.mNodeType = MULTI_NODE;
			
			mNodeTranslator.mExe(aDestNode, aDestNode);
			
			for(int id = 0; id < splitedNode.length; id++){
				
				if(splitedNode[id] != null){
				
					splitedNode[id].mNodeType = MULTI_NODE;
					
					mNodeTranslator.mExe(splitedNode[id], splitedNode[id]);
				}
			}

		}else{
			throw new Exception();
		}
		
		return splitedNode;
	}
	
	public static void main(String[] args){
		try{
			Remover remover = new Remover();

			Node arg = new Node();
			
			arg.mNodeType = MULTINOMIAL_NODE;
			
			arg.mCoef = -1.0;
			
			Node subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Y";
			
			subNode.mPower.mInit();
			
			arg.add(subNode);
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower.mInit();
			
			arg.add(subNode);
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "X";
			
			subNode.mPower.mInit();
			
			arg.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower.mInit();
			
			arg.add(subNode);
			
			Node leftNode = new Node();
			
			leftNode.mNodeType = MULTINOMIAL_NODE;
			
			leftNode.mCoef = 1.0;

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower.mInit();
			
			leftNode.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "X";
			
			subNode.mPower.mInit();
			
			leftNode.add(subNode);
			
			Node removedNode = new Node();
			
			Node[] splitedNode = mRemover.mExe(removedNode, arg, leftNode, 1);
			
			out.printf("arg            %s\n", arg.toString());
			out.printf("leftNode       %s\n", leftNode.toString());
			out.printf("removedNode    %s\n", removedNode.toString());
			out.printf("splitedNode[0] %s\n", splitedNode[0].toString());
			out.printf("splitedNode[1] %s\n", splitedNode[1].toString());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
