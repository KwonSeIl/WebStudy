package com.sist.temp;
import java.util.*;
public class GroupSelectModel {
   public static void main(String[] args) {
	   GroupSelectModel m=new GroupSelectModel();
	   m.group_choice();
   }
   public List<Integer> group_choice()
   {
	   List<Integer> list=new ArrayList<Integer>();
	   int[] com=new int[13];
	   int[] rand=new int[13];
	   int su=0;
	   boolean bCheck=false;
	   for(int i=0;i<13;i++)
	   {
		   bCheck=true;
		   while(bCheck)
		   {
			   su=(int)(Math.random()*13);
			   bCheck=false;
			   for(int j=0;j<i;j++)
			   {
				   if(com[j]==su)
				   {
					   bCheck=true;
					   break;
				   }
			   }
		   }
		   com[i]=su;
	   }
	   for(int i=0;i<13;i++)
	   {
		   
		   if(i>=0 && i<4)
		   {
		      rand[com[i]]=1;
		   }
		   else if(i>=4 && i<8)
		   {
			   rand[com[i]]=2;
		   }
		   else if(i>=8 && i<12)
		   {
			   rand[com[i]]=3;
		   }
	   }
	   
	   for(int i:rand)
	   {
		   System.out.print(i+" ");
	   }
	   return list;
   }
}