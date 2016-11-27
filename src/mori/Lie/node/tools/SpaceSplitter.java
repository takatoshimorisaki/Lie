package mori.Lie.node.tools;

import java.util.Vector;

public class SpaceSplitter {

	public String[] mExe(String arg){
		
		String[] parts = arg.split(" ");
		
		Vector<String> vector = new Vector<String>();
		
		for(int id = 0; id < parts.length; id++){
			
			if(parts[id].length() != 0){
				
				vector.add(parts[id]);
			}
		}
		
		String[] ans = null;
		
		if(vector.size() > 0){
			
			ans = new String[vector.size()];
			
			for(int id = 0; id < vector.size(); id++){
				
				ans[id] = vector.get(id);
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args){
		
		String str = "factor   lr";
		
		SpaceSplitter ss = new SpaceSplitter();
		
		String[] ans = ss.mExe(str);
		
		for(int id = 0; id < ans.length; id++){
			
			System.out.printf("id %d:%s\n", id, ans[id]);
		}
	}
}
