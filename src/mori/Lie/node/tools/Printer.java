package mori.Lie.node.tools;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;

public class Printer{

	public void mPrintlnNodeType(Node arg)throws Exception{
		
		mPrintNodeType(arg);
		
		out.println();
	}
	public void mPrintNodeType(Node arg)throws Exception{
		
		out.print(this.toNodeType(arg));
	}
	
	public String toNodeType(Node arg)throws Exception{
		String ans = null;
		
		switch(arg.mNodeType){
		case NULL_NODE:
			ans = String.format("NULL_NODE");
			break;
		case NUMBER_NODE:
			ans = String.format("NUMBER_NODE");
			break;
		case MONOMIAL_NODE:
			ans = String.format("MONOMIAL_NODE");
			break;
		case MULTINOMIAL_NODE:
			ans = String.format("MULTINOMIAL_NODE");
			break;
		case POLYNOMIAL_NODE:
			ans = String.format("POLYNOMIAL_NODE");
			break;
		case BRACKET_NODE:
			ans = String.format("BRACKET_NODE");
			break;
		case EQU_NODE:
			ans = String.format("EQU_NODE");
			break;
		case OPE_ADD_NODE:
			ans = String.format(":");
			break;
		case OPE_SUB_NODE:
			ans = String.format("OPE_SUB_NODE");
			break;
		case OPE_MULTI_NODE:
			ans = String.format("OPE_MULTI_NODE");
			break;
		case OPE_DIV_NODE:
			ans = String.format("/");
			break;
		case OPE_POWER_NODE:
			ans = String.format("^");
			break;
		case OPE_PLUS_NODE:
			ans = String.format("+");
			break;
		case OPE_MINUS_NODE:
			ans = String.format("-");
			break;
		case RATIONAL_NODE:
			ans = String.format("RATIONAL_NODE");
			break;
		case BRACKET_START_NODE:
			ans = String.format("[");
			break;
		case BRACKET_END_NODE:
			ans = String.format("]");
			break;
		case COMMA_NODE:
			ans = String.format(",");
			break;
		default:
			String errMsg = String.format("arg.mNodeType %d", arg.mNodeType);
			
			throw new Exception(errMsg);
		}
		
		return ans;
	}
	
