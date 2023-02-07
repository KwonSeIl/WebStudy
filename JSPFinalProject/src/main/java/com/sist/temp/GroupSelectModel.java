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
	   boolean bCheck=false;
	   int s=0;
	   for(int i=0;i<4;i++)
	   {
		   bCheck=true;
		   //System.out.println("1");
		   while(bCheck)
		   {
		      s=(int)(Math.random()*13);
		      System.out.println("s="+s);
		      //System.out.println(s);
		      bCheck=false;
		          if(com[s]==1)
		    	  {
		    		  bCheck=true;
		    		  break;
		    	  }
		     
		   }
		   com[s]=1;
	   }
	   
	   
	   bCheck=false;
	   s=0;
	   for(int i=0;i<4;i++)
	   {
		   bCheck=true;
		   //System.out.println("1");
		   while(bCheck)
		   {
		      s=(int)(Math.random()*13);
		      //System.out.println(s);
		      bCheck=false;
		          if(com[s]!=0)
		    	  {
		    		  bCheck=true;
		    		  break;
		    	  }
		   }
		   com[s]=2;
	   }
	   bCheck=false;
	   s=0;
	   for(int i=0;i<4;i++)
	   {
		   bCheck=true;
		   //System.out.println("1");
		   while(bCheck)
		   {
		      s=(int)(Math.random()*13);
		      //System.out.println(s);
		      bCheck=false;
		          if(com[s]!=0)
		    	  {
		    		  bCheck=true;
		    		  break;
		    	  }
		   }
		   com[s]=3;
	   }
	   for(int i:com)
	   {
		   System.out.print(i+" ");
	   }
	   return list;
   }
}