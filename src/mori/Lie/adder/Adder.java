package mori.Lie.adder;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.NodeTools.Factory;

public class Adder {

	private static Factory mFactory = new Factory();
	
	private static AdderNumPoly mAdderNumPoly = new AdderNumPoly();
	
	private static AdderMonoMono mAdderMonoMono = new AdderMonoMono();
	
	private static AdderMonoPoly mAdderMonoPoly = new AdderMonoPoly();
	
	private static AdderMultiMulti mAdderMultiMulti = new AdderMultiMulti();
	
	private static AdderMultiPoly mAdderMultiPoly = new AdderMultiPoly();
	
	private static AdderPolyPoly mAdderPolyPoly = new AdderPolyPoly();
	
	private static AdderPolyBra mAdderPolyBra = new AdderPolyBra();
	
	private static AdderBraBra mAdderBraBra = new AdderBraBra();
	
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
			
			throw new Exception("aOneNode");
			
		}
	
		if(aAnoNode.mNodeType == NULL_NODE){
			
			throw new Exception("aAnoNode");
			
		}

		aDestNode.mInit();

		if(aOneNode.mNodeType == NUMBER_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				aDestNode.mNodeType = NUMBER_NODE;
				
				aDestNode.mCoef = aOneNode.mCoef + aAnoNode.mCoef;
				
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE
			|| aAnoNode.mNodeType == MULTINOMIAL_NODE
			|| aAnoNode.mNodeType == BRACKET_NODE){	

				aDestNode.mNodeType = POLY_NODE;
				
				aDestNode.add(mFactory.mExe(aOneNode));
				
				aDestNode.add(mFactory.mExe(aAnoNode));
			}else
			if(aAnoNode.mNodeType == POLYNOMIAL_NODE){
				
				mAdderNumPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				mExeNomialEqu(aDestNode, aOneNode, aAnoNode);
				
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == MONOMIAL_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MULTI_NODE
			|| aAnoNode.mNodeType == BRACKET_NODE){

				aDestNode.mNodeType = POLY_NODE;
				
				aDestNode.add(mFactory.mExe(aOneNode));
				
				aDestNode.add(mFactory.mExe(aAnoNode));
			}else
			if(aAnoNode.mNodeType == MONOMIAL_NODE){
				
				mAdderMonoMono.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == POLY_NODE){
				
				mAdderMonoPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				mExeNomialEqu(aDestNode, aOneNode, aAnoNode);
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == MULTI_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONO_NODE
			|| aAnoNode.mNodeType == BRACKET_NODE){

				aDestNode.mNodeType = POLY_NODE;
				
				aDestNode.add(mFactory.mExe(aOneNode));
				
				aDestNode.add(mFactory.mExe(aAnoNode));
			}else
			if(aAnoNode.mNodeType == MULTI_NODE){
				
				mAdderMultiMulti.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == POLY_NODE){
				
				mAdderMultiPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				mExeNomialEqu(aDestNode, aOneNode, aAnoNode);
				
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == POLY_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				mAdderNumPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == MONO_NODE){
				
				mAdderMonoPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == MULTI_NODE){

				mAdderMultiPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == POLY_NODE){
			
				mAdderPolyPoly.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){

				mAdderPolyBra.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				mExeNomialEqu(aDestNode, aOneNode, aAnoNode);
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == BRACKET_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONO_NODE
			|| aAnoNode.mNodeType == MULTI_NODE){

				aDestNode.mNodeType = POLY_NODE;
				
				aDestNode.add(mFactory.mExe(aOneNode));
				
				aDestNode.add(mFactory.mExe(aAnoNode));
				
			}else
			if(aAnoNode.mNodeType == POLY_NODE){
					
				mAdderPolyBra.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == BRACKET_NODE){
				
				mAdderBraBra.mExe(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				mExeNomialEqu(aDestNode, aOneNode, aAnoNode);
			}else{
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == EQU_NODE){

			if(aAnoNode.mNodeType == NUMBER_NODE
			|| aAnoNode.mNodeType == MONO_NODE
			|| aAnoNode.mNodeType == MULTI_NODE
			|| aAnoNode.mNodeType == POLY_NODE
			|| aAnoNode.mNodeType == BRACKET_NODE){

				mExeNomialEqu(aDestNode, aOneNode, aAnoNode);
			}else
			if(aAnoNode.mNodeType == EQU_NODE){

				mExeEquEqu(aDestNode, aOneNode, aAnoNode);
			}else{
				throw new Exception();
			}
		}else{
			throw new Exception();
		}
	}

	private void mExeNomialEqu(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		aDestNode.mNodeType = EQU_NODE;
		
		Node leftNode = null;
		Node rightNode = null;
		
		if(aOneNode.mNodeType == EQU_NODE){

			leftNode = aOneNode.mGetSubNode(0);
			
			rightNode = aOneNode.mGetSubNode(1);
			
			leftNode = mExe(aAnoNode, leftNode);
			
			rightNode = mExe(aAnoNode, rightNode);
		}else{
					
			leftNode = aAnoNode.mGetSubNode(0);
			
			rightNode = aAnoNode.mGetSubNode(1);
			
			leftNode = mExe(aOneNode, leftNode);
			
			rightNode = mExe(aOneNode, rightNode);
		}
		
		aDestNode.add(leftNode);
		
		aDestNode.add(rightNode);
	}

	private void mExeEquEqu(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		aDestNode.mNodeType = EQU_NODE;
	
		Node oneLeft = aOneNode.mGetSubNode(0);
		
		Node oneRight = aOneNode.mGetSubNode(1);
		
		Node anoLeft = aAnoNode.mGetSubNode(0);
		
		Node anoRight = aAnoNode.mGetSubNode(1);
		
		Node left = mExe(oneLeft, anoLeft);
		
		Node right = mExe(oneRight, anoRight);
		
		aDestNode.add(left);
		
		aDestNode.add(right);
	}
}