	public String mToString(
		Node aNode
	)throws Exception{
		String ans = "";
		
		if(aNode.mNodeType == NULL_NODE){
			
			ans = "null";
			
			if(aNode.mSubNodes != null){
				
				ans += "(";
				
				for(int id = 0; id < aNode.mSubNodes.size(); id++){
				
					Node node = aNode.mGetSubNode(id);
					
					ans += String.format(" %s", node.mToString());
				}
				
				ans += ")";
			}
		}else
		if(aNode.mNodeType == NUMBER_NODE){
			
			ans = String.format("%s", aNode.mCoef);
		}else
		if(aNode.mNodeType == MONOMIAL_NODE){
			
			if(Math.abs(aNode.mCoef - 1.0) < DOUBLE_THRESHOLD){

				ans = String.format("%s", aNode.mToken);
			}else{
				ans = String.format("%s%s", mAdjust(aNode.mCoef), aNode.mToken);
			}

			if(aNode.mPower.mEquals(1) != true){
				
				ans = String.format("%s^(%s)", ans, aNode.mPower.mToString());
				
			}else{
				// nothing to do.
			}
		}else
		if(aNode.mNodeType == MULTINOMIAL_NODE){

			ans = mAdjust(aNode.mCoef);
			
			for(int cnt = 0; cnt < aNode.mSubNodes.size(); cnt++){
				
				if(cnt > 0){
					ans = String.format("%s*", ans);
				}
				
				Node node = (Node)aNode.mSubNodes.elementAt(cnt);
				
				ans = String.format("%s%s", 
						ans, this.mToString(node) );
			}
		}else
		if(aNode.mNodeType == POLYNOMIAL_NODE){
			
			for(int cnt = 0; cnt < aNode.mSubNodes.size(); cnt++){
				
				if(cnt > 0){
					
					ans = String.format("%s:", ans);
				}
				
				Node node = (Node)aNode.mSubNodes.elementAt(cnt);
				
				ans = String.format("%s%s", 
						ans, 
						this.mToString(node) );
			}
		}else
		if(aNode.mNodeType == BRACKET_NODE){
			
			Node firstNode = (Node)aNode.mSubNodes.elementAt(0);
			
			Node secondNode = (Node)aNode.mSubNodes.elementAt(1);
			
			ans = String.format("[%s, %s]", 
					this.mToString(firstNode),
					this.mToString(secondNode));
		}else
		if(aNode.mNodeType == EQU_NODE){
			
			Node firstNode = (Node)aNode.mSubNodes.elementAt(0);
			
			Node secondNode = (Node)aNode.mSubNodes.elementAt(1);

			ans = String.format("%s = %s", 
					this.mToString(firstNode),
					this.mToString(secondNode));
		}else
		if(aNode.mNodeType == OPE_ADD_NODE){

			if(aNode.mSubNodes == null
			|| aNode.mSubNodes.size() == 0){
				
				ans = ":";
				
			}else{
				Node firstNode = (Node)aNode.mSubNodes.elementAt(0);
				
				Node secondNode = (Node)aNode.mSubNodes.elementAt(1);
	
				ans = String.format("%s + %s", 
						this.mToString(firstNode),
						this.mToString(secondNode));
			}
			
		}else
		if(aNode.mNodeType == OPE_SUB_NODE){

			if(aNode.mSubNodes == null
			|| aNode.mSubNodes.size() == 0){
				
				ans = "-";
				
			}else{
				Node firstNode = (Node)aNode.mSubNodes.elementAt(0);
				
				Node secondNode = (Node)aNode.mSubNodes.elementAt(1);
	
				ans = String.format("%s - %s", 
						this.mToString(firstNode),
						this.mToString(secondNode));
			}
		}else
		if(aNode.mNodeType == OPE_MULTI_NODE){

			if(aNode.mSubNodes == null
			|| aNode.mSubNodes.size() == 0){
				
				ans = "*";
				
			}else{
				Node firstNode = (Node)aNode.mSubNodes.elementAt(0);
				
				Node secondNode = (Node)aNode.mSubNodes.elementAt(1);
	
				ans = String.format("%s * %s", 
						this.mToString(firstNode),
						this.mToString(secondNode));
			}
		}else
		if(aNode.mNodeType == OPE_DIV_NODE){

			if(aNode.mSubNodes == null
			|| aNode.mSubNodes.size() == 0){
				
				ans = "/";
				
			}else{
				Node firstNode = (Node)aNode.mSubNodes.elementAt(0);
				
				Node secondNode = (Node)aNode.mSubNodes.elementAt(1);
	
				ans = String.format("%s / %s", 
						this.mToString(firstNode),
						this.mToString(secondNode));
			}
		}else
		if(aNode.mNodeType == OPE_POWER_NODE){

			ans = "^";
			
		}else
		if(aNode.mNodeType == PARENTHESIS_NODE){

			Node subNode = (Node)aNode.mSubNodes.elementAt(0);
			
			ans = String.format("(%s)", this.mToString(subNode));

		}else
		if(aNode.mNodeType == BRACKET_START_NODE){

			ans = "[";
			
		}else
		if(aNode.mNodeType == BRACKET_END_NODE){

			ans = "]";
			
		}else
		if(aNode.mNodeType == COMMA_NODE){

			ans = ",";
			
		}else
		if(aNode.mNodeType == RATIONAL_NODE){
			
			ans = aNode.mPower.mToString();
			
		}else{
			throw new Exception(aNode.toNodeType());
		}
		
		return ans;
	}
	
	public void mExe(
		Node aNode
	)throws Exception{
		out.printf("%s", aNode.mToString());
	}
	
	public void mExeln(
		Node aNode
	)throws Exception{
		mExe(aNode);
		
		out.println();
	}
	
	public String mAdjust(double arg){
		double plus = arg - 1.0;
		
		double minus = arg + 1.0;
		
		if(Math.abs(plus) < DOUBLE_THRESHOLD){
			return "";
		}else
		if(Math.abs(minus) < DOUBLE_THRESHOLD){
			return "-1";
		}else{
			int intValue = (int)arg;
			
			if(Math.abs((double)intValue - arg) < DOUBLE_THRESHOLD){
				
				return String.format("%d", intValue);
				
			}else{
				return String.format("%f", arg);
			}
		}
		
	}
}

