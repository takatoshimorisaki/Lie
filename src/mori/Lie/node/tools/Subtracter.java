package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mBracketExpander;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mSignReverser;

import mori.Lie.Node;

public class Subtracter{

	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aAnoNode);
		
		return ans;
	}
	
	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		if(aOneNode.mNodeType == NULL_NODE){
			
			throw new Exception();
		}
		
		if(aOneNode.mNodeType == NULL_NODE){
		
			throw new Exception();
		}

		aDestNode.mInit();
		
		if(aOneNode.mNodeType == NUMBER_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
		
				aDestNode.mNodeType = NUMBER_NODE;
						
				aDestNode.mCoef = aOneNode.mCoef - aAnoNode.mCoef;
				
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE
			|| aAnoNode.mNodeType == MULTINOMIAL_NODE){

				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				Node node1 = mFactory.mExe(aOneNode);
						
				aDestNode.add( node1 );
				
				Node node2 = mFactory.mExe(aAnoNode);
				
				node2.mCoef *= -1.0;
				
				aDestNode.add( node2 );
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE){

				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				Node nodeOne = mFactory.mExe( aOneNode );
				
				aDestNode.add(nodeOne);
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node nodeAno = aAnoNode.mGetSubNode(cnt);
					
					nodeAno = mFactory.mExe(nodeAno);
					
					aDestNode.add(nodeAno);
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){
				
				Node node = mBracketExpander.mExe(aAnoNode);
				
				mExe(aDestNode, aOneNode, node);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){
				
				Node leftNode = mExe(aOneNode, aAnoNode.mGetSubNode(0) );
				
				Node rightNode = mExe(aOneNode, aAnoNode.mGetSubNode(1) );
				
				aDestNode.mNodeType = EQU_NODE;
				
				aDestNode.add(leftNode);
				
				aDestNode.add(rightNode);
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == MONOMIAL_NODE
		|| aOneNode.mNodeType == MULTINOMIAL_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONOMIAL_NODE
			|| aAnoNode.mNodeType == MULTINOMIAL_NODE){
				
				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				Node node1 = mFactory.mExe(aOneNode);
						
				aDestNode.add( node1 );
				
				Node node2 = mFactory.mExe(aAnoNode);
				
				node2.mCoef *= -1.0;
				
				aDestNode.add( node2 );
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE){

				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				Node node1 = mFactory.mExe(aOneNode);
						
				aDestNode.add( node1 );
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node2 = mFactory.mExe(aAnoNode.mGetSubNode(cnt));
					
					mSignReverser.mExe(node2);
					
					aDestNode.add(node2);
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){
				
				Node node = mBracketExpander.mExe(aAnoNode);
				
				mExe(aDestNode, aOneNode, node);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){
				
				Node leftNode = mExe(aOneNode, aAnoNode.mGetSubNode(0) );
				
				Node rightNode = mExe(aOneNode, aAnoNode.mGetSubNode(1) );
				
				aDestNode.mNodeType = EQU_NODE;
				
				aDestNode.add(leftNode);
				
				aDestNode.add(rightNode);
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == POLYNOMIAL_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONOMIAL_NODE
			|| aAnoNode.mNodeType == MULTINOMIAL_NODE){
			
				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node1 = aOneNode.mGetSubNode(cnt);
					
					aDestNode.add(mFactory.mExe(node1));
				}
				
				Node node2 = mFactory.mExe(aAnoNode);
				
				mSignReverser.mExe(node2);
				
				aDestNode.add(node2);
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE){

				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node1 = aOneNode.mGetSubNode(cnt);
					
					aDestNode.add(mFactory.mExe(node1));
				}

				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node2 = aAnoNode.mGetSubNode(cnt);
					
					node2 = mFactory.mExe(node2);
					
					mSignReverser.mExe(node2);
					
					aDestNode.add(node2);
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){

				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node1 = aOneNode.mGetSubNode(cnt);
					
					aDestNode.add(mFactory.mExe(node1));
				}
				
				Node anoNode = mBracketExpander.mExe(aAnoNode);

				for(int cnt = 0; cnt < anoNode.mSubNodes.size(); cnt++){
					
					Node node2 = anoNode.mGetSubNode(cnt);
					
					node2 = mFactory.mExe(node2);
					
					mSignReverser.mExe(node2);
					
					aDestNode.add(node2);
				}
			}else
			if(aAnoNode.mNodeType == EQU_NODE){
				
				Node leftNode = aAnoNode.mGetSubNode(0);
				
				Node rightNode = aAnoNode.mGetSubNode(1);
				
				leftNode = mExe(aOneNode, leftNode);
				
				rightNode = mExe(aOneNode, rightNode);
				
				aDestNode.mNodeType = EQU_NODE;
				
				aDestNode.add(leftNode);
				
				aDestNode.add(rightNode);
			}
		}else
		if(aOneNode.mNodeType == BRACKET_NODE){
			
			Node oneNode = mBracketExpander.mExe(aOneNode);
			
			mExe(aDestNode, oneNode, aAnoNode);
		}else
		if(aOneNode.mNodeType == EQU_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONOMIAL_NODE
			|| aAnoNode.mNodeType == MULTINOMIAL_NODE
			|| aAnoNode.mNodeType == POLYNOMIAL_NODE){
				
				Node leftNode = aOneNode.mGetSubNode(0);
				Node rightNode = aOneNode.mGetSubNode(1);
				
				aDestNode.mNodeType = EQU_NODE;
				
				Node dest0 = mExe(leftNode, aAnoNode);
				Node dest1 = mExe(rightNode, aAnoNode);
				
				aDestNode.add(dest0);
				aDestNode.add(dest1);
				
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){

				Node anoNode = mBracketExpander.mExe(aAnoNode);
				
				mExe(aDestNode, aOneNode, anoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				aDestNode.mNodeType = EQU_NODE;
				
				Node oneLeft = aOneNode.mGetSubNode(0);
				Node oneRight = aOneNode.mGetSubNode(1);
				
				Node anoLeft = aAnoNode.mGetSubNode(0);
				Node anoRight = aAnoNode.mGetSubNode(1);
				
				Node destLeft = mExe(oneLeft, anoLeft);
				Node destRight = mExe(oneRight, anoRight);
				
				aDestNode.add(destLeft);
				aDestNode.add(destRight);
			}else{
				throw new Exception();
			}
		}
	}
}

