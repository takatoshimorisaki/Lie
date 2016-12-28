package mori.Lie.node.parser;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import static mori.Lie.node.tools.Holder.mPrinter;
import mori.Lie.Node;
import mori.tools.AlphabetChecker;
import mori.tools.NumericChecker;
import mori.tools.Rational;

public class TokenParser {

	private Node mRoot;

	private StringBuffer mStrBuf;
	
	private int mNodeType0 = NULL_NODE;
	
	private int mNodeType1 = NULL_NODE;

	private int mNodeType2 = NULL_NODE;
	
	private int mParenthesisDepth;
	
	private AlphabetParser mAlphabetParser;
	
	private NodeParser mNodeParser;
	
	public TokenParser(){
		
		mRoot = new Node();
	
		mAlphabetParser = new AlphabetParser();
		
		mNodeParser = new NodeParser();
	}

	public void mExe(String token)throws Exception{
	
		if(mStrBuf == null){
			
			mStrBuf = new StringBuffer();
		}

		mNodeType2 = mNodeType1;
		
		mNodeType1 = mNodeType0;
		
		if(NumericChecker.mExe(token)){

			mNodeType0 = NUMBER_NODE;
			
			mStrBuf.append(token);
			
		}else
		if(AlphabetChecker.mExe(token)){

			mNodeType0 = ALPHABET_NODE;
			
			mStrBuf.append(token);
			
		}else
		if(token.equals("+")){

			mNodeType0 = OPE_PLUS_NODE;

			mStrBuf.append(token);
			
			mAddNode();
			
		}else
		if(token.equals("-")){

			mNodeType0 = OPE_MINUS_NODE;

			mStrBuf.append(token);
			
			mAddNode();
			
		}else
		if(token.equals("[")){

			mNodeType0 = BRACKET_START_NODE;
			
			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mAddNode();

		}else
		if(token.equals("]")){

			mNodeType0 = BRACKET_END_NODE;
			
			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mAddNode();

		}else
		if(token.equals(",")){

			mNodeType0 = COMMA_NODE;
			
			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mAddNode();
			
		}else
		if(token.equals(":")){

			mNodeType0 = OPE_ADD_NODE;
			
			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mAddNode();
			
		}else
		if(token.equals("*")){

			mNodeType0 = OPE_MULTI_NODE;

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mAddNode();
			
		}else
		if(token.equals("/")){

			mNodeType0 = OPE_DIV_NODE;

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mAddNode();

		}else
		if(token.equals("^")){

			mNodeType0 = OPE_POWER_NODE;

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mAddNode();
			
		}else
		if(token.equals("(")){

			mNodeType0 = PARENTHESIS_NODE;
			
			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mParenthesisDepth++;

		}else
		if(token.equals(")")){

			mNodeType0 = PARENTHESIS_NODE;
			
			mStrBuf.append(token);
			
			mParenthesisDepth--;
			
			mAddNode();
		}	
	}

	private void mAddNumberAlphabetNode()throws Exception{
		
		if(mNodeType0 != NUMBER_NODE
		&& mNodeType1 == NUMBER_NODE
		&& mParenthesisDepth == 0){
			
			Node node = new Node();
			
			if(mNodeType2 == OPE_POWER_NODE){

				node.mNodeType = RATIONAL_NODE;
				
				node.mPower = new Rational(mStrBuf.toString());
				
			}else{

				node.mNodeType = NUMBER_NODE;
				
				node.mCoef = Double.parseDouble( mStrBuf.toString() );
			}
			
			node.mToken = null;

			mRoot.add(node);
			
			mStrBuf = new StringBuffer();
		}else
		if(mNodeType0 != ALPHABET_NODE
		&& mNodeType1 == ALPHABET_NODE
		&& mParenthesisDepth == 0){
			
			Node node = mAlphabetParser.mExe( mStrBuf.toString() );

			mRoot.add(node);
			
			mStrBuf = new StringBuffer();
		}
	}

	private void mAddNode()throws Exception{

		if(mParenthesisDepth == 0){
			
			Node node = new Node();
			
			node.mNodeType = mNodeType0;
			
			if(mNodeType0 == OPE_PLUS_NODE){
			
				node.mNodeType = NUMBER_NODE;
				
				node.mCoef = 1.0;
				
				node.mPower = new Rational(1);
				
			}else
			if(mNodeType0 == OPE_MINUS_NODE){

				node.mNodeType = NUMBER_NODE;

				node.mCoef = -1.0;

				node.mPower = new Rational(-1);
			}
			
			mRoot.add(node);
			
			mStrBuf = null;
		}
	}

	public Node mParse()throws Exception{

		if(mNodeType0 == NUMBER_NODE){

			Node node = new Node();
			
			if(mNodeType1 == OPE_POWER_NODE){

				node.mNodeType = RATIONAL_NODE;
				
				node.mPower = new Rational(mStrBuf.toString());
			}else{

				node.mNodeType = NUMBER_NODE;
				
				node.mCoef = Double.parseDouble( mStrBuf.toString() );
			}
			
			node.mToken = null;
			
			mRoot.add(node);
			
			mStrBuf = null;
		}else
		if(mNodeType0 == ALPHABET_NODE){
			
			Node node = mAlphabetParser.mExe( mStrBuf.toString() );

			mRoot.add(node);
			
			mStrBuf = null;
		}
		
		Node ans = mNodeParser.mExe(mRoot);
		
		return ans;
	}
}
