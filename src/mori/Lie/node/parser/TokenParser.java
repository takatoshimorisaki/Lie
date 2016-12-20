package mori.Lie.node.parser;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.tools.AlphabetChecker;
import mori.tools.NumericChecker;
import mori.tools.Rational;

public class TokenParser {

	private Node mRoot;

	private StringBuffer mStrBuf;
	
	private int mNodeType0;
	
	private int mNodeType1;

	private int mNodeType2;
	
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
			
			mStrBuf.append(token);
			
			mNodeType0 = NUMBER_NODE;
			
		}else
		if(AlphabetChecker.mExe(token)){
			
			mStrBuf.append(token);
			
			mNodeType0 = ALPHABET_NODE;
			
		}else
		if(token.equals("+")){

			mStrBuf.append(token);
			
			mNodeType0 = NUMBER_NODE;
			
		}else
		if(token.equals(":")){

			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mNodeType0 = OPE_ADD_NODE;
			
			mAddNode();
			
		}else
		if(token.equals("-")){

			mStrBuf.append(token);
			
			mNodeType0 = NUMBER_NODE;
			
		}else
		if(token.equals("*")){

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mNodeType0 = OPE_MULTI_NODE;

			mAddNode();
			
		}else
		if(token.equals("/")){

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mNodeType0 = OPE_DIV_NODE;

			mAddNode();

		}else
		if(token.equals("^")){

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mNodeType0 = OPE_POWER_NODE;

			mAddNode();
			
		}else
		if(token.equals("(")){

			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mNodeType0 = PARENTHESIS_NODE;
			
			mParenthesisDepth++;

		}else
		if(token.equals(")")){

			mStrBuf.append(token);
			
			mNodeType0 = PARENTHESIS_NODE;
			
			mParenthesisDepth--;
			
			mAddNode();
		}	
	}

	private void mAddNumberAlphabetNode()throws Exception{
		
		if(mNodeType1 == NUMBER_NODE
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

			out.printf("TokenParser number %s\n", node.mToString());
			
			mRoot.add(node);
			
			mStrBuf = new StringBuffer();
		}else
		if(mNodeType1 == ALPHABET_NODE
		&& mParenthesisDepth == 0){
			
			Node node = mAlphabetParser.mExe( mStrBuf.toString() );

			out.printf("TokenParser alphabet %s\n", node.mToString());
			
			mRoot.add(node);
			
			mStrBuf = new StringBuffer();
		}
	}

	private void mAddNode()throws Exception{

		if(mParenthesisDepth == 0){
			
			Node node = new Node();
			
			node.mNodeType = mNodeType0;
			
			out.printf("TokenParser mAddNode %s\n", node.mToString());
			
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
		
		out.printf("TokenParser mParse mRoot %s\n", mRoot.mToString());
		
		Node ans = mNodeParser.mExe(mRoot);
		
		return ans;
	}
}
