package mori.Lie.cmd;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;
import mori.Lie.node.tools.*;

public class BracketCmd extends mori.Lie.Lie implements I_Command{
	
	private static mori.Lie.node.tools.Factory mFactory = new mori.Lie.node.tools.Factory();
	
	private static mori.Lie.node.tools.BracketExpander mBracketExpander = new mori.Lie.node.tools.BracketExpander();
	
	private static Printer mPrinter = new Printer();
	
	public boolean mExe(String arg)throws Exception{
		
		int startIndex = arg.indexOf("[");
		
		int periodIndex = arg.indexOf(",");
		
		int endIndex = arg.indexOf("]");
		
		if( startIndex == -1 || periodIndex == -1 || endIndex == -1){
			
			return false;
		}
		
		String firstStr = arg.substring( startIndex + 1, periodIndex ).trim();
		
		String secondStr = arg.substring( periodIndex + 1, endIndex ).trim();
		
		Node firstNode = mFactory.mExe(firstStr);
		
		Node secondNode = mFactory.mExe(secondStr);
		
		Node leftNode = mFactory.mExe(firstNode, secondNode, BRACKET_NODE);
	
		Node rightNode = mBracketExpander.mExe(leftNode);
		
		Node equ = mFactory.mExe(leftNode, rightNode, EQU_NODE);

		int equId = mEquations.add(equ);
		
		out.printf("%d ", equId);
		
		mPrinter.mExeln(equ);
				
		return true;
	}
}

