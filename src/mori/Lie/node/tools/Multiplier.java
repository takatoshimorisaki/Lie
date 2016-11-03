package mori.Lie.node.tools;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;

import mori.Lie.I_Operator;
import mori.Lie.Node;

public class Multiplier implements I_Operator{
	
	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aAnoNode);
		
		return ans;
	}

	public Node mExe(
		Node aOneNode,
		double aCoef
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aCoef);
		
		return ans;
	}

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		double aCoef
	)throws Exception{
		
		if(aOneNode.mNodeType == POLY_NODE){
			
			aDestNode.mNodeType = POLY_NODE;
			
			aDestNode.mCoef = 1.0;
			
			aDestNode.mToken = null;
			
			aDestNode.mPower = 1;
			
			int size = aOneNode.mSubNodes.size();
			
			for(int cnt = 0; cnt < size; cnt++){
				
				Node subNode = mFactory.mExe(aOneNode.mGetSubNode(cnt));
				
				subNode.mCoef *= aCoef;
				
				aDestNode.add(subNode);
			}
		}else{
			throw new Exception("not implemented");
		}
	}
	
	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		if(aOneNode.mNodeType == NULL_NODE){
			
			throw new Exception("aOneNode");
			
		}
	
		if(aAnoNode.mNodeType == NULL_NODE){
			
			throw new Exception("aAnoNode");
			
		}
		
		aDestNode.mInit();
		
		if(aOneNode.mNodeType == NUMBER_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				aDestNode.mNodeType = NUMBER_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE){
				
				aDestNode.mNodeType = MONOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				aDestNode.mToken = new String(aAnoNode.mToken);
				
				aDestNode.mPower = aAnoNode.mPower;
				
				if(aDestNode.mToken == null){
					throw new Exception(aAnoNode.toString());
				}
				
			}else
			if(aAnoNode.mNodeType == MULTINOMIAL_NODE){
				
				aDestNode.mNodeType = MULTINOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
								
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node = (Node)aAnoNode.mGetSubNode(cnt);
					
					aDestNode.add( mFactory.mExe(node) );
				}
				
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE
			|| aAnoNode.mNodeType == EQU_NODE){
				
				aDestNode.mNodeType = aAnoNode.mNodeType;
				
				if(aAnoNode.mSubNodes.size() == 0){
					throw new Exception(aAnoNode.toString());
				}
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node1 = (Node)aAnoNode.mGetSubNode(cnt);
					
					Node node2 = mExe(aOneNode, node1);
					
					aDestNode.add( node2 );
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){
				
				throw new Exception();
				
			}else{
				String line = String.format("aAnoNode.mNodeType %d", aAnoNode.mNodeType);
				
				throw new Exception(line);
			}
		}else
		if(aOneNode.mNodeType == MONOMIAL_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				aDestNode.mNodeType = MONOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				aDestNode.mToken = new String(aOneNode.mToken);
				
				aDestNode.mPower = aOneNode.mPower;
				
				if(aDestNode.mToken == null || aDestNode.mToken.equals("")){
					throw new Exception(aOneNode.toString());
				}
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE){/* MONO * MONO */
				
				aDestNode.mNodeType = MULTINOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				Node node1 = new Node();
				
				node1.mNodeType = MONOMIAL_NODE;
				
				node1.mToken = new String( aOneNode.mToken );
				
				node1.mPower = aOneNode.mPower;
				
				aDestNode.add( node1 );
				
				Node node2 = new Node();
				
				node2.mNodeType = MONOMIAL_NODE;
				
				node2.mToken = new String( aAnoNode.mToken );
				
				node2.mPower = aAnoNode.mPower;
				
				aDestNode.add( node2 );
				
			}else
			if(aAnoNode.mNodeType == MULTINOMIAL_NODE){/* MONO * MULTI */
				
				aDestNode.mNodeType = MULTINOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				Node node1 = new Node();
				
				node1.mNodeType = MONOMIAL_NODE;
				
				node1.mToken = new String( aOneNode.mToken );
				
				node1.mPower = aOneNode.mPower;
				
				aDestNode.add(node1);
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node2 = (Node)aAnoNode.mGetSubNode(cnt);
					
					if(Math.abs(node2.mCoef - 1.0) > DOUBLE_THRESHOLD){
						throw new Exception(node2.toString());
					}
					
					aDestNode.add( mFactory.mExe(node2) );
				}
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE
			|| aAnoNode.mNodeType == EQU_NODE){
				
				aDestNode.mNodeType = aAnoNode.mNodeType;
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node1 = (Node)aAnoNode.mGetSubNode(cnt);
					
					Node node2 = mExe(aOneNode, node1);
					
					aDestNode.add( node2 );
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){// MONOMIAL * BRACKET
				
				throw new Exception();
				
			}else{
				String line = String.format("aAnoNode.mNodeType %d", aAnoNode.mNodeType);
				
				throw new Exception(line);
			}
		}else
		if(aOneNode.mNodeType == MULTINOMIAL_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){/* MULTI * NUMBER */
				
				aDestNode.mNodeType = MULTINOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node =(Node)aOneNode.mGetSubNode(cnt);

					if(Math.abs(node.mCoef - 1.0) > DOUBLE_THRESHOLD){
						throw new Exception(node.toString());
					}
					
					aDestNode.add( mFactory.mExe(node) );
				}
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE){
				
				aDestNode.mNodeType = MULTINOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node =(Node)aOneNode.mGetSubNode(cnt);

					if(Math.abs(node.mCoef - 1.0) > DOUBLE_THRESHOLD){
						throw new Exception(node.toString());
					}
					
					aDestNode.add( mFactory.mExe(node) );
				}
				
				Node node2 = new Node();
				
				node2.mNodeType = MONOMIAL_NODE;
				
				node2.mToken = new String( aAnoNode.mToken );
				
				aDestNode.add( node2 );
			}else
			if(aAnoNode.mNodeType == MULTINOMIAL_NODE){
				
				aDestNode.mNodeType = MULTINOMIAL_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node =(Node)aOneNode.mGetSubNode(cnt);
					
					aDestNode.add( mFactory.mExe(node) );
				}
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node =(Node)aAnoNode.mGetSubNode(cnt);
					
					aDestNode.add( mFactory.mExe(node) );
				}
											
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE
			|| aAnoNode.mNodeType == EQU_NODE){
				
				aDestNode.mNodeType = aAnoNode.mNodeType;
				
				for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
					
					Node node1 = (Node)aAnoNode.mGetSubNode(cnt);
					
					Node node2 = mExe(aOneNode, node1);
					
					aDestNode.add( node2 );
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){// MULTI * BRACKET
				
				throw new Exception();
				
			}else{
				String line = String.format("aAnoNode.mNodeType %d", aAnoNode.mNodeType);
				
				throw new Exception(line);
			}
		}else
		if(aOneNode.mNodeType == POLYNOMIAL_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node = mFactory.mExe( aOneNode.mGetSubNode(cnt) );
					
					node.mCoef *= aAnoNode.mCoef;
					
					aDestNode.add( node );
				}
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE
			|| aAnoNode.mNodeType == MULTINOMIAL_NODE){
				
				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node1 = aOneNode.mGetSubNode(cnt);
					
					Node node2 = mExe(node1, aAnoNode);
					
					aDestNode.add( node2 );
				}
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE){
				
				aDestNode.mNodeType = POLYNOMIAL_NODE;
				
				for(int oct = 0; oct < aOneNode.mSubNodes.size(); oct++){
					
					Node node1 = (Node)aOneNode.mGetSubNode(oct);
					
					for(int act = 0; act < aAnoNode.mSubNodes.size(); act++){
						
						Node node2 = (Node)aAnoNode.mGetSubNode(act);
						
						Node node3 = mExe(node1, node2);
						
						aDestNode.add(node3);
					}
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){
				
				throw new Exception();
				
			}else
			if(aAnoNode.mNodeType == EQU_NODE){
				
				aDestNode.mNodeType = EQU_NODE;
				
				Node leftNode = mExe(aOneNode, aAnoNode.mGetSubNode(0));
				
				Node rightNode = mExe(aOneNode, aAnoNode.mGetSubNode(1));
				
				aDestNode.add(leftNode);
				
				aDestNode.add(rightNode);
			}else{
				
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == BRACKET_NODE){
			
			throw new Exception();
			
		}else
		if(aOneNode.mNodeType == EQU_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONO_NODE
			|| aAnoNode.mNodeType == MULTI_NODE){
				
				aDestNode.mNodeType = EQU_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node1 = aOneNode.mGetSubNode(cnt);
						
					Node node2 = mExe(node1, aAnoNode);
					
					aDestNode.add(node2);
				}
			}else
			if(aAnoNode.mNodeType == POLY_NODE){
				
				aDestNode.mNodeType = EQU_NODE;
								
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node1 = aOneNode.mGetSubNode(cnt);
						
					Node node2 = mExe(node1, aAnoNode);
				
					aDestNode.add(node2);
				}
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){
				
				throw new Exception();
				
			}else
			if(aAnoNode.mNodeType == EQU_NODE){/* EQU * EQU */
				
				aDestNode.mNodeType = EQU_NODE;
				
				for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
					
					Node node = mExe( aOneNode.mGetSubNode(cnt), aAnoNode.mGetSubNode(cnt) );
					
					aDestNode.add(node);
				}
			}else{
				
				throw new Exception();
				
			}
		}else{
			
			throw new Exception();
		}
	}
}

