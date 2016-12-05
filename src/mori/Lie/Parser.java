package mori.Lie;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Parser extends mori.Lie.Lie {
	
	private static String mCharSet = "UTF-8"; // "US-ASCII"; // "UTF-8";
	
	private static mori.Lie.cmd.I_Command mBracketCmd = new mori.Lie.cmd.BracketCmd();

	private static mori.Lie.cmd.I_Command mExpandCmd = new mori.Lie.cmd.ExpandCmd();
	
	private static mori.Lie.cmd.I_Command mEquationCheckCmd = new mori.Lie.cmd.EquationCheckCmd();
	
	private static mori.Lie.cmd.I_Command mMultiplyCmd = new mori.Lie.cmd.MultiplyCmd();
	
	private static mori.Lie.cmd.I_Command mDelCmd = new mori.Lie.cmd.DelCmd();
	
	private static mori.Lie.cmd.I_Command mDetailCmd = new mori.Lie.cmd.DetailCmd();
	
	private static mori.Lie.cmd.I_Command mAddCmd = new mori.Lie.cmd.AddCmd();

	private static mori.Lie.cmd.I_Command mFactorCmd = new mori.Lie.cmd.FactorCmd();

	private static mori.Lie.cmd.I_Command mShrinkCmd = new mori.Lie.cmd.ShrinkCmd();
	
	public Parser(){
		
		this.read();
	}
	
	public void add(String arg){
		
		try{
			String msg = arg.trim();
			
			if(msg.length() == 0){
				// nothig to do.
			}else
			if(msg.startsWith("token") == true){
				
				mTokens.add( msg.substring(5) );
			}else
			if(msg.indexOf("=") > 0){
				
				mEquations.add(msg);
				
			}else
			if(msg.indexOf("*") > 0){
				
				mMultiplyCmd.mExe(msg);
				
			}else
			if(msg.indexOf("+") > 0){
				
				mAddCmd.mExe(msg);
				
			}else
			if(msg.startsWith("check") == true){
					
				mEquationCheckCmd.mExe(arg);
				
			}else
			if(msg.startsWith("expand") == true){
				
				mExpandCmd.mExe(arg);

			}else
			if(msg.startsWith("del") == true){
				
				mDelCmd.mExe(arg);
				
			}else
			if(msg.startsWith("list") == true){
				
				mEquations.mList();
				
			}else
			if(msg.startsWith("detail") == true){
				
				mDetailCmd.mExe(arg);

			}else
			if(msg.startsWith("factor") == true){
				
				mFactorCmd.mExe(arg);

			}else
			if(msg.startsWith("shrink") == true){
				
				mShrinkCmd.mExe(arg);
				
			}else{
				
				mBracketCmd.mExe(arg);
				
			}
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
	}
	
	private void read(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("DOC/input.txt"),mCharSet));
			
			String line = null;

			while((line = br.readLine()) != null){

				if(line.startsWith("#")){
				}else{
					this.add(line);
				}
			}
			
			br.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println(ex);
		}
	}
}

