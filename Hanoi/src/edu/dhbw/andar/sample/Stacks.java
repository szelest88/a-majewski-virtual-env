package edu.dhbw.andar.sample;

import java.util.ArrayList;
import java.util.Stack;

public class Stacks {
	public ArrayList<Stack<Krazek>> stacks;
	
	public Stacks()
	{
		
		this.stacks = new ArrayList<Stack<Krazek>>();
		for(int i=0;i<3;i++)
		{
			stacks.add(new Stack<Krazek>());
		}
		for(int i=0;i<5;i++){ //7
			Krazek temp = new Krazek();
			temp.X=0;
			temp.wysokosc=10-i*10;
			temp.rozmiar=9-i;
			stacks.get(0).add(temp);
		}
		
	}
	public void move(int from, int to){
		if(!stacks.get(from).empty() && (stacks.get(to)).peek().rozmiar>(stacks.get(from).peek().rozmiar))
			stacks.get(to).push(stacks.get(from).pop());
	}
	
	public void push(int ktory, int num)
	{
		Krazek k = new Krazek();
		k.rozmiar=num;
		stacks.get(ktory).push(k);
	}
	
	

}
