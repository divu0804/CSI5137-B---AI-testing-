    package org.avmframework.localsearch;

import java.util.ArrayList;

import org.avmframework.TerminationException;
import org.avmframework.objective.ObjectiveValue;

public class HillClimbing extends LocalSearch{

	private ObjectiveValue initial_value;
	private ObjectiveValue next_value;
	
	
	public static final int MAXIMA = 100;
	private int n;
	int final_vector;
	
	public HillClimbing() {}
	
	protected void performSearch() throws TerminationException {
		initialization();
		hillClimbing(); 
	}	 
	
	protected void initialization() throws TerminationException {
		System.out.println(vector);
		initial_value = objFun.evaluate(vector);
		final_vector = var.getValue();
	}

	   
	protected void hillClimbing() throws TerminationException {
	    Boolean climb = true;
	     //This loop iterates through the neighbour list and finds the optimal neighbour
	    while (climb) 
	    {
	       ArrayList<Integer> neighbours = getNeighbours(var.getValue());
	       climb = false;
	       
	       for (Integer N : neighbours) 
	       {
	          var.setValue(N);
	          next_value = objFun.evaluate(vector);
	          if (next_value.betterThan(initial_value)) 
	          {
	             initial_value = next_value;
	             climb = true;
	             final_vector = N;
	          }
	       }
	       var.setValue(final_vector);
	     }
	 }
	
	public ArrayList<Integer> getNeighbours(int current) 
	{
			ArrayList<Integer> neighbours = new ArrayList<>();
		    for (int i = 0; i < MAXIMA ; i++) 
		      {
		          int randomNeighbour = (int) ((current / 2) + (current * Math.random()));
		          neighbours.add(randomNeighbour);
		      }
		    return neighbours;
	}
		
}
